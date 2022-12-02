package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scan) {
		while (system) {
			System.out.println("Qual acao deseja executar?");
			System.out.println("0- Sair");
			System.out.println("1- Busca funcionario por nome");
			System.out.println("2- Busca funcionario por nome, data contratacao e salario maior");
			System.out.println("3- Busca funcionario por data contratacao");

			int action = scan.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scan);
				break;

			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scan);
				break;

			case 3:
				buscaFuncionarioDataContratacao(scan);
				break;

			default:
				system = false;
				break;
			}
		}
	}

	private void buscaFuncionarioNome(Scanner scan) {
		System.out.println("Digite o nome:");
		String nome = scan.next();

		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scan) {
		System.out.println("Nome:");
		String nome = scan.next();
		System.out.println("Data de contratacao:");
		String data = scan.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		System.out.println("Salario:");
		Double salario = scan.nextDouble();

		List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario,
				localDate);
		funcionarios.forEach(System.out::println);
	}

	private void buscaFuncionarioDataContratacao(Scanner scan) {
		System.out.println("Data de contratacao:");
		String data = scan.next();
		LocalDate localDate = LocalDate.parse(data, formatter);

		List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
		funcionarios.forEach(System.out::println);
	}

}
