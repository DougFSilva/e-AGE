package com.dougfsilva.e_AGE.dominio.mensagem.celular;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface MensagemDeCelularRepotory {

	MensagemDeCelular salvar(MensagemDeCelular mensagem);

	void excluir(MensagemDeCelular mensagem);

	Optional<MensagemDeCelular> buscarPeloID(String ID);

	default MensagemDeCelular buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Mensagem de celular com ID %s não encontrada", ID)));
	}

	List<MensagemDeCelular> buscarPeloDestinatario(String telefone);

	Pagina<MensagemDeCelular> buscarPeloSucessoDeEnvio(boolean enviadoComSucesso, RequisicaoDePagina requisicao);
}
