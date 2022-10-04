package me.dio.ifoodweek.sacola.api.repository;

import me.dio.ifoodweek.sacola.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long > {
}
