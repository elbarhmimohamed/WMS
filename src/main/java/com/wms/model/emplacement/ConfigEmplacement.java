package com.wms.model.emplacement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ConfigEmplacement")
public class ConfigEmplacement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String indexRangee;
    private int numNiveau;
    private int numRack;
    private int numPosition;
    private int occupation;
}
