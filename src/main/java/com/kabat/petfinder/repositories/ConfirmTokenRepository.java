package com.kabat.petfinder.repositories;

import com.kabat.petfinder.entities.ConfirmToken;
import com.kabat.petfinder.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, UUID> {
}
