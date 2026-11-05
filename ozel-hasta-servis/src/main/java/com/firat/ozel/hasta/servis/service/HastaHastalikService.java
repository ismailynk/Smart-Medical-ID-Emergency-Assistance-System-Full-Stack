package com.firat.ozel.hasta.servis.service;

import com.firat.ozel.hasta.servis.domain.dto.HastaHastalikDTO;
import com.firat.ozel.hasta.servis.domain.model.HastaHastalik;
import com.firat.ozel.hasta.servis.repository.HastaHastalikRepository;
import com.firat.ozel.hasta.servis.yonetici.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class HastaHastalikService extends BaseService<HastaHastalikDTO, HastaHastalik> {

    private final Logger log = LoggerFactory.getLogger(HastaHastalikService.class);

    private final HastaHastalikRepository hastaHastalikRepository;

    protected HastaHastalikService(HastaHastalikRepository hastaHastalikRepository) {
        super(hastaHastalikRepository, HastaHastalikDTO.class, HastaHastalik.class);
        this.hastaHastalikRepository = hastaHastalikRepository;
    }

    public Boolean hastalikVar(UUID hastaId, UUID hastalikId) {
        return hastaHastalikRepository.existsByHasta_IdAndHastalik_Id(hastaId, hastalikId);
    }

    public List<HastaHastalikDTO> listeleByHastaHastalik(UUID hastaId) {
        return getModelMapper().getDTOList(hastaHastalikRepository.getByHasta_IdOrderByTeshisTarihi(hastaId));
    }
}
