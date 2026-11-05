package com.firat.ozel.hasta.servis.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class HastaHastalikBilgiResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7823349963352298506L;

    private HastalikDTO hastalik;
    private String teshisTarihi;
}
