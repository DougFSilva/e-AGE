package com.dougfsilva.e_AGE.dominio.ocorrencia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface OcorrenciaRepository {

	Ocorrencia salvar(Ocorrencia ocorrencia);
	
	void excluir(Ocorrencia ocorrencia);
	
	void excluirTodas(List<Ocorrencia> ocorrencias);
	
	void excluirPeloAluno(Aluno aluno);
	
	Optional<Ocorrencia> buscarPeloID(String ID);
	
	default Ocorrencia buscarPeloIDOuThrow(String ID) {
	    return buscarPeloID(ID)
	        .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Ocorrência com ID %s não encontrado", ID)));
	}
	
	Pagina<Ocorrencia> buscarPelaDataDeAbertura(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPelaDataDeAberturaENaoRestritaOuPelaDataDeAberturaEUsuarioRelator(LocalDate dataInicial, LocalDate dataFinal, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloRelator(Funcionario relator, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloRelatorENaoRestritaOuPeloRelatorEUsuarioRelator(Funcionario relator, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);

	List<Ocorrencia> buscarPelaMatricula(Matricula matricula);
	
	List<Ocorrencia> buscarPelaMatriculaENaoRestritaOuPelaMatriculaEUsuarioRelator(Matricula matricula, Usuario usuario);
	
	List<Ocorrencia> buscarPeloAluno(Aluno aluno);
	
	List<Ocorrencia> buscarPeloAlunoENaoRestritaOuPeloAlunoEUsuarioRelator(Aluno aluno, Usuario usuario);
	
	Pagina<Ocorrencia> buscarPeloCurso(Curso curso, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloCursoENaoRestritaOuPeloCursoEUsuarioRelator(Curso curso, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPelaTurma(Turma turma, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPelaTurmaENaoRestritaOuPelaTurmaEUsuarioRelator(Turma turma, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloTipoDeOcorrencia(TipoOcorrencia tipoOcorrencia, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloTipoDeOcorrenciaENaoRestritaOuPeloTipoDeOcorrenciaEUsuarioRelator(TipoOcorrencia tipoOcorrencia, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarEncaminhadas(Boolean encaminhada, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarEncaminhadasENaoRestritaOuEncaminhadasEUsuarioRelator(Boolean encaminhada, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloStatusAberta(Boolean aberta, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloStatusAbertaENaoRestritaOuPeloStatusAbertaEUsuarioRelator(Boolean aberta, Usuario usuario,RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarTodas(RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarTodasENaoRestritaOuTodasEUsuarioRelator(Usuario usuario, RequisicaoDePagina RequisicaoDePagina);

	Boolean existePeloAluno(Aluno aluno);
	
	Boolean existePeloResponsavelDeAberturaOuFechamentoOuEncerramentoOuAssinaturaDeAlunoOuResponsavel(Funcionario funcionario);
}
