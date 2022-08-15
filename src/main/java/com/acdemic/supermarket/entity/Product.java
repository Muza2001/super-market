package com.acdemic.supermarket.entity;

import com.acdemic.supermarket.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbcEntity {

    private String name;

    private Double price;

    private String description;

    private Integer garaty;

    private String status;

    @ManyToOne
    private Category category;

}
