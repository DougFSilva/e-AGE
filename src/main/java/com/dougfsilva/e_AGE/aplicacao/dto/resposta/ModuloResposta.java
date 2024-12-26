package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = { "codigo", "curso" })
@ToString
public class ModuloResposta {

	private String ID;
	private String codigo;
	private String curso;
	
	public ModuloResposta(Modulo modulo) {
		this.ID = modulo.getID();
		this.codigo = modulo.getCodigo();
		this.curso = modulo.getCurso().getTitulo();
	}
	
	public static Pagina<ModuloResposta> dePagina(Pagina<Modulo> modulos) {
		return new Pagina<ModuloResposta>(
				modulos.getConteudo()
				.stream()
				.map(ModuloResposta::new)
				.collect(Collectors.toList()), 
				modulos.getNumero(), 
				modulos.getTamanho(),
				modulos.getTotalDeElementos(),
				modulos.getTotalDePaginas(), 
				modulos.getTemConteudo(), 
				modulos.getPrimeira(),
				modulos.getUltima());
	}
	
	 public static ModuloResposta deModulo(Modulo modulo) {
	        return new ModuloResposta(modulo);
	}
}
