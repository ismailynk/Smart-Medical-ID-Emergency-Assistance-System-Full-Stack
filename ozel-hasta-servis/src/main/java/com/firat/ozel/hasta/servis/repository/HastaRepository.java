package com.firat.ozel.hasta.servis.repository;

import com.firat.ozel.hasta.servis.domain.model.Hasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HastaRepository extends JpaRepository<Hasta, UUID> {

    Boolean existsByTckimlik(String tckimlik);

    @Query(value = "select t" +
            " from Hasta t" +
            " where t.tckimlik = :tckimlik")
    Hasta getirByHasta(@Param("tckimlik") String tckimlik);
}
