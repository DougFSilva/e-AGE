	package com.dougfsilva.e_AGE.aplicacao.formulario;
	
	import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.TipoOcorrencia;
	
	public record AbreOcorrenciaForm(
	
			String relatorID, String alunoID, TipoOcorrencia tipo, Boolean encaminhada, Boolean restrita,
			String descricao) {
		
		 public AbreOcorrenciaForm {
		        if (relatorID == null || relatorID.isBlank()) {
		            throw new ErroDeValidacaoDeCamposException("O campo relatorID não pode ser nulo ou vazio");
		        }
		        if (alunoID == null || alunoID.isBlank()) {
		            throw new ErroDeValidacaoDeCamposException("O campo alunoID não pode ser nulo ou vazio");
		        }
		        if (tipo == null) {
		            throw new ErroDeValidacaoDeCamposException("O campo tipo não pode ser nulo");
		        }
		        if (descricao == null || descricao.isBlank()) {
		            throw new ErroDeValidacaoDeCamposException("O campo descrição não pode ser nulo ou vazio");
		        }
		        if (encaminhada == null) {
		            throw new ErroDeValidacaoDeCamposException("O campo encaminhada não pode ser nulo ou vazio");
		        }
		        if (restrita == null) {
		            throw new ErroDeValidacaoDeCamposException("O campo restrita não pode ser nulo ou vazio");
		        }
		    }
		 
	
	}
