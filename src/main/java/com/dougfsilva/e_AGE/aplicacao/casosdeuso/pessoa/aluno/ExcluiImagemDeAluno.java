package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeAluno {

	private final AlunoRepository repository;
	private final ImagemService imagemService;
	
	public AlunoResposta excluirPeloID(String ID){
		Aluno aluno = repository.buscarPeloIDOuThrow(ID);
		imagemService.remover(TipoImagem.ALUNO, GeraNomeDeImagem.pelaPessoa(aluno));
		aluno.setFoto(imagemService.buscarImagemPadrao(TipoImagem.ALUNO));
		return AlunoResposta.deAluno(repository.salvar(aluno));
	}
}
