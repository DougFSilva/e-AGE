package com.dougfsilva.e_AGE.dominio.ocorrencia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	
	Pagina<Ocorrencia> buscarPelaDataDeAberturaENaoRestritaOuPelaDataDeAberturaEUsuarioRelator(LocalDate dataInicial, LocalDate dataFinal, Usuario usuario, Boolean restrita, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPelaDataDeFechamento(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPelaDataDeFechamentoENaoRestritaOuPelaDataDeAberturaEUsuarioRelator(LocalDate dataInicial, LocalDate dataFinal, Usuario usuario, Boolean restrita, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloRelator(Funcionario relator, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloRelatorENaoRestritaOuPeloRelatorEUsuarioRelator(Funcionario relator, Boolean restrita, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);

	List<Ocorrencia> buscarPelaMatricula(Matricula matricula);
	
	List<Ocorrencia> buscarPelaMatriculaENaoRestritaOuPelaMatriculaEUsuarioRelator(Matricula matricula, Boolean restrita, Usuario usuario);
	
	List<Ocorrencia> buscarPeloAluno(Aluno studant);
	
	List<Ocorrencia> buscarPeloAlunoENaoRestritaOuPeloAlunoEUsuarioRelator(Aluno studant, Boolean restrita, Usuario usuario);
	
	Pagina<Ocorrencia> buscarPelaTurma(Turma clazz, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPelaTurmaENaoRestritaOuPelaTurmaEUsuarioRelator(Turma clazz, Usuario usuario, Boolean restrita, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloTipoDeOcorrencia(TipoOcorrencia ocorrenciaType, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloTipoDeOcorrenciaENaoRestritaOuPeloTipoDeOcorrenciaEUsuarioRelator(TipoOcorrencia ocorrenciaType, Usuario usuario, Boolean restrita, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarEncaminhadas(Boolean forwarding, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarEncaminhadasENaoRestritaOuEncaminhadasEUsuarioRelator(Boolean forwardind, Boolean restrita, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloStatusAberta(Boolean open, RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarPeloStatusAbertaENaoRestritaOuPeloStatusAbertaEUsuarioRelator(Boolean open, Usuario usuario, Boolean restrita,RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarTodas(RequisicaoDePagina RequisicaoDePagina);
	
	Pagina<Ocorrencia> buscarTodasENaoRestritaOuTodasEUsuarioRelator(Boolean restrita, Usuario usuario, RequisicaoDePagina RequisicaoDePagina);

	Boolean existePeloAluno(Aluno aluno);
	
	Boolean existePeloResponsavelDeAberturaOuFechamentoOuEncerramentoOuAssinaturaDeAlunoOuResponsavel(Funcionario funcionario);
}
