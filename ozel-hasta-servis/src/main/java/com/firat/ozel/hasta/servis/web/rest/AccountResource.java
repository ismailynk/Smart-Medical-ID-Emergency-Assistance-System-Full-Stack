package com.firat.ozel.hasta.servis.web.rest;

import com.firat.ozel.hasta.servis.domain.dto.KullaniciDTO;
import com.firat.ozel.hasta.servis.repository.KullaniciRepository;
import com.firat.ozel.hasta.servis.service.KullaniciService;
import com.firat.ozel.hasta.servis.web.rest.vm.ManagedUserVM;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final KullaniciRepository userRepository;

    private final KullaniciService userService;

    public AccountResource(KullaniciRepository userRepository, KullaniciService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/authenticate")
    public ResponseEntity<String> isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        log.debug(request.getRemoteUser());
        return ResponseEntity.ok(request.getRemoteUser());
    }

    @GetMapping("/account")
    public KullaniciDTO getAccount() {
        KullaniciDTO kullaniciDTO = userService.getModelMapper().getDTO(userService.getUserWithAuthorities().get());
        if (kullaniciDTO == null) {
            throw new RuntimeException("User could not be found");
        }
        return kullaniciDTO;
    }

    @PostMapping("/register")
    public void registerAccount(@RequestBody ManagedUserVM managedUserVM) {
        if (!checkPassWordLength(managedUserVM.getPassword())) {
            throw new RuntimeException("Hatalı Şifre");
        }
        managedUserVM.setKullaniciKodu(managedUserVM.getTckimlik());
        userService.registerUser(managedUserVM, managedUserVM.getPassword());
    }

    private static boolean checkPassWordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
