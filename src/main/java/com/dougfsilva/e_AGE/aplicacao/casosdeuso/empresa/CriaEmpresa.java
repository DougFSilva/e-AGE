package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaEmpresaForm;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComEmpresaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEmpresaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaEmpresa {

	private final EmpresaRepository repository;
	private final ValidaEmpresa validador;
	private final LogPadrao log;
	
	public Empresa criar(CriaEmpresaForm form) {
		try {
			Empresa empresa = construirEmpresa(form);
			Empresa empresaSalva = repository.salvar(empresa);
			log.info(String.format("Criada empresa %s", empresaSalva.getNome()));
			return empresaSalva;
		} catch (ErroDeValidacaoDeEmpresaException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar empresa %s : %s", form.nome(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComEmpresaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar empresa %s : %s", form.nome(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComEmpresaException(mensagem, e);
		}
	}
	
	private Empresa construirEmpresa(CriaEmpresaForm form) {
		Endereco endereco = new Endereco(
				form.endereco().pais(),
				form.endereco().estado(),
				form.endereco().codigoPostal(),
				form.endereco().cidade(),
				form.endereco().bairro(),
				form.endereco().rua(),
				form.endereco().numero());
		validador.validarUnicoCNPJ(form.CNPJ());
		validador.validarUnicoNome(form.nome());
		return new Empresa(form.CNPJ(),form.nome(), endereco);
	}
}
