package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository repository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudFuncionarioService(FuncionarioRepository repository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.repository = repository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scan) {
		while (system) {
			System.out.println("Qual acao de funcionario deseja executar?");
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
		System.out.println("Digite o nome:");
		String nome = scan.next();
		System.out.println("Digite o cpf:");
		String cpf = scan.next();
		System.out.println("Digite o salario:");
		Double salario = scan.nextDouble();
		System.out.println("Digite a data de contratacao:");
		String dataContratacao = scan.next();
		System.out.println("Digite o ID do cargo:");
		Integer cargoId = scan.nextInt();

		List<UnidadeTrabalho> unidades = unidade(scan);

		Funcionario funcionario = new Funcionario(nome, cpf, salario, LocalDate.parse(dataContratacao, formatter));

		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());

		funcionario.setUnidadeTrabalhos(unidades);

		repository.save(funcionario);
		System.out.println("Salvo.");
	}

	private List<UnidadeTrabalho> unidade(Scanner scan) {
		boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();

		while (isTrue) {
			System.out.println("Digite o ID da unidade: (para sair digite 0)");
			Integer unidadeId = scan.nextInt();

			if (unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}

		return unidades;
	}

	private void atualizar(Scanner scan) {
		System.out.println("Digite o ID:");
		Integer id = scan.nextInt();

		System.out.println("Digite o nome:");
		String nome = scan.next();
		System.out.println("Digite o cpf:");
		String cpf = scan.next();
		System.out.println("Digite o salario:");
		Double salario = scan.nextDouble();
		System.out.println("Digite a data de contratacao:");
		String dataContratacao = scan.next();
		System.out.println("Digite o ID do cargo:");
		Integer cargoId = scan.nextInt();

		Funcionario funcionario = new Funcionario(nome, cpf, salario, LocalDate.parse(dataContratacao, formatter));

		funcionario.setId(id);

		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());

		repository.save(funcionario);
		System.out.println("Atualizado.");
	}

	private void visualizar() {
		Iterable<Funcionario> funcionarios = repository.findAll();
		funcionarios.forEach(f -> System.out.println(f));
	}

	private void deletar(Scanner scan) {
		System.out.println("Digite o ID:");
		Integer id = scan.nextInt();
		repository.deleteById(id);
		System.out.println("Deletado.");
	}

}
