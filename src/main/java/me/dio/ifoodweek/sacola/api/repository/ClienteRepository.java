package me.dio.ifoodweek.sacola.api.repository;

import me.dio.ifoodweek.sacola.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
