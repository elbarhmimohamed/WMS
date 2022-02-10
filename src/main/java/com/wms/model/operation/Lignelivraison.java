package com.wms.model.operation;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.stock.Composante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lignelivraison")
public class Lignelivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "composante_id")
    private Composante composante;

    private int quantite;

    private double prix;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "livraison_id")
    private PreparationCommande preparationCommande;

    @OneToOne
    @JoinColumn(name="emplacement_id")
    private Emplacement emplacement;






}
