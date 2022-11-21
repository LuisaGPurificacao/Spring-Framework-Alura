package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Empresa;

public class ListaEmpresas {

	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("listando empresas");

		Banco banco = new Banco();

		List<Empresa> empresas = banco.getEmpresas();

		RequestDispatcher rd = request.getRequestDispatcher("listaEmpresas.jsp");
		request.setAttribute("lista", empresas);
		rd.forward(request, response);
	}

}
