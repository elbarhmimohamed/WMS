package com.wms.model.operation;


import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventaire")
public class Inventaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="date")
    private Date date;

    @ManyToOne
    private Users user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Composante> composantes;

}

