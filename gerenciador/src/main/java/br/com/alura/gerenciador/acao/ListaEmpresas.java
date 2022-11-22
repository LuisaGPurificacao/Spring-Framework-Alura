package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Empresa;

public class ListaEmpresas implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		long antes = System.currentTimeMillis();

		System.out.println("listando empresas");

		Banco banco = new Banco();

		List<Empresa> empresas = banco.getEmpresas();

		request.setAttribute("lista", empresas);

//		long depois = System.currentTimeMillis();
//
//		System.out.println("Tempo de execução: " + (depois - antes));

		return "forward:listaEmpresas.jsp";
	}

}
