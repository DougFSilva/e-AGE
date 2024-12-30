package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeFuncionario {

	private final FuncionarioRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;
	
	public Funcionario salvar(String ID, MultipartFile imagem) {
		validador.validar(imagem);
		Funcionario funcionario = repository.buscarPeloIDOuThrow(ID);
		String url = imagemService.salvar(imagem, TipoImagem.FUNCIONARIO, GeraNomeDeImagem.pelaPessoa(funcionario));
		funcionario.setFoto(url);
		return repository.salvar(funcionario);
	}
}
