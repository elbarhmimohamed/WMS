package com.wms.model.personne;

import com.wms.model.operation.Commande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String Name;
    private String email;

    @Column(name="adress")
    private String adress;
    @Column(name="phone")
    private String phone;
    @Column(name="status")
    private boolean status;

    @OneToOne
    private Image image;

    @OneToMany(mappedBy = "person")
    private Collection<Commande> commandes;
}
