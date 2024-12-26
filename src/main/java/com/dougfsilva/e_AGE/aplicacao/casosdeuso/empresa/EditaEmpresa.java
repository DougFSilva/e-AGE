package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaEmpresaForm;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaEnderecoForm;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComEmpresaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEmpresaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaEmpresa {

	private final EmpresaRepository repository;
	private final ValidaEmpresa validador;
	private final LogPadrao log;
	
	public Empresa editar(EditaEmpresaForm form) {
		try {
			Empresa empresa = repository.buscarPeloIDOuThrow(form.ID());
			Empresa empresaAtualizada = atualizarDados(form, empresa);
			Empresa empresaSalva = repository.salvar(empresaAtualizada);
			log.info(String.format("Editada empresa %s", empresaSalva.getNome()));
			return empresaSalva;
		} catch (ErroDeValidacaoDeEmpresaException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar empresa %s : %s", form.nome(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComEmpresaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar empresa %s : %s", form.nome(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComEmpresaException(mensagem, e);
		}
	}
	
	private Empresa atualizarDados(EditaEmpresaForm form, Empresa empresa) {
		if (form.CNPJ() != null && !form.CNPJ().isBlank() && !form.CNPJ().equalsIgnoreCase(empresa.getCNPJ())) {
			validador.validarUnicoCNPJ(form.CNPJ());
			empresa.setCNPJ(form.CNPJ());
		}
		if (form.nome() != null && !form.nome().isBlank() && !form.nome().equalsIgnoreCase(empresa.getNome())) {
			validador.validarUnicoNome(form.nome());
			empresa.setNome(form.nome());
		}
		if (form.endereco() != null) {
			Endereco enderecoAtualizado = atualizarEndereco(form.endereco(), empresa.getEndereco());
			empresa.setEndereco(enderecoAtualizado);
		}
		return empresa;
	}
	
	private Endereco atualizarEndereco(EditaEnderecoForm form, Endereco endereco) {
		if (form.pais() != null && !form.pais().isBlank()) {
			endereco.setPais(form.pais());
		}
		if (form.estado() != null && !form.estado().isBlank()) {
			endereco.setEstado(form.estado());
		}
		if (form.codigoPostal() != null && !form.codigoPostal().isBlank()) {
			endereco.setCodigoPostal(form.codigoPostal());
		}
		if (form.cidade() != null && !form.cidade().isBlank()) {
			endereco.setCidade(form.cidade());
		}
		if (form.bairro() != null && !form.bairro().isBlank()) {
			endereco.setBairro(form.bairro());
		}
		if (form.rua() != null && !form.rua().isBlank()) {
			endereco.setRua(form.rua());
		}
		if (form.numero() != null && !form.numero().isBlank()) {
			endereco.setNumero(form.numero());
		}
		return endereco;
	}
}
