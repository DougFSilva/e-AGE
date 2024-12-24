package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.Modalidade;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = { "titulo" })
public class CursoResposta {

	private String ID;
	private Modalidade modalidade;
	private String areaTecnologica;
	private String titulo;
	private String descricao;
	private Boolean aberto;
	private String imagem;
	
	public CursoResposta(Curso curso) {
		this.ID = curso.getID();
		this.modalidade = curso.getModalidade();
		this.areaTecnologica = curso.getAreaTecnologica().getTitulo();
		this.titulo = curso.getTitulo();
		this.descricao = curso.getDescricao();
		this.aberto = curso.getAberto();
		this.imagem = curso.getImagem();
	}
	
	public static Pagina<CursoResposta> dePage(Pagina<Curso> courses) {
		return new Pagina<CursoResposta>(
				courses.getConteudo()
				.stream()
				.map(CursoResposta::new)
				.collect(Collectors.toList()), 
				courses.getNumero(), 
				courses.getTamanho(),
				courses.getTotalDeElementos(),
				courses.getTotalDePaginas(), 
				courses.getTemConteudo(), 
				courses.getPrimeira(),
				courses.getUltima());
	}
	
	 public static CursoResposta deCurso(Curso curso) {
	        return new CursoResposta(curso);
	    }
	
}
