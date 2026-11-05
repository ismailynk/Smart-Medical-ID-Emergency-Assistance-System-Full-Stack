package com.firat.ozel.hasta.servis.repository;

import com.firat.ozel.hasta.servis.domain.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, UUID> {
    Optional<Kullanici> findOneWithAuthoritiesByMailIgnoreCase(String email);

    Optional<Kullanici> findOneWithAuthoritiesByKullaniciKodu(String login);

    Optional<Kullanici> findByKullaniciKodu(String kullaniciKodu);

    @Query(value = "select t.id" +
            " from Kullanici t" +
            " where t.kullaniciKodu = :kullaniciKodu")
    UUID getirBykullaniciId(@Param("kullaniciKodu") String kullaniciKodu);
}
