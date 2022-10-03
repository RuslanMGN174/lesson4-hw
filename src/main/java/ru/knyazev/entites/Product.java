package ru.knyazev.entites;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private int cost;
}
