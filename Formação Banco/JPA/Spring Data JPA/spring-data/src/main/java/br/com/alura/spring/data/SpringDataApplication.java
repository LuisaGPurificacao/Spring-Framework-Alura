package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.*;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService unidadeService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatoriosService relatoriosService;

	private boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService, CrudUnidadeTrabalhoService unidadeService,
			CrudFuncionarioService funcionarioService, RelatoriosService relatoriosService) {
		this.cargoService = cargoService;
		this.unidadeService = unidadeService;
		this.funcionarioService = funcionarioService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);

		while (system) {
			System.out.println("Qual acao voce quer executar?");
			System.out.println("0- Sair");
			System.out.println("1- Cargo");
			System.out.println("2- Unidade de trabalho");
			System.out.println("3- Funcionario");
			System.out.println("4- Relatorios");

			int action = scan.nextInt();

			if (action == 1) {
				cargoService.inicial(scan);
			} else if (action == 2) {
				unidadeService.inicial(scan);
			} else if (action == 3) {
				funcionarioService.inicial(scan);
			} else if (action == 4) {
				relatoriosService.inicial(scan);
			} else {
				system = false;
			}
		}

	}

}
