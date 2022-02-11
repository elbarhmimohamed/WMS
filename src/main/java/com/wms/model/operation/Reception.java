package com.wms.model.operation;

import com.wms.model.personne.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
@Table(name = "Reception")
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date")

    private Date date;

    private Boolean Qualite;

    @Column(name="reference")
    private String reference;

    @OneToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @OneToOne(cascade = CascadeType.ALL)
    private FichierStock fichierStock;

    @OneToMany(mappedBy="reception",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ControleQualite> controleQualiteList;









}
