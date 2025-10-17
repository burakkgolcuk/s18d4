// com/workintech/s18d1/util/BurgerValidation.java
package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {

    public static void validate(Burger b) {
        if (b == null) {
            throw new BurgerException("Burger is null", HttpStatus.BAD_REQUEST);
        }
        if (b.getName() == null || b.getName().isBlank()) {
            throw new BurgerException("Burger name is required", HttpStatus.BAD_REQUEST);
        }
        if (b.getPrice() == null || b.getPrice() < 0) {
            throw new BurgerException("Burger price must be non-negative", HttpStatus.BAD_REQUEST);
        }
        if (b.getBreadType() == null) {
            throw new BurgerException("BreadType is required", HttpStatus.BAD_REQUEST);
        }
    }
}
