package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = { "matricula" })
@ToString
public class EvasaoResposta {

	private String ID;
	private MatriculaResposta matricula;
	private String motivo;
	private LocalDate data;
	
	public EvasaoResposta(Evasao evasao) {
		this.ID = evasao.getID();
		this.matricula = MatriculaResposta.deMatricula(evasao.getMatricula());
		this.motivo = evasao.getMotivo();
		this.data = evasao.getData();
	}
	
	public static Pagina<EvasaoResposta> dePagina(Pagina<Evasao> evasoes) {
		return new Pagina<EvasaoResposta>(
				evasoes.getConteudo()
				.stream()
				.map(EvasaoResposta::new)
				.collect(Collectors.toList()), 
				evasoes.getNumero(), 
				evasoes.getTamanho(),
				evasoes.getTotalDeElementos(),
				evasoes.getTotalDePaginas(), 
				evasoes.getTemConteudo(), 
				evasoes.getPrimeira(),
				evasoes.getUltima());
	}
	
	 public static EvasaoResposta deEvasao(Evasao evasao) {
	        return new EvasaoResposta(evasao);
	}
}
