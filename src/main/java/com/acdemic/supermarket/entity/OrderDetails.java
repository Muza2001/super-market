package com.acdemic.supermarket.entity;

import com.acdemic.supermarket.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails extends AbcEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Product products;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Integer countOfProduct;

    private Double quantity;

    private Double price;

    private boolean isDeleted = false;
}
