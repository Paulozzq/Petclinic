package com.tecsup.petclinic.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "owners")
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "direccion")
    private String address;
    @Column(name = "telefono")
    private String telephone;
    @Column(name = "email")
    private String email;

    public Owner() {
    }

    public Owner(Integer id, String nombres, String address, String telephone, String email) {
        super();
        this.id = id;
        this.nombres = nombres;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
