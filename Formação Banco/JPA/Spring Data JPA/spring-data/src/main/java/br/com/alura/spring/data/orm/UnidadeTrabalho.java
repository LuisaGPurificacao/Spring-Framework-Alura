package br.com.alura.spring.data.orm;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidade_trabalho")
public class UnidadeTrabalho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String endereco;

	@ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
	private List<Funcionario> funcionarios;

	@Override
	public String toString() {
		return "Unidade de trabalho " + id + "\n" + descricao + "\n" + endereco + "\nFuncionários:\n" + funcionarios;
	}

	public UnidadeTrabalho() {
	}

	public UnidadeTrabalho(String descricao, String endereco) {
		this.descricao = descricao;
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
