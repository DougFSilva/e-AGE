package com.dougfsilva.e_AGE.infraestrutura.dto;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Cargo;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class FuncionarioResposta extends PessoaResposta {

	private String registro;
	private Cargo cargo;
	
	public FuncionarioResposta(Funcionario funcionario) {
		super(funcionario);
		this.registro = funcionario.getRegistro();
		this.cargo = funcionario.getCargo();
	}
	
	public static Pagina<FuncionarioResposta> dePagina(Pagina<Funcionario> funcionarios) {
		return new Pagina<FuncionarioResposta>(
				funcionarios.getConteudo()
				.stream()
				.map(FuncionarioResposta::new)
				.collect(Collectors.toList()), 
				funcionarios.getNumero(), 
				funcionarios.getTamanho(),
				funcionarios.getTotalDeElementos(),
				funcionarios.getTotalDePaginas(), 
				funcionarios.getTemConteudo(), 
				funcionarios.getPrimeira(),
				funcionarios.getUltima());
	}
	
	public static FuncionarioResposta deFuncionario(Funcionario funcionario) {
	        return new FuncionarioResposta(funcionario);
	}
}
