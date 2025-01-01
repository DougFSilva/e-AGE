package com.dougfsilva.e_AGE.dominio.curso.reprova;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface ReprovaRepository {
	
	Reprova salvar(Reprova reprova);
	
	void excluir(Reprova reprova);

	Optional<Reprova> buscarPeloID(String ID);
	
	default Reprova buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Reprova com ID %s não encontrada", ID)));
	}
	
	Optional<Reprova> buscarPelaMatricula(Matricula matricula);
	
	Pagina<Reprova> buscarPeloCurso(Curso curso);
	
	List<Reprova> buscarPelaTurma(Turma turma);
	
	List<Reprova> buscarPeloAluno(Aluno aluno);
	
	List<Reprova> buscarPeloModulo(Modulo modulo);
	
	Pagina<Reprova> buscarPelaData(LocalDate min, LocalDate max, RequisicaoDePagina requisicao);
	
	Pagina<Reprova> buscarPelaCausa(CausaReprova causa, RequisicaoDePagina requisicao);
	
	Pagina<Reprova> buscarTodas(RequisicaoDePagina requisicao);
	
	Boolean existePelaMatricula(Matricula matricula);
}
