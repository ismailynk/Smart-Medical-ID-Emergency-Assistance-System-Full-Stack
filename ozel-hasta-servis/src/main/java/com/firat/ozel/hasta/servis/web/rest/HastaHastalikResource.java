package com.firat.ozel.hasta.servis.web.rest;

import com.firat.ozel.hasta.servis.domain.dto.HastaHastalikDTO;
import com.firat.ozel.hasta.servis.service.HastaHastalikService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/hasta-hastalik")
public class HastaHastalikResource {

    private final HastaHastalikService hastaHastalikService;

    public HastaHastalikResource(HastaHastalikService hastaHastalikService) {
        this.hastaHastalikService = hastaHastalikService;
    }

    @PostMapping("/")
    public void kaydetHastaHastalik(@RequestBody HastaHastalikDTO hastaHastalikDTO) {
        if (hastaHastalikService.hastalikVar(hastaHastalikDTO.getHasta().getId(), hastaHastalikDTO.getHastalik().getId())) {
            throw new RuntimeException("Hastalık Kaydı Var");
        }
        hastaHastalikDTO.setTeshisTarihi(LocalDate.now());
        hastaHastalikService.kaydet(hastaHastalikDTO);
    }

    @DeleteMapping(path = "/{HASTA_HASTALIK_UUID}")
    public void silHastaHastalik(@PathVariable("HASTA_HASTALIK_UUID") UUID hastaHastalikUUID) {
        hastaHastalikService.sil(hastaHastalikUUID);
    }

    @PutMapping("/")
    public void guncelleHastaHastalik(@RequestBody HastaHastalikDTO hastaHastalikBilgiDTO) {
        Optional<HastaHastalikDTO> hastaHastalikItem = hastaHastalikService.getirById(hastaHastalikBilgiDTO.getId());
        if (hastaHastalikItem.isPresent()) {
            hastaHastalikService.guncelle(hastaHastalikBilgiDTO);
        } else throw new RuntimeException("Hasta Hastalık Bilgisi Bulunamadi");
    }

    @GetMapping(path = "/listeleHastaHastalik/{HASTAID}")
    public List<HastaHastalikDTO> listeleHastaHastalik(@PathVariable("HASTAID") UUID hastaId) {
        return hastaHastalikService.listeleByHastaHastalik(hastaId);
    }
}
