package com.lezenford.spring.example.jpa.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @JoinColumn(name = "roleid")
    @ManyToOne(fetch = FetchType.EAGER) //данные тянутся в момент запроса к БД
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY) //данные тянутся в момент первого обращения к полю
    @JoinTable(
            name = "carts",
            joinColumns = @JoinColumn(name = "USERID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCTID")
    )
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", products=" + products +
                '}';
    }
}
