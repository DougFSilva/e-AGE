package com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaEnderecoForm;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.endereco.EnderecoRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComEnderecoException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaEndereco {

	private final EnderecoRepository repository;
	private final LogPadrao log;
	
	public Endereco editar(EditaEnderecoForm form) {
		try {
			Endereco endereco = repository.buscarPeloIDOuThrow(form.ID());
			Endereco enderecoAtualizado = atualizarDados(form, endereco);
			Endereco enderecoSalvo = repository.salvar(enderecoAtualizado);
			log.info(String.format("Editado endereço %s", enderecoSalvo.toString()));
			return enderecoSalvo;
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar endereço com ID %s : %s", form.ID(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComEnderecoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar endereço com ID %s : %s", form.ID(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComEnderecoException(mensagem, e);
		}
	}
	
	private Endereco atualizarDados(EditaEnderecoForm form, Endereco endereco) {
	    if (form.pais() != null && !form.pais().isBlank()) {
	        endereco.setPais(form.pais());
	    }
	    if (form.estado() != null && !form.estado().isBlank()) {
	        endereco.setEstado(form.estado());
	    }
	    if (form.codigoPostal() != null && !form.codigoPostal().isBlank()) {
	        endereco.setCodigoPostal(form.codigoPostal());
	    }
	    if (form.cidade() != null && !form.cidade().isBlank()) {
	        endereco.setCidade(form.cidade());
	    }
	    if (form.bairro() != null && !form.bairro().isBlank()) {
	        endereco.setBairro(form.bairro());
	    }
	    if (form.rua() != null && !form.rua().isBlank()) {
	        endereco.setRua(form.rua());
	    }
	    if (form.numero() != null && !form.numero().isBlank()) {
	        endereco.setNumero(form.numero());
	    }
	    return endereco;
	}

	
}
