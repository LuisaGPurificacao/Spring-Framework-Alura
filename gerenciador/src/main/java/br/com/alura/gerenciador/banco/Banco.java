package br.com.alura.gerenciador.banco;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.gerenciador.model.Empresa;

public class Banco {

	private static List<Empresa> empresas = new ArrayList<>();

	static {
		Empresa empresa = new Empresa();
		empresa.setNome("Google");
		Empresa empresa2 = new Empresa();
		empresa2.setNome("Alura");
		empresas.add(empresa);
		empresas.add(empresa2);
	}

	public void adicionar(Empresa empresa) {
		Banco.empresas.add(empresa);
	}

	public List<Empresa> getEmpresas() {
		return Banco.empresas;
	}

}
