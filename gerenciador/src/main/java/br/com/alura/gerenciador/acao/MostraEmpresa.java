package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Empresa;

public class MostraEmpresa {

	public String executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("mostrando dados da empresa");

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		Banco banco = new Banco();

		Empresa empresa = banco.buscaPorId(id);

		System.out.println(empresa.getNome());

		request.setAttribute("empresa", empresa);

		return "forward:/formAlteraEmpresa.jsp";
	}

}
