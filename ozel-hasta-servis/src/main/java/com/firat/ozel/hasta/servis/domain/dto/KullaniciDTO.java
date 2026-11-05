package com.firat.ozel.hasta.servis.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class KullaniciDTO extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4835269571471001182L;

    private String kullaniciKodu;
    private String tckimlik;
    private String kullaniciAdi;
    private String mail;
    private Boolean active;
    private List<KullaniciRoleDTO> authorities;
}
