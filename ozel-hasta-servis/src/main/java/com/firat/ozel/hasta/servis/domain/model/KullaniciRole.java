package com.firat.ozel.hasta.servis.domain.model;

import com.firat.ozel.hasta.servis.domain.enums.EnumRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.util.UUID;

@Entity
@Table(name = "kullanici_role")
public @Data
class KullaniciRole extends BaseModel {

    @Serial
    private static final long serialVersionUID = 5239484708126459698L;

    @Column(name = "kullanici_id", nullable = false)
    private UUID kullanici;

    @Column(name = "role", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private EnumRoles role;
}

