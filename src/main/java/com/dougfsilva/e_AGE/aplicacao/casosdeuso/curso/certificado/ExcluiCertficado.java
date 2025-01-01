package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.certificado;

import com.dougfsilva.e_AGE.dominio.curso.certificado.Certificado;
import com.dougfsilva.e_AGE.dominio.curso.certificado.CertificadoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiCertficado {

	private final CertificadoRepository repository;
	private final MatriculaRepository matriculaRepository;
	
	public void excluirPeloID(String ID) {
		Certificado certificado = repository.buscarPeloIDOuThrow(ID);
		certificado.getMatricula().setStatus(MatriculaStatus.ALUNO_MATRICULADO);
		matriculaRepository.salvar(certificado.getMatricula());
		repository.excluir(certificado);
	}
}
