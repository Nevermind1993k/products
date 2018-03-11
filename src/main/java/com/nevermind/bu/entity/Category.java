package com.nevermind.bu.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Category Entity Class
 * Contains data about category(s) and write them
 *
 * @author Roman Kovaliov
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
