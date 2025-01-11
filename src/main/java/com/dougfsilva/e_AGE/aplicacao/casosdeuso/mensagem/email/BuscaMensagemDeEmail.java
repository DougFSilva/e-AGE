package com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem.email;

import java.time.LocalDateTime;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmail;
import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmailRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaMensagemDeEmail {

	private final MensagemDeEmailRepository repository;

	public MensagemDeEmail buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public List<MensagemDeEmail> buscarPeloDestinatario(String email) {
		return repository.buscarPeloDestinatario(new Email(email));
	}

	public Pagina<MensagemDeEmail> buscarPeloSucessoDeEnvio(boolean enviadoComSucesso, RequisicaoDePagina requisicao) {
		return repository.buscarPeloSucessoDeEnvio(enviadoComSucesso, requisicao);
	}
	
	public Pagina<MensagemDeEmail> buscarPelaDataDeEnvio(LocalDateTime min, LocalDateTime max, RequisicaoDePagina requisicao) {
		return repository.buscarPelaDataDeEnvio(min, max, requisicao);
	}

	public Pagina<MensagemDeEmail> buscarTodas(RequisicaoDePagina requisicao) {
		return repository.buscarTodas(requisicao);
	}
}
