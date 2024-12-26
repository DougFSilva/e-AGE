package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Responsavel;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class AlunoResposta extends PessoaResposta {

	private Responsavel responsavel;
	private Empresa empresa;
	
	public AlunoResposta(Aluno aluno) {
		super(aluno);
		this.responsavel = aluno.getResponsavel();
		this.empresa = aluno.getEmpresa();
	}
	
	public static Pagina<AlunoResposta> dePagina(Pagina<Aluno> alunos) {
		return new Pagina<AlunoResposta>(
				alunos.getConteudo()
				.stream()
				.map(AlunoResposta::new)
				.collect(Collectors.toList()), 
				alunos.getNumero(), 
				alunos.getTamanho(),
				alunos.getTotalDeElementos(),
				alunos.getTotalDePaginas(), 
				alunos.getTemConteudo(), 
				alunos.getPrimeira(),
				alunos.getUltima());
	}
	
	public static AlunoResposta deAluno(Aluno aluno) {
	        return new AlunoResposta(aluno);
	}
}
