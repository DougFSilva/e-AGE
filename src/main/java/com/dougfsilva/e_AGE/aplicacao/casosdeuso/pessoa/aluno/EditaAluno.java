package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.EditaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAlunoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDePessoaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
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
	private final LogPadrao log;

	public AlunoResposta editar(EditaAlunoForm form) {
		try {
			Aluno aluno = repository.buscarPeloIDOuThrow(form.ID());
			Aluno alunoEditado = editarDados(form, aluno);
			Aluno alunoSalvo = repository.salvar(alunoEditado);
			log.info(String.format("Editado aluno %s com ID %s", alunoSalvo.getNome(), alunoSalvo.getID()));
			return AlunoResposta.deAluno(alunoSalvo);
		} catch (ErroDeValidacaoDePessoaException | ObjetoNaoEncontradoException | ErroDeOperacaoComUsuarioException e) {
			String mensagem = String.format("Erro ao editar aluno com ID %s : %s", form.ID(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar aluno com ID %s : %s", form.ID(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		}
	}

	private Aluno editarDados(EditaAlunoForm form, Aluno aluno) {
		if (form.nome() != null && !form.nome().isBlank()) {
			aluno.setNome(form.nome());
		}
		if (form.sexo() != null) {
			aluno.setSexo(form.sexo());
		}
		if (form.RG() != null && !form.RG().isBlank() && !form.RG().equalsIgnoreCase(aluno.getRG())) {
			validador.validarUnicoRG(form.RG());
			aluno.setRG(form.RG());
		}
		if (form.telefone() != null && !form.telefone().isBlank()) {
			aluno.setTelefone(form.telefone());
		}
		if (form.email() != null && !form.email().isBlank()) {
			aluno.setEmail(new Email(form.email()));
			aluno.getUsuario().setNomeDeUsuario(form.email());
			usuarioRepository.salvar(aluno.getUsuario());
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
