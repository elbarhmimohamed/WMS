package com.wms.model.emplacement;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class ConfigEmplacement {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int numRangee;
    private int NumNiveau;
    private int NumRack;





}
