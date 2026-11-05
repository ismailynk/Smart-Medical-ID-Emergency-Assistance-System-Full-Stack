package com.firat.ozel.hasta.servis.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "hastalik")
public class Hastalik extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -8569487898508081810L;

    @Column(name = "adi", length = 150)
    private String adi;

    @Column(name = "tanim", length = 4000)
    private String tanim;

    @Column(name = "belirtiler", length = 4000)
    private String belirtiler;

    @Column(name = "yapilmasi_gerekenler", length = 4000)
    private String yapilmasiGerekenler;

    @Column(name = "yapilmamasi_gerekenler", length = 4000)
    private String yapilmamasiGerekenler;
}
