package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.EditaEndereco;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaEmpresaForm;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaEmpresa {

	private final EmpresaRepository repository;
	private final EditaEndereco editaEndereco;
	private final ValidaEmpresa validador;
	
	public Empresa editar(EditaEmpresaForm form) {
		Empresa empresa = repository.buscarPeloIDOuThrow(form.ID());
		Empresa empresaEditada = editarDados(form, empresa);
		return repository.salvar(empresaEditada);
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
