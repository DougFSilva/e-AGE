package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.CriaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario.CriaUsuario;
import com.dougfsilva.e_AGE.aplicacao.dto.CriaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.CriaEMatriculaAlunoForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
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
public class CriaEMatriculaAluno {

	private final MatriculaRepository repository;
	private final ModuloRepository moduloRepository;
	private final AlunoRepository alunoRepository;
	private final EmpresaRepository  empresaRepository;
	private final ImagemService imagemService;
	private final CriaEndereco criaEndereco;
	private final CriaUsuario criaUsuario;
	private final ValidaMatricula validadorDeMatricula;
	private final ValidaPessoa validadorDePessoa;
	
	public List<Matricula> matricular(CriaEMatriculaAlunoForm form){
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		List<Aluno> alunos = new ArrayList<>();
		form.alunos().forEach(alunoForm -> {
			alunoRepository.buscarPeloCPF(alunoForm.CPF()).ifPresentOrElse(aluno -> {
				alunos.add(aluno);
			}, () -> {
				alunos.add(criarAluno(alunoForm));
			});
		});
		
	}
	
	public Aluno criarAluno(CriaAlunoForm form) {
		validadorDePessoa.validarUnicoCPF(form.CPF());
		Aluno aluno = construirAluno(form);
		Usuario usuario = criaUsuario.criarUsuarioDefaultParaPessoa(aluno, Arrays.asList(TipoPerfil.ALUNO));
		aluno.setUsuario(usuario);
		return alunoRepository.salvar(aluno);
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
