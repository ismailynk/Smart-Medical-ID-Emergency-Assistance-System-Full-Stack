package com.firat.ozel.hasta.servis.service;

import com.firat.ozel.hasta.servis.domain.dto.HastaDTO;
import com.firat.ozel.hasta.servis.domain.model.Hasta;
import com.firat.ozel.hasta.servis.repository.HastaRepository;
import com.firat.ozel.hasta.servis.yonetici.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HastaService extends BaseService<HastaDTO, Hasta> {

    private final Logger log = LoggerFactory.getLogger(HastaService.class);

    private final HastaRepository hastaRepository;

    protected HastaService(HastaRepository hastaRepository) {
        super(hastaRepository, HastaDTO.class, Hasta.class);
        this.hastaRepository = hastaRepository;
    }

    public void hastaKaydiOlustur(String tckimlik, String adi) {
        if (!hastaRepository.existsByTckimlik(tckimlik)) {
            HastaDTO hastaDTO = new HastaDTO();
            hastaDTO.setTckimlik(tckimlik);
            hastaDTO.setAdi(adi);
            kaydet(hastaDTO);
        }
    }

    public HastaDTO hastaBilgiGetir(String tckimlik){
        return getModelMapper().getDTO(hastaRepository.getirByHasta(tckimlik));
    }
}
