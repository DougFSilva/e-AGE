package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.certificado;

import com.dougfsilva.e_AGE.aplicacao.formulario.CertificaAlunoForm;
import com.dougfsilva.e_AGE.dominio.curso.certificado.Certificado;
import com.dougfsilva.e_AGE.dominio.curso.certificado.CertificadoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCertificadoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificaAluno {

	private final CertificadoRepository repository;
	private final MatriculaRepository matriculaRepository;
	
	public Certificado certificar(CertificaAlunoForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		garantirStatusAlunoMatriculado(matricula);
		garantirUnicaReprovaPorMatricula(matricula);
		matricula.setStatus(MatriculaStatus.ALUNO_CERTIFICADO);
		Certificado certificado = new Certificado(matricula, form.data());
		return repository.salvar(certificado);
	}
	
	private void garantirUnicaReprovaPorMatricula(Matricula matricula) {
		if(repository.existePelaMatricula(matricula)) {
			throw new ErroDeValidacaoDeCertificadoException(String.format("Já existe certificação para matrícula %s", matricula.getRegistro()));
		}
	}
	
	private void garantirStatusAlunoMatriculado(Matricula matricula) {
		if (matricula.getStatus() != MatriculaStatus.ALUNO_MATRICULADO) {
			throw new ErroDeValidacaoDeCertificadoException("Não é possível certificar um aluno com status diferente de ALUNO_MATRICULADO");
		}
	}
}
