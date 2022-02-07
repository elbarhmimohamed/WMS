package com.wms.model.personne;

import com.wms.model.operation.Commande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.lang.Nullable;

import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.util.Collection;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="mail")
    private String mail;

    @Column(name="adress")
    private String adress;
    @Column(name="phone")
    private String phone;
    @Column(name="status")
    private boolean status;
    @Column(name="role")   //role == true => person is customer  -----  role == false  => person is supplier
    private boolean role;
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "fournisseur")
    private Collection<Commande> commandes;
}
