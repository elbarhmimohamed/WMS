package com.wms.model.stock;


import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Commande;
import com.wms.model.operation.Inventaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "composante")

public class Composante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="seuil")
    private long seuil;
    @Column(name="quantity")
    private long quantity;
    @Column(name="type")
    private boolean type;   // type == 0 => matiere 1ere   // type == 1 => produit fini


    @ManyToOne
    private Categorie categorie;



    @ManyToMany(mappedBy = "composantes", fetch = FetchType.EAGER)
    private Collection<Commande> commandes = new ArrayList<>() ;



}
