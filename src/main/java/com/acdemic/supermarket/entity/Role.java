package com.acdemic.supermarket.entity;

import com.acdemic.supermarket.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AbcEntity {

    private String name;

}
