package br.com.alura.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioJpaRepository extends JpaRepository<Funcionario, Integer> {

}
