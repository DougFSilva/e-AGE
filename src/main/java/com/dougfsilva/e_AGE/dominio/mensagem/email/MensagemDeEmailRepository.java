package com.dougfsilva.e_AGE.dominio.mensagem.email;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface MensagemDeEmailRepository {
	
	MensagemDeEmail salvar(MensagemDeEmail mensagem);
	
	void excluir(MensagemDeEmail mensagem);
	
	Optional<MensagemDeEmail> buscarPeloID(String ID);
	
	default MensagemDeEmail buscarPeloIDOuThrow(String ID) {
	    return buscarPeloID(ID)
	        .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Mensagem de Email com ID %s não encontrada", ID)));
	}
	
	List<MensagemDeEmail> buscarPeloDestinatario(Email email);
	
	Pagina<MensagemDeEmail> buscarPeloSucessoDeEnvio(boolean enviadoComSucesso, RequisicaoDePagina requisicao);
	
	Pagina<MensagemDeEmail> buscarPelaDataDeEnvio(LocalDateTime min, LocalDateTime max, RequisicaoDePagina requisicao);
	
	Pagina<MensagemDeEmail> buscarTodas(RequisicaoDePagina requisicao);
}
