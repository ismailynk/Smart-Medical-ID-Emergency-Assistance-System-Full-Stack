package com.firat.ozel.hasta.servis.security;

import com.firat.ozel.hasta.servis.domain.model.Kullanici;
import com.firat.ozel.hasta.servis.domain.model.KullaniciRole;
import com.firat.ozel.hasta.servis.repository.KullaniciRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Service(value = "userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final KullaniciRepository userRepository;

    public DomainUserDetailsService(KullaniciRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User createSpringSecurityUser(String lowercaseLogin, Kullanici user) {
        if (!user.getActive()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map((KullaniciRole t) -> t.getRole().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new User(user.getKullaniciKodu(), user.getSifre(), grantedAuthorities);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        log.debug("Authenticating {}", login);
        Optional<Kullanici> kullanici;
        if (new EmailValidator().isValid(login, null)) {
            kullanici = userRepository.findOneWithAuthoritiesByMailIgnoreCase(login);
            if (!kullanici.isPresent()) {
                new UsernameNotFoundException("User with email " + login + " was not found in the database");
            }
            return createSpringSecurityUser(login, kullanici.get());
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        kullanici = userRepository.findOneWithAuthoritiesByKullaniciKodu(lowercaseLogin);
        if (!kullanici.isPresent()) {
            new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
        }
        return createSpringSecurityUser(lowercaseLogin, kullanici.get());
    }
}
