package com.firat.ozel.hasta.servis.service;

import com.firat.ozel.hasta.servis.domain.dto.KullaniciDTO;
import com.firat.ozel.hasta.servis.domain.enums.EnumRoles;
import com.firat.ozel.hasta.servis.domain.model.Kullanici;
import com.firat.ozel.hasta.servis.domain.model.KullaniciRole;
import com.firat.ozel.hasta.servis.repository.KullaniciRepository;
import com.firat.ozel.hasta.servis.repository.KullaniciRoleRepository;
import com.firat.ozel.hasta.servis.security.SecurityUtils;
import com.firat.ozel.hasta.servis.yonetici.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class KullaniciService extends BaseService<KullaniciDTO, Kullanici> {

    private final Logger log = LoggerFactory.getLogger(KullaniciService.class);
    private final KullaniciRepository kullaniciRepository;

    private final PasswordEncoder passwordEncoder;

    private final KullaniciRoleRepository kullaniciRoleRepository;

    private final HastaService hastaService;

    protected KullaniciService(KullaniciRepository kullaniciRepository,
                               PasswordEncoder passwordEncoder,
                               KullaniciRoleRepository kullaniciRoleRepository,
                               HastaService hastaService) {
        super(kullaniciRepository, KullaniciDTO.class, Kullanici.class);
        this.kullaniciRepository = kullaniciRepository;
        this.passwordEncoder = passwordEncoder;
        this.kullaniciRoleRepository = kullaniciRoleRepository;
        this.hastaService = hastaService;
    }

    public Optional<Kullanici> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(kullaniciRepository::findOneWithAuthoritiesByKullaniciKodu);
    }

    public Kullanici registerUser(KullaniciDTO kullaniciDTO, String password) {
        kullaniciRepository.findByKullaniciKodu(kullaniciDTO.getKullaniciKodu())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw new RuntimeException("Tc Kaydı Bulundu İşlem Yapılamaz");
                    }
                });
        kullaniciRepository.findOneWithAuthoritiesByMailIgnoreCase(kullaniciDTO.getMail())
                .ifPresent(existingMail -> {
                    throw new RuntimeException("Mail Kaydı Bulundu İşlem Yapılamaz");
                });
        Kullanici newKullanici = new Kullanici();
        String encryptedPassword = passwordEncoder.encode(password);
        newKullanici.setKullaniciKodu(kullaniciDTO.getKullaniciKodu());
        newKullanici.setKullaniciAdi(kullaniciDTO.getKullaniciAdi());
        newKullanici.setSifre(encryptedPassword);
        newKullanici.setMail(kullaniciDTO.getMail());
        newKullanici.setTckimlik(kullaniciDTO.getTckimlik());
        newKullanici.setActive(Boolean.TRUE);

        newKullanici = kullaniciRepository.save(newKullanici);

        KullaniciRole kullaniciRole = new KullaniciRole();
        kullaniciRole.setKullanici(newKullanici.getId());
        kullaniciRole.setRole(EnumRoles.USER);
        kullaniciRoleRepository.save(kullaniciRole);
        hastaService.hastaKaydiOlustur(newKullanici.getTckimlik(), newKullanici.getKullaniciAdi());
        return newKullanici;
    }

    private boolean removeNonActivatedUser(Kullanici existingUser) {
        if (existingUser.getActive()) {
            return false;
        }
        kullaniciRepository.delete(existingUser);
        kullaniciRepository.flush();
        return true;
    }
}
