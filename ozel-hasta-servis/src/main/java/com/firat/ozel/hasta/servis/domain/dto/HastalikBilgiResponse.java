package com.firat.ozel.hasta.servis.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class HastalikBilgiResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 6323921091675853714L;

    private HastaDTO hasta;
    private List<HastaHastalikBilgiResponse> hastaHastalik;
}
