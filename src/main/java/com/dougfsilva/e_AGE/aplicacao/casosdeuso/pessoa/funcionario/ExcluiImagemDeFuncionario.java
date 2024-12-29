package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.FuncionarioResposta;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeFuncionario {

	private final FuncionarioRepository repository;
	private final ImagemService imagemService;
	
	public FuncionarioResposta excluirPeloID(String ID){
		Funcionario funcionario = repository.buscarPeloIDOuThrow(ID);
		imagemService.remover(TipoImagem.FUNCIONARIO, GeraNomeDeImagem.pelaPessoa(funcionario));
		funcionario.setFoto(imagemService.buscarImagemPadrao(TipoImagem.ALUNO));
		return FuncionarioResposta.deFuncionario(repository.salvar(funcionario));
	}
}
