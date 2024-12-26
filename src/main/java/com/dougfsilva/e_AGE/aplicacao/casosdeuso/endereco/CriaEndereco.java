package com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaEnderecoForm;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.endereco.EnderecoRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComEnderecoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaEndereco {

	private final EnderecoRepository repository;
	private final LogPadrao log;
	
	public Endereco criar(CriaEnderecoForm form) {
		try {
			Endereco endereco = construirEndereco(form);
			Endereco enderecoSalvo = repository.salvar(endereco);
			log.info(String.format("Criado o endereço %s", endereco.toString()));
			return enderecoSalvo;
		} catch (ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar endereço %s : %s", form.toString(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComEnderecoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar endereço %s : %s", form.toString(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComEnderecoException(mensagem, e);
		}
	}
	
	
	private Endereco construirEndereco(CriaEnderecoForm form) {
		return new Endereco(form.pais(), form.estado(), form.codigoPostal(), form.cidade(), form.bairro(), form.rua(), form.numero());
	}
}
