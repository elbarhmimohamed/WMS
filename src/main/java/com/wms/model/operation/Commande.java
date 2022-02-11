package com.wms.model.operation;


import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commande")
public class Commande{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date")

    private Date date;
    @Column(name="reference")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Person fournisseur;


    @OneToMany(mappedBy="commande" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommande;

    private double total;

}
