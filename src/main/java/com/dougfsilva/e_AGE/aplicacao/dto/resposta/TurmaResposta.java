package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "codigo", "curso" })
@ToString
public class TurmaResposta {

	private String ID;
	private String codigo;
	private String curso;
	private Boolean aberta;
	private LocalDate dataDeAbertura;
	private LocalDate dataDeFechamento;
	
	public TurmaResposta(Turma turma) {
		ID = turma.getID();
		this.codigo = turma.getCodigo();
		this.curso = turma.getCurso().getTitulo();
		this.aberta = turma.getAberta();
		this.dataDeAbertura = turma.getDataDeAbertura();
		this.dataDeFechamento = turma.getDataDeFechamento();
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