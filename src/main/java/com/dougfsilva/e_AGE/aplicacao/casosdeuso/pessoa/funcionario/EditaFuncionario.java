package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.EditaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaFuncionarioForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.FuncionarioResposta;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComFuncionarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDePessoaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaFuncionario {

	private final FuncionarioRepository repository;
	private final UsuarioRepository usuarioRepository;
	private final EditaEndereco editaEndereco;
	private final ValidaPessoa validador;
	private final LogPadrao log;
	
	public FuncionarioResposta editar(EditaFuncionarioForm form) {
		try {
			Funcionario funcionario = repository.buscarPeloIDOuThrow(form.ID());
			Funcionario funcionarioEditado = editarDados(form, funcionario);
			Funcionario funcionarioSalvo = repository.salvar(funcionarioEditado);
			log.info(String.format("Editado funcionário %s com ID %s", funcionarioSalvo.getNome(), funcionarioSalvo.getID()));
			return FuncionarioResposta.deFuncionario(funcionarioSalvo);
		} catch (ErroDeValidacaoDePessoaException | ObjetoNaoEncontradoException | ErroDeOperacaoComUsuarioException e) {
			String mensagem = String.format("Erro ao editar funcionário com ID %s : %s", form.ID(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar funcionário com ID %s : %s", form.ID(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		}
		
	}
	
	private Funcionario editarDados(EditaFuncionarioForm form, Funcionario funcionario) {
		if (form.nome() != null && !form.nome().isBlank()) {
			funcionario.setNome(form.nome());
		}
		if (form.sexo() != null) {
			funcionario.setSexo(form.sexo());
		}
		if (form.CPF() != null && !form.CPF().isBlank() && !form.CPF().equalsIgnoreCase(funcionario.getCPF())) {
			validador.validarUnicoCPF(form.CPF());
			funcionario.setCPF(form.CPF());
			funcionario.getUsuario().setNomeDeUsuario(form.CPF());
			usuarioRepository.salvar(funcionario.getUsuario());
			log.info(String.format("Editado usuário %s com ID %s", funcionario.getUsuario().getNomeDeUsuario(), funcionario.getUsuario().getID()));
		}
		if (form.telefone() != null && !form.telefone().isBlank()) {
			funcionario.setTelefone(form.telefone());
		}
		if (form.email() != null && !form.email().isBlank()) {
			funcionario.setEmail(new Email(form.email()));
		}
		if (form.dataDeNascimento() != null) {
			funcionario.setDataDeNascimento(form.dataDeNascimento());
		}
		if (form.endereco() != null) {
			Endereco endereco = editaEndereco.editar(form.endereco(), funcionario.getEndereco());
			funcionario.setEndereco(endereco);
		}
		if (form.registro() != null && !form.registro().isBlank()) {
			funcionario.setRegistro(form.registro());
		}
		if (form.cargo() != null) {
			funcionario.setCargo(form.cargo());
		}
		return funcionario;
	}
}
