package me.dio.ifoodweek.sacola.api.repository;


import me.dio.ifoodweek.sacola.api.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {
}
