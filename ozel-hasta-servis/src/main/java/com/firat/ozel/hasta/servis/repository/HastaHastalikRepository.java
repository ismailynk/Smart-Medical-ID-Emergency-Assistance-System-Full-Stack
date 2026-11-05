package com.firat.ozel.hasta.servis.repository;

import com.firat.ozel.hasta.servis.domain.model.HastaHastalik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HastaHastalikRepository extends JpaRepository<HastaHastalik, UUID> {

    Boolean existsByHasta_IdAndHastalik_Id(UUID hastaId, UUID hastalikId);

    List<HastaHastalik> getByHasta_IdOrderByTeshisTarihi(UUID hastaId);
}
