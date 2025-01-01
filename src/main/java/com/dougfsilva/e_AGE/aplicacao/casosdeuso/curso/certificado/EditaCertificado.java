package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.certificado;

import com.dougfsilva.e_AGE.aplicacao.formulario.EditaCertificadoForm;
import com.dougfsilva.e_AGE.dominio.curso.certificado.Certificado;
import com.dougfsilva.e_AGE.dominio.curso.certificado.CertificadoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaCertificado {

	private final CertificadoRepository repository;
	
	public Certificado editar(EditaCertificadoForm form) {
		Certificado certificado = editarDados(form);
		return repository.salvar(certificado);
	}
	
	private Certificado editarDados(EditaCertificadoForm form) {
		Certificado certificado = repository.buscarPeloIDOuThrow(form.ID());
		if (form.data() != null) {
			certificado.setData(form.data());
		}
		return certificado;
	}
}
