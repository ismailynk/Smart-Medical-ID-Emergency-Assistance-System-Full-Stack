package com.firat.ozel.hasta.servis.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "hasta_hastalik")
public class HastaHastalik extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -2141828401082527654L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hasta_id", nullable = false)
    private Hasta hasta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hastalik_id", nullable = false)
    private Hastalik hastalik;

    @Column(name = "teshis_tarihi")
    private LocalDate teshisTarihi;
}
