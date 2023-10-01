package com.mirea.rschirpractice5.entity;

import jakarta.persistence.*;
import lombok.Data;




@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private int sellerId;
    private String productType;
    private double price;
    private String title;
}
