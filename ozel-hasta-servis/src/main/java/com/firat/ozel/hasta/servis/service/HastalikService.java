package com.firat.ozel.hasta.servis.service;

import com.firat.ozel.hasta.servis.domain.dto.HastalikDTO;
import com.firat.ozel.hasta.servis.domain.model.Hastalik;
import com.firat.ozel.hasta.servis.repository.HastalikRepository;
import com.firat.ozel.hasta.servis.yonetici.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HastalikService extends BaseService<HastalikDTO, Hastalik> {

    private final Logger log = LoggerFactory.getLogger(HastalikService.class);

    private final HastalikRepository hastalikRepository;

    protected HastalikService(HastalikRepository hastalikRepository) {
        super(hastalikRepository, HastalikDTO.class, Hastalik.class);
        this.hastalikRepository = hastalikRepository;
    }
}
