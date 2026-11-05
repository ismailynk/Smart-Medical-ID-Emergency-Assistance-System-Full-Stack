package com.firat.ozel.hasta.servis.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class BaseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8015215972610554262L;

    private UUID id;
}
