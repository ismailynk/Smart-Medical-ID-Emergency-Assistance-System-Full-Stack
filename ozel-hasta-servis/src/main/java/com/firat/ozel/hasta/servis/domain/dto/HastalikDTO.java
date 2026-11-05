package com.firat.ozel.hasta.servis.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class HastalikDTO extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2372244442134699474L;

    private String adi;
    private String tanim;
    private String belirtiler;
    private String yapilmasiGerekenler;
    private String yapilmamasiGerekenler;
}
