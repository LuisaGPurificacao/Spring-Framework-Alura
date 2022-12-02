package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private boolean system = true;
	private final CargoRepository repository;

	public CrudCargoService(CargoRepository repository) {
		this.repository = repository;
	}

	public void inicial(Scanner scan) {
		while (system) {
			System.out.println("Qual acao de cargo deseja executar?");
			System.out.println("0- Sair");
			System.out.println("1- Salvar");
			System.out.println("2- Atualizar");
			System.out.println("3- Visualizar");
			System.out.println("4- Deletar");

			int action = scan.nextInt();

			switch (action) {
			case 1:
				salvar(scan);
				break;

			case 2:
				atualizar(scan);
				break;

			case 3:
				visualizar();
				break;

			case 4:
				deletar(scan);
				break;

			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scan) {
		System.out.println("Descricao do cargo:");
		String descricao = scan.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		repository.save(cargo);
		System.out.println("Salvo.");
	}

	private void atualizar(Scanner scan) {
		System.out.println("ID do cargo:");
		Integer id = scan.nextInt();
		System.out.println("Descricao do cargo");
		String descricao = scan.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		repository.save(cargo);
		System.out.println("Atualizado.");
	}

	private void visualizar() {
		Iterable<Cargo> cargos = repository.findAll();
		cargos.forEach(c -> System.out.println(c));
	}

	private void deletar(Scanner scan) {
		System.out.println("ID do cargo:");
		Integer id = scan.nextInt();

		repository.deleteById(id);
		System.out.println("Deletado.");
	}

}
