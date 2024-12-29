package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import java.util.Arrays;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.CriaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario.CriaUsuario;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaAluno {

	private final AlunoRepository repository;
	private final EmpresaRepository  empresaRepository;
	private final ImagemService imagemService;
	private final CriaEndereco criaEndereco;
	private final CriaUsuario criaUsuario;
	private final ValidaPessoa validador;
	
	public AlunoResposta criar(CriaAlunoForm form) {
		validador.validarUnicoCPF(form.CPF());
		Aluno aluno = construirAluno(form);
		Usuario usuario = criaUsuario.criarUsuarioDefaultParaPessoa(aluno, Arrays.asList(TipoPerfil.ALUNO));
		aluno.setUsuario(usuario);
		Aluno alunoSalvo = repository.salvar(aluno);
		return AlunoResposta.deAluno(alunoSalvo);
	}
	
	private Aluno construirAluno(CriaAlunoForm form) {
		Endereco endereco = criaEndereco.criar(form.endereco());
		Aluno aluno = new Aluno(
				form.nome(), 
				form.sexo(), 
				form.CPF(), 
				form.telefone(), 
				new Email(form.email()), 
				form.dataDeNascimento(), 
				endereco, 
				imagemService.buscarImagemPadrao(TipoImagem.ALUNO));
		if (form.empresaID() != null && !form.empresaID().isBlank()) {
			Empresa empresa = empresaRepository.buscarPeloIDOuThrow(form.empresaID());
			aluno.setEmpresa(empresa);
		}
		if (form.responsavel() != null) {
			aluno.setResponsavel(form.responsavel());
		}
		return aluno;
	}
}
