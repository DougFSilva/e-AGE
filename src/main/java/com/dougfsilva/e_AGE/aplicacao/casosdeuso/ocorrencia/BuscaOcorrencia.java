package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.TipoOcorrencia;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.UsuarioAutenticado;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaOcorrencia {

	private final OcorrenciaRepository repository;
	private final UsuarioAutenticado usuarioAutenticado;
	private final FuncionarioRepository funcionarioRepository;
	private final AlunoRepository alunoRepository;
	private final CursoRepository cursoRepository;
	private final TurmaRepository turmaRepository;

	public Pagina<Ocorrencia> buscarPelaDataDeAbertura(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina RequisicaoDePagina) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarPelaDataDeAbertura(dataInicial, dataFinal, RequisicaoDePagina);
		}
		return repository.buscarPelaDataDeAberturaENaoRestritaOuPelaDataDeAberturaEUsuarioRelator(dataInicial, dataFinal,
				usuario, RequisicaoDePagina);
	}
	
	public Pagina<Ocorrencia> buscarPeloRelator(String relatorID, RequisicaoDePagina RequisicaoDePagina) {
		Funcionario relator = funcionarioRepository.buscarPeloIDOuThrow(relatorID);
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarPeloRelator(relator, RequisicaoDePagina);
		}
		return repository.buscarPeloRelatorENaoRestritaOuPeloRelatorEUsuarioRelator(relator, usuario, RequisicaoDePagina);
	}

	public List<Ocorrencia> buscarPeloAluno(String alunoID) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(alunoID);
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarPeloAluno(aluno);
		}
		return repository.buscarPeloAlunoENaoRestritaOuPeloAlunoEUsuarioRelator(aluno, usuario);
	}
	
	public Pagina<Ocorrencia> buscarPeloCurso(String cursoID, RequisicaoDePagina RequisicaoDePagina) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		Curso curso = cursoRepository.buscarPeloIDOuThrow(cursoID);
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarPeloCurso(curso, RequisicaoDePagina);
		}
		return repository.buscarPeloCursoENaoRestritaOuPeloCursoEUsuarioRelator(curso, usuario, RequisicaoDePagina);
	}

	public Pagina<Ocorrencia> buscarPelaTurma(String turmaID, RequisicaoDePagina RequisicaoDePagina) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		Turma turma = turmaRepository.buscarPeloIDOuThrow(turmaID);
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarPelaTurma(turma, RequisicaoDePagina);
		}
		return repository.buscarPelaTurmaENaoRestritaOuPelaTurmaEUsuarioRelator(turma, usuario, RequisicaoDePagina);
	}

	public Pagina<Ocorrencia> buscarPeloTipoDeOcorrencia(TipoOcorrencia tipoOcorrencia, RequisicaoDePagina RequisicaoDePagina) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarPeloTipoDeOcorrencia(tipoOcorrencia, RequisicaoDePagina);
		}
		return repository.buscarPeloTipoDeOcorrenciaENaoRestritaOuPeloTipoDeOcorrenciaEUsuarioRelator(tipoOcorrencia, usuario, RequisicaoDePagina);
	}

	public Pagina<Ocorrencia> buscarEncaminhadas(Boolean encaminhada, RequisicaoDePagina RequisicaoDePagina) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarEncaminhadas(encaminhada, RequisicaoDePagina);
		}
		return repository.buscarEncaminhadasENaoRestritaOuEncaminhadasEUsuarioRelator(encaminhada, usuario, RequisicaoDePagina);
	}

	public Pagina<Ocorrencia> buscarPeloStatusAberta(Boolean aberta, RequisicaoDePagina RequisicaoDePagina) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarPeloStatusAberta(aberta, RequisicaoDePagina);
		}
		return repository.buscarPeloStatusAbertaENaoRestritaOuPeloStatusAbertaEUsuarioRelator(aberta, usuario, RequisicaoDePagina);
	}

	public Pagina<Ocorrencia> buscarTodas(RequisicaoDePagina RequisicaoDePagina) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		if (usuario.contemPerfil(TipoPerfil.GESTOR)) {
			return repository.buscarTodas(RequisicaoDePagina);
		}
		return repository.buscarTodasENaoRestritaOuTodasEUsuarioRelator(usuario, RequisicaoDePagina);
	}

}
