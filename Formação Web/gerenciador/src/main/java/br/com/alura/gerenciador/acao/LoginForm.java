package br.com.alura.gerenciador.acao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginForm implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {
		return "forward:formLogin.jsp";
	}

}
