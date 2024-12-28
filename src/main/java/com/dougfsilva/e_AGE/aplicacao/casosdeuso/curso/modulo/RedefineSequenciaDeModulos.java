package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RedefineSequenciaDeModulos {

	private final ModuloRepository repository;
	private final TurmaRepository turmaRepository;
	private final LogPadrao log;

	public void redefinir(String turmaID, List<String> IDsModulosOrdenados) {
		Turma turma = turmaRepository.buscarPeloIDOuThrow(turmaID);
		List<Modulo> modulos = repository.buscarPelaTurma(turma);
		validarQuantidadeDeModulos(modulos, IDsModulosOrdenados);
		List<Modulo> modulosReorganizados = reorganizarModulos(modulos, IDsModulosOrdenados);
		repository.salvarTodos(modulosReorganizados);
		log.info(String.format("Reordenado módulos da turma %s", turma.getCodigo()));
	}

	private void validarQuantidadeDeModulos(List<Modulo> modulos, List<String> IDsModulosOrdenados) {
		if (IDsModulosOrdenados.size() != modulos.size()) {
			throw new ErroDeValidacaoDeModuloException(
					"O número de módulos fornecido não corresponde ao número de módulos da turma");
		}
	}

	private List<Modulo> reorganizarModulos(List<Modulo> modulos, List<String> IDsModulosOrdenados) {
		Map<String, Modulo> modulosMap = modulos.stream().collect(Collectors.toMap(Modulo::getID, modulo -> modulo));
		for (int i = 0; i < IDsModulosOrdenados.size(); i++) {
			String id = IDsModulosOrdenados.get(i);
			Modulo modulo = modulosMap.get(id);

			if (modulo == null) {
				throw new ObjetoNaoEncontradoException("Módulo com ID " + id + " não encontrado");
			}

			modulo.setNumeroDoModulo(i + 1);
			modulo.setModuloFinal(i == IDsModulosOrdenados.size() - 1);
		}

		return modulos;
	}
}
