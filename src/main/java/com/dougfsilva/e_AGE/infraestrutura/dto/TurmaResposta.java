package com.dougfsilva.e_AGE.infraestrutura.dto;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = { "codigo", "curso" })
@ToString
public class TurmaResposta {

	private String ID;
	private String codigo;
	private CursoResposta curso;
	private Boolean aberta;
	
	public TurmaResposta(Turma turma) {
		this.ID = turma.getID();
		this.codigo = turma.getCodigo();
		this.curso = CursoResposta.deCurso(turma.getCurso());
		this.aberta = turma.getAberta();
	}
	
	public static Pagina<TurmaResposta> dePagina(Pagina<Turma> turmas) {
		return new Pagina<TurmaResposta>(
				turmas.getConteudo()
				.stream()
				.map(TurmaResposta::new)
				.collect(Collectors.toList()), 
				turmas.getNumero(), 
				turmas.getTamanho(),
				turmas.getTotalDeElementos(),
				turmas.getTotalDePaginas(), 
				turmas.getTemConteudo(), 
				turmas.getPrimeira(),
				turmas.getUltima());
	}
	
	 public static TurmaResposta deTurma(Turma turma) {
	        return new TurmaResposta(turma);
	}
	
}
