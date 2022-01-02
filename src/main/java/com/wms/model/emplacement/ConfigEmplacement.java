package com.wms.model.emplacement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ConfigEmplacement")
public class ConfigEmplacement {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int numRangee;
    private int NumNiveau;
    private int NumRack;





}
