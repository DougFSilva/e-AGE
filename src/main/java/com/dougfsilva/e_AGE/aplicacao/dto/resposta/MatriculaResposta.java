package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = { "modulo", "aluno" })
@ToString
public class MatriculaResposta {

	private String ID;
	private String registro;
	private ModuloResposta modulo;
	private AlunoResposta aluno;
	private LocalDate dataDaMatricula;
	private MatriculaStatus status;
	
	public MatriculaResposta(Matricula matricula) {
		this.ID = matricula.getID();
		this.registro = matricula.getRegistro();
		this.modulo = ModuloResposta.deModulo(matricula.getModulo());
		this.aluno = AlunoResposta.deAluno(matricula.getAluno());
		this.dataDaMatricula = matricula.getDataDaMatricula();
		this.status = matricula.getStatus();
	}
	
	public static Pagina<MatriculaResposta> dePagina(Pagina<Matricula> matriculas) {
		return new Pagina<MatriculaResposta>(
				matriculas.getConteudo()
				.stream()
				.map(MatriculaResposta::new)
				.collect(Collectors.toList()), 
				matriculas.getNumero(), 
				matriculas.getTamanho(),
				matriculas.getTotalDeElementos(),
				matriculas.getTotalDePaginas(), 
				matriculas.getTemConteudo(), 
				matriculas.getPrimeira(),
				matriculas.getUltima());
	}
	
	 public static MatriculaResposta deMatricula(Matricula matricula) {
	        return new MatriculaResposta(matricula);
	}
}
