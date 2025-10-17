// com/workintech/s18d1/dao/BurgerDaoImpl.java
package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao {

    private final EntityManager entityManager;

    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        Burger found = entityManager.find(Burger.class, id);
        if (found == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return found;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> q = entityManager.createQuery(
                "SELECT b FROM Burger b", Burger.class);
        return q.getResultList();
    }

    @Override
    public List<Burger> findByPrice(int price) {
        TypedQuery<Burger> q = entityManager.createQuery(
                "SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC",
                Burger.class);
        q.setParameter("price", (double) price);
        return q.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> q = entityManager.createQuery(
                "SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC",
                Burger.class);
        q.setParameter("breadType", breadType);
        return q.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> q = entityManager.createQuery(
                "SELECT b FROM Burger b " +
                        "WHERE LOWER(b.contents) LIKE LOWER(CONCAT('%', :content, '%'))",
                Burger.class);
        q.setParameter("content", content);
        return q.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(Long id) {
        Burger toRemove = entityManager.find(Burger.class, id);
        if (toRemove == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        entityManager.remove(toRemove);
        return toRemove;
    }
}
