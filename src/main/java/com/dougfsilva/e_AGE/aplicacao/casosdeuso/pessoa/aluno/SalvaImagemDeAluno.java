package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeAluno {

	private final AlunoRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;
	
	public AlunoResposta salvar(String ID, MultipartFile imagem) {
		validador.validar(imagem);
		Aluno aluno = repository.buscarPeloIDOuThrow(ID);
		String url = imagemService.salvar(imagem, TipoImagem.ALUNO, GeraNomeDeImagem.pelaPessoa(aluno));
		aluno.setFoto(url);
		return AlunoResposta.deAluno(repository.salvar(aluno));
	}
}
