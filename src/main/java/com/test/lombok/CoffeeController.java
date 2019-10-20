package com.test.lombok;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoffeeController {

    @GetMapping("/coffees")
    public List<Coffee> all() {
        List<Coffee> coffees = new ArrayList<>();
        coffees.add(new Coffee("01","Mocha"));
        coffees.add(new Coffee("02","Cappuccino"));

        Coffee coffee = new Coffee();
        coffee.setId("03");
        coffee.setName("Latte");
        coffees.add(coffee);
        return coffees;
    }
}
