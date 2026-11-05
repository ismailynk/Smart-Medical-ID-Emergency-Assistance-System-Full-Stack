package com.firat.ozel.hasta.servis.repository;

import com.firat.ozel.hasta.servis.domain.model.Hastalik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HastalikRepository extends JpaRepository<Hastalik, UUID> {
}
