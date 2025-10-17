// src/main/java/com/workintech/s18d1/exceptions/BurgerErrorResponse.java
package com.workintech.s18d1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BurgerErrorResponse {
    private final String message;
}
