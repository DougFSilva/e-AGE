package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.EditaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.formulario.EditaAlunoForm;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Responsavel;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaAluno {

	private final AlunoRepository repository;
	private final EmpresaRepository empresaRepository;
	private final UsuarioRepository usuarioRepository;
	private final EditaEndereco editaEndereco;
	private final ValidaPessoa validador;

	public Aluno editar(EditaAlunoForm form) {
		Aluno aluno = editarDados(form);
		return repository.salvar(aluno);
	}

	private Aluno editarDados(EditaAlunoForm form) {
		Aluno aluno = repository.buscarPeloIDOuThrow(form.ID());
		if (form.nome() != null && !form.nome().isBlank()) {
			aluno.setNome(form.nome());
		}
		if (form.sexo() != null) {
			aluno.setSexo(form.sexo());
		}
		if (form.CPF() != null && !form.CPF().isBlank() && !form.CPF().equalsIgnoreCase(aluno.getCPF())) {
			validador.validarUnicoCPF(form.CPF());
			aluno.setCPF(form.CPF());
			aluno.getUsuario().setNomeDeUsuario(form.CPF());
			usuarioRepository.salvar(aluno.getUsuario());
		}
		if (form.telefone() != null && !form.telefone().isBlank()) {
			aluno.setTelefone(form.telefone());
		}
		if (form.email() != null && !form.email().isBlank()) {
			aluno.setEmail(new Email(form.email()));
		}
		if (form.dataDeNascimento() != null) {
			aluno.setDataDeNascimento(form.dataDeNascimento());
		}
		if (form.responsavel() != null) {
			Responsavel responsavel = editarResponsavel(form.responsavel(), aluno.getResponsavel());
			aluno.setResponsavel(responsavel);
		}
		if (form.endereco() != null) {
			Endereco endereco = editaEndereco.editar(form.endereco(), aluno.getEndereco());
			aluno.setEndereco(endereco);
		}
		if (form.empresaID() != null && !form.empresaID().isBlank()) {
			Empresa empresa = empresaRepository.buscarPeloIDOuThrow(form.empresaID());
			aluno.setEmpresa(empresa);
		}
		return aluno;
	}

	private Responsavel editarResponsavel(Responsavel form, Responsavel responsavel) {
		if (form.getNome() != null && !form.getNome().isBlank()) {
			responsavel.setNome(form.getNome());
		}
		if (form.getEmail() != null) {
			responsavel.setEmail(form.getEmail());
		}
		if (form.getTelefone() != null && !form.getTelefone().isBlank()) {
			responsavel.setTelefone(form.getTelefone());
		}
		return responsavel;
	}
}
