package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.dominio.curso.certificado.CertificadoRepository;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.reprova.ReprovaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiMatricula {

	private final MatriculaRepository repository;
	private final CertificadoRepository certificadoRepository;
	private final EvasaoRepository evasaoRepository;
	private final ReprovaRepository reprovaRepository;
	
	public void excluirPeloID(String ID) {
		Matricula matricula = repository.buscarPeloIDOuThrow(ID);
		excluirCertificado(matricula);
		excluirEvasao(matricula);
		excluirReprova(matricula);
		repository.excluir(matricula);
	}
	
	private void excluirCertificado(Matricula matricula) {
		certificadoRepository.buscarPelaMatricula(matricula).ifPresent(certificado -> certificadoRepository.excluir(certificado));
	}
	
	private void excluirEvasao(Matricula matriucla) {
		evasaoRepository.buscarPelaMatricula(matriucla).ifPresent(evasao -> evasaoRepository.excluir(evasao));
	}
	
	private void excluirReprova(Matricula matricula) {
		reprovaRepository.buscarPelaMatricula(matricula).ifPresent(reprova -> reprovaRepository.excluir(reprova));
	}
}
