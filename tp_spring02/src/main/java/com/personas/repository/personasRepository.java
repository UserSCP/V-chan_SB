package com.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.personas.modal.personas;
import java.util.List;

public interface personasRepository extends JpaRepository<personas, Long> {
    List<personas> findByName(String name);
}
