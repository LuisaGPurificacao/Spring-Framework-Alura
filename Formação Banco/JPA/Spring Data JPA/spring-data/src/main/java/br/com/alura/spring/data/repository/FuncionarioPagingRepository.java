package br.com.alura.spring.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioPagingRepository extends PagingAndSortingRepository<Funcionario, Integer> {

}
