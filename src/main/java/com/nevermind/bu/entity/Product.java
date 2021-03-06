package com.nevermind.bu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * Product Entity Class
 * Contains data about products(s) and write them
 *
 * @author Roman Kovaliov
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
