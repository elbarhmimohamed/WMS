package com.wms.model.personne;

import com.wms.model.operation.Commande;
import com.wms.model.operation.Inventaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Name;
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private Boolean role;
    @Column(name="creating_date")
    private Date creating_date;
    @Column(name="editing_date")
    private Date editing_date;

    @OneToMany(mappedBy = "user")
    private Collection<Commande> commandes;

    @OneToMany(mappedBy = "user")
    private Collection<Inventaire> inventaires;


	
}

