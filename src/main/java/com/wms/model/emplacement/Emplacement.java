package com.wms.model.emplacement;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Emplacement {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private  String refemplacement;
    private  float tauxOccupation;
    @OneToOne
    private  Palette palette;



}
