package com.firat.ozel.hasta.servis.security;

import com.firat.ozel.hasta.servis.domain.model.Kullanici;
import com.firat.ozel.hasta.servis.domain.model.KullaniciRole;
import com.firat.ozel.hasta.servis.web.rest.vm.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Kullanici user) {
        return new JwtUser(
                user.getId(),
                user.getKullaniciKodu(),
                user.getKullaniciAdi(),
                user.getMail(),
                user.getSifre(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getActive()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<KullaniciRole> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                .collect(Collectors.toList());
    }


}