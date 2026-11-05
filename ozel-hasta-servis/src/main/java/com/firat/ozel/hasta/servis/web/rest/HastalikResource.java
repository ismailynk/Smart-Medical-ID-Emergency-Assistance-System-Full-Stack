package com.firat.ozel.hasta.servis.web.rest;

import com.firat.ozel.hasta.servis.domain.dto.HastalikDTO;
import com.firat.ozel.hasta.servis.service.HastalikService;
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
@RequestMapping("/api/hastalik")
public class HastalikResource {

    private final HastalikService hastalikService;

    public HastalikResource(HastalikService hastalikService) {
        this.hastalikService = hastalikService;
    }

    @PostMapping("/")
    public void kaydetHastalik(@RequestBody HastalikDTO hastalikDTO) {
        hastalikService.kaydet(hastalikDTO);
    }

    @DeleteMapping(path = "/{HASTALIK_UUID}")
    public void silHastalik(@PathVariable("HASTALIK_UUID") UUID hastalikUUID) {
        hastalikService.sil(hastalikUUID);
    }

    @PutMapping("/")
    public void guncelleHastalik(@RequestBody HastalikDTO hastalikBilgiDTO) {
        Optional<HastalikDTO> hastalikItem = hastalikService.getirById(hastalikBilgiDTO.getId());
        if (hastalikItem.isPresent()) {
            hastalikService.guncelle(hastalikBilgiDTO);
        } else new RuntimeException("Hastalık Bilgisi Bulunamadi");
    }

    @GetMapping(path = "/listeleHastalik")
    public List<HastalikDTO> listeleHastalik() {
        return hastalikService.tumuListele();
    }
}
