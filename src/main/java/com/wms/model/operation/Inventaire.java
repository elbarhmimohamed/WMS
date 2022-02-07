package com.wms.model.operation;


import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import com.wms.model.stock.Inventaire_composante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "inventaire")
public class Inventaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventaire_id")
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date")
    private Date date;

    @Column(name="Ref")
    private String reference;

    @ManyToOne
    private Users user;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "inventaire_id")
    private List<Inventaire_composante> inventaire_composantes;



}

