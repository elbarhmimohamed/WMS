package com.wms.model.emplacement;

import com.wms.model.stock.Composante;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Palette {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Composante composante;
    private float qteArticle;
}
