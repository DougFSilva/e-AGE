package com.dougfsilva.e_AGE.infraestrutura.dto;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = { "codigo", "turma" })
@ToString
public class ModuloResposta {

	private String ID;
	private String codigo;
	private Turma turma;
	private Boolean aberto;
	private LocalDate dataDeAbertura;
	private LocalDate dataDeFechamento;
	private Integer numeroDoModulo;
	private Boolean moduloFinal;
	
	public ModuloResposta(Modulo modulo) {
		this.ID = modulo.getID();
		this.codigo = modulo.getCodigo();
		this.turma = modulo.getTurma();
		this.aberto = modulo.getAberto();
		this.dataDeAbertura = modulo.getDataDeAbertura();
		this.dataDeFechamento = modulo.getDataDeFechamento();
		this.numeroDoModulo = modulo.getNumeroDoModulo();
		this.moduloFinal = modulo.getModuloFinal();
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
