package com.firat.ozel.hasta.servis.domain.dto;

import com.firat.ozel.hasta.servis.domain.enums.EnumRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class KullaniciRoleDTO extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8815652349546131928L;

    private UUID kullanici;
    @Enumerated(EnumType.STRING)
    private EnumRoles role;
}
