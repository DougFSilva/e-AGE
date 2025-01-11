package com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem.celular;

import java.time.LocalDateTime;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.mensagem.celular.MensagemDeCelular;
import com.dougfsilva.e_AGE.dominio.mensagem.celular.MensagemDeCelularRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaMensagemDeCelular {

	private final MensagemDeCelularRepository repository;

	public MensagemDeCelular buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public List<MensagemDeCelular> buscarPeloDestinatario(String telefone) {
		return repository.buscarPeloDestinatario(telefone);
	}

	public Pagina<MensagemDeCelular> buscarPeloSucessoDeEnvio(boolean enviadoComSucesso, RequisicaoDePagina requisicao) {
		return repository.buscarPeloSucessoDeEnvio(enviadoComSucesso, requisicao);
	}

	public Pagina<MensagemDeCelular> buscarPelaDataDeEnvio(LocalDateTime min, LocalDateTime max, RequisicaoDePagina requisicao) {
		return repository.buscarPelaDataDeEnvio(min, max, requisicao);
	}

	public Pagina<MensagemDeCelular> buscarTodas(RequisicaoDePagina requisicao) {
		return repository.buscarTodas(requisicao);
	}
}
