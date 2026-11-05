package com.firat.ozel.hasta.servis.yonetici;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface KayitImplements<DTO extends Serializable> {

    DTO guncelle(DTO dtoEntity);

    DTO kaydet(DTO dtoEntity);

    List<DTO> tumuListele();

    DTO getir(UUID id);

    Optional<DTO> getirById(UUID id);

    boolean sil(UUID id);

    boolean tumunuSil(List<UUID> dtoEntity);
}
