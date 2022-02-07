package com.wms.model.operation;

import com.wms.model.personne.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "controleQualite")
public class ControleQualite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantiteReel;
    private int echantillon;
    private int defectueux;
    @OneToOne
    private LigneCommande ligneCommande;

    private boolean accepter;

    @ManyToOne
    @JoinColumn(name="reception")
    private Reception reception;



}
