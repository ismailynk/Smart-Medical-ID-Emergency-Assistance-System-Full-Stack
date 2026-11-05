package com.firat.ozel.hasta.servis.web.rest;

import com.firat.ozel.hasta.servis.domain.dto.HastaDTO;
import com.firat.ozel.hasta.servis.domain.dto.HastaHastalikBilgiResponse;
import com.firat.ozel.hasta.servis.domain.dto.HastaHastalikDTO;
import com.firat.ozel.hasta.servis.domain.dto.HastalikBilgiResponse;
import com.firat.ozel.hasta.servis.service.HastaHastalikService;
import com.firat.ozel.hasta.servis.service.HastaService;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/publics/")
public class PublicsHastaBilgiResource {

    private final HastaService hastaService;
    private final HastaHastalikService hastaHastalikService;

    public PublicsHastaBilgiResource(HastaService hastaService, HastaHastalikService hastaHastalikService) {
        this.hastaService = hastaService;
        this.hastaHastalikService = hastaHastalikService;
    }

    @GetMapping(path = "{HASTAID}")
    public HastalikBilgiResponse hastaBilgiGetir(@PathVariable("HASTAID") UUID hastaUUID) {
        HastalikBilgiResponse hastalikBilgiResponse = new HastalikBilgiResponse();
        HastaDTO hastaDTO = hastaService.getir(hastaUUID);
        List<HastaHastalikBilgiResponse> hastaHastalikBilgiResponseList = new ArrayList<>();
        for (HastaHastalikDTO hastaHastalikDTO : hastaHastalikService.listeleByHastaHastalik(hastaUUID)) {
            HastaHastalikBilgiResponse hastaHastalikBilgiResponse = new HastaHastalikBilgiResponse();
            hastaHastalikBilgiResponse.setHastalik(hastaHastalikDTO.getHastalik());
            hastaHastalikBilgiResponse.setTeshisTarihi(hastaHastalikDTO.getTeshisTarihi().toString());
            hastaHastalikBilgiResponseList.add(hastaHastalikBilgiResponse);
        }
        hastalikBilgiResponse.setHasta(hastaDTO);
        hastalikBilgiResponse.setHastaHastalik(hastaHastalikBilgiResponseList);
        return hastalikBilgiResponse;
    }
}
