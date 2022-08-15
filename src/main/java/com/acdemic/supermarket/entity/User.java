package com.acdemic.supermarket.entity;

import com.acdemic.supermarket.entity.template.AbcEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends AbcEntity {

    private String firstname;

    private String lastname;

    private String username;

    private String phoneNumber;

    private String password;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cashback cashback;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

}
