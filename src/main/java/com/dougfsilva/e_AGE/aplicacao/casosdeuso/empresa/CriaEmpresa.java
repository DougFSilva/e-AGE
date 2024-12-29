package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.CriaEndereco;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaEmpresaForm;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaEmpresa {

	private final EmpresaRepository repository;
	private final CriaEndereco criaEndereco;
	private final ValidaEmpresa validador;
	
	public Empresa criar(CriaEmpresaForm form) {
		Empresa empresa = construirEmpresa(form);
		return repository.salvar(empresa);
	}
	
	private Empresa construirEmpresa(CriaEmpresaForm form) {
		Endereco endereco = criaEndereco.criar(form.endereco());
		validador.validarUnicoCNPJ(form.CNPJ());
		validador.validarUnicoNome(form.nome());
		return new Empresa(form.CNPJ(),form.nome(), endereco);
	}
}
