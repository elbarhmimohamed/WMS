package com.wms.model.operation;

import com.wms.model.operation.Commande;
import com.wms.model.stock.Composante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ligneCommande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "composante_id")
    private Composante composante;

    private int quantite;

    private double prix;

    @OneToOne(cascade = CascadeType.ALL)
    private ControleQualite controleQualite;

}
