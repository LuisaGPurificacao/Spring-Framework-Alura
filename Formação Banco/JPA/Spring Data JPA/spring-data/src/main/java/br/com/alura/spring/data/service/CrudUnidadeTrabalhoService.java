package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private boolean system = true;
	private final UnidadeTrabalhoRepository repository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
		this.repository = repository;
	}

	public void inicial(Scanner scan) {
		while (system) {
			System.out.println("Qual acao de unidade de trabalho deseja executar?");
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
		System.out.println("Digite o nome da unidade");
		String nome = scan.next();
		System.out.println("Digite o endereco:");
		String endereco = scan.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(nome, endereco);

		repository.save(unidadeTrabalho);
		System.out.println("Salvo.");
	}

	private void atualizar(Scanner scan) {
		System.out.println("ID:");
		Integer id = scan.nextInt();
		System.out.println("Digite o nome da unidade");
		String nome = scan.next();
		System.out.println("Digite o endereco:");
		String endereco = scan.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(nome, endereco);
		unidadeTrabalho.setId(id);

		repository.save(unidadeTrabalho);
		System.out.println("Atualizado.");
	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = repository.findAll();
		unidades.forEach(u -> System.out.println(u));
	}

	private void deletar(Scanner scan) {
		System.out.println("ID:");
		Integer id = scan.nextInt();
		repository.deleteById(id);
		System.out.println("Deletado.");
	}

}
