package com.firat.ozel.hasta.servis.domain.model;

import com.firat.ozel.hasta.servis.domain.enums.EnumCinsiyet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "hasta")
public class Hasta extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = -1089445198975927690L;

    @Column(name = "tckimlik", nullable = false, unique = true, length = 11)
    private String tckimlik;

    @Column(name = "adi", length = 100)
    private String adi;

    @Column(name = "tel", length = 50)
    private String tel;

    @Column(name = "yakin_tel1", length = 50)
    private String yakinTel1;

    @Column(name = "yakin_tel2", length = 50)
    private String yakinTel2;

    @Column(name = "dogum_tarihi")
    private LocalDate dogumTarihi;

    @Enumerated(EnumType.STRING)
    @Column(name = "cinsiyet", length = 50)
    private EnumCinsiyet cinsiyet;
}
