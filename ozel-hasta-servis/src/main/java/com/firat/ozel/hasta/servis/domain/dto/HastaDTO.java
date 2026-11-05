package com.firat.ozel.hasta.servis.domain.dto;

import com.firat.ozel.hasta.servis.domain.enums.EnumCinsiyet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class HastaDTO extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2338090675715700738L;

    private String tckimlik;
    private String adi;
    private String tel;
    private String yakinTel1;
    private String yakinTel2;
    private LocalDate dogumTarihi;
    @Enumerated(EnumType.STRING)
    private EnumCinsiyet cinsiyet;
}
