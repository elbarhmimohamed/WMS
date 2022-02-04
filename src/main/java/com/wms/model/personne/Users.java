package com.wms.model.personne;

import com.wms.model.operation.Commande;
import com.wms.model.operation.Inventaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
    @Column(name="creating_date")
    private LocalDateTime creating_date;
    @Column(name="editing_date")
    private LocalDateTime editing_date;

    @OneToMany(mappedBy = "user")
    private Collection<Commande> commandes;

    @OneToMany(mappedBy = "user")
    private Collection<Inventaire> inventaires;


	
}

