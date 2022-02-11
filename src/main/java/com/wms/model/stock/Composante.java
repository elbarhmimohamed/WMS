package com.wms.model.stock;


import com.wms.model.operation.Commande;
import com.wms.model.operation.Inventaire;
import com.wms.model.operation.LigneCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Builder(toBuilder = true)
@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "composante")

public class Composante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="seuil")
    private long seuil;
    @Column(name="quantity")
    private long quantity;
    @Column(name="type")
    private boolean type;   // type == 0 => matiere 1ere   // type == 1 => produit fini



    @ManyToOne()
    private Categorie categorie;

    @OneToMany(mappedBy = "composante")
    private List<LigneCommande> ligneCommandes = new ArrayList<>() ;






}
