package com.firat.ozel.hasta.servis.web.rest;

import com.firat.ozel.hasta.servis.domain.dto.HastaDTO;
import com.firat.ozel.hasta.servis.service.HastaService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/hasta")
public class HastaResource {

    private final HastaService hastaService;

    public HastaResource(HastaService hastaService) {
        this.hastaService = hastaService;
    }

    @PutMapping("/")
    public void guncelleHasta(@RequestBody HastaDTO hastaBilgiDTO) {
        Optional<HastaDTO> hastaItem = hastaService.getirById(hastaBilgiDTO.getId());
        if (hastaItem.isPresent()) {
            hastaService.guncelle(hastaBilgiDTO);
        } else new RuntimeException("Hasta Bilgisi Bulunamadi");
    }

    @GetMapping(path = "/hastaBilgiGetir/{TCKIMLIK}")
    public HastaDTO hastaBilgiGetir(@PathVariable("TCKIMLIK") String tckimlik) {
        return hastaService.hastaBilgiGetir(tckimlik);
    }
}
