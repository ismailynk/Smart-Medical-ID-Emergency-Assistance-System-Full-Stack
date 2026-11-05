package com.firat.ozel.hasta.servis.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "kullanici")
public class Kullanici extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 2900934697909747436L;

    @Column(name = "kullanici_kodu", nullable = false, unique = true, length = 50)
    private String kullaniciKodu;

    @Column(name = "tckimlik", length = 11)
    private String tckimlik;

    @Column(name = "kullanici_adi", nullable = false, length = 150)
    private String kullaniciAdi;

    @Column(name = "sifre")
    private String sifre;

    @Column(name = "mail", nullable = false, unique = true, length = 100)
    private String mail;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "kullanici_role_fk1"), referencedColumnName = "id", name = "kullanici_id")
    private List<KullaniciRole> authorities;
}
