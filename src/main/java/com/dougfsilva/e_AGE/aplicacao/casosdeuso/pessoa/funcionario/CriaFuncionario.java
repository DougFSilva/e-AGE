package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import java.util.Arrays;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.CriaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario.CriaUsuario;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaFuncionarioForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.FuncionarioResposta;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaFuncionario {

	private final FuncionarioRepository repository;
	private final ImagemService imagemService;
	private final CriaEndereco criaEndereco;
	private final CriaUsuario criaUsuario;
	private final ValidaPessoa validador;
	
	public FuncionarioResposta criar(CriaFuncionarioForm form) {
		validador.validarUnicoCPF(form.CPF());
		Funcionario funcionario = construirFuncionario(form);
		Usuario usuario = criaUsuario.criarUsuarioDefaultParaPessoa(funcionario, Arrays.asList(TipoPerfil.FUNCIONARIO));
		funcionario.setUsuario(usuario);
		Funcionario funcionarioSalvo = repository.salvar(funcionario);
		return FuncionarioResposta.deFuncionario(funcionarioSalvo);
	}
	
	private Funcionario construirFuncionario(CriaFuncionarioForm form) {
		Endereco endereco = criaEndereco.criar(form.endereco());
		Funcionario funcionario = new Funcionario(
				form.nome(), 
				form.sexo(), 
				form.CPF(), 
				form.telefone(), 
				new Email(form.email()), 
				form.dataDeNascimento(), 
				endereco, 
				imagemService.buscarImagemPadrao(TipoImagem.FUNCIONARIO),
				form.registro(),
				form.cargo());
		return funcionario;
	}
}
