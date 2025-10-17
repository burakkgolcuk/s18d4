// src/main/java/com/workintech/s18d1/controller/BurgerController.java
package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // RestController, Get/Post/Put/DeleteMapping, PathVariable, RequestBody
import java.util.List;

@RestController
public class BurgerController {

    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping("/burger")
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/burger/{id}")
    public Burger findById(@PathVariable Long id) {
        return burgerDao.findById(id);
    }

    @PostMapping("/burger")
    public Burger save(@RequestBody Burger burger) {
        // CREATE sırasında validasyon (istersen)
        // BurgerValidation.validate(burger);
        return burgerDao.save(burger);
    }

    @PutMapping("/burger")
    public Burger update(@RequestBody Burger burger) {
        // UPDATE: testler null alanlara izin veriyor, o yüzden burada validation çağırma
        return burgerDao.update(burger);
    }

    @DeleteMapping("/burger/{id}")
    public Burger remove(@PathVariable Long id) {
        return burgerDao.remove(id);
    }

    @GetMapping("/burger/price/{price}")
    public List<Burger> findByPrice(@PathVariable int price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/burger/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/burger/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }
}
