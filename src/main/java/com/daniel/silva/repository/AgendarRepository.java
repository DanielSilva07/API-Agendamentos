package com.daniel.silva.repository;

import com.daniel.silva.domain.model.AgendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendarRepository extends JpaRepository<AgendarModel,Long> {

     Optional<AgendarModel>findByNome(String nome);

}
