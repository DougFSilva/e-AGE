package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.EditaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaEmpresaForm;
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
	private final EditaEndereco editaEndereco;
	private final ValidaEmpresa validador;
	private final LogPadrao log;
	
	public Empresa editar(EditaEmpresaForm form) {
		try {
			Empresa empresa = repository.buscarPeloIDOuThrow(form.ID());
			Empresa empresaEditada = editarDados(form, empresa);
			Empresa empresaSalva = repository.salvar(empresaEditada);
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
	
	private Empresa editarDados(EditaEmpresaForm form, Empresa empresa) {
		if (form.CNPJ() != null && !form.CNPJ().isBlank() && !form.CNPJ().equalsIgnoreCase(empresa.getCNPJ())) {
			validador.validarUnicoCNPJ(form.CNPJ());
			empresa.setCNPJ(form.CNPJ());
		}
		if (form.nome() != null && !form.nome().isBlank() && !form.nome().equalsIgnoreCase(empresa.getNome())) {
			validador.validarUnicoNome(form.nome());
			empresa.setNome(form.nome());
		}
		if (form.endereco() != null) {
			Endereco enderecoEditado = editaEndereco.editar(form.endereco(), empresa.getEndereco());
			empresa.setEndereco(enderecoEditado);
		}
		return empresa;
	}
	
}
