package com.firat.ozel.hasta.servis.domain.dto;

import com.firat.ozel.hasta.servis.domain.model.BaseModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class HastaHastalikDTO extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1716478863728332100L;

    private HastaDTO hasta;
    private HastalikDTO hastalik;
    private LocalDate teshisTarihi;
}
