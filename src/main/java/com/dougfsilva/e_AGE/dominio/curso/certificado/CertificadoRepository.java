package com.dougfsilva.e_AGE.dominio.curso.certificado;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface CertificadoRepository {

	Certificado salvar(Certificado certificado);

	void excluir(Certificado certificado);

	Optional<Certificado> buscarPeloID(String ID);

	default Certificado buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Certificado com ID %s não encontrado", ID)));
	}
	
	List<Certificado> buscarPeloAluno(Aluno aluno);
	
	Pagina<Certificado> buscarPeloCurso(Curso curso, RequisicaoDePagina requisicao);
	
	Pagina<Certificado> buscarPelaDataDeCertificacao(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao);
	
	Pagina<Certificado> buscarTodos(RequisicaoDePagina requisicao);
}
