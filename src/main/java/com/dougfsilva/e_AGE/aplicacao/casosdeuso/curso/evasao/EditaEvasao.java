package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import com.dougfsilva.e_AGE.aplicacao.formulario.EditaEvasaoForm;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaEvasao {

	private final EvasaoRepository repository;
	private final ModuloRepository moduloRepository;
	
	public Evasao editar(EditaEvasaoForm form) {
		Evasao evasao = editarDados(form);
		return repository.salvar(evasao);
	}
	
	private Evasao editarDados(EditaEvasaoForm form) {
		Evasao evasao = repository.buscarPeloIDOuThrow(form.ID());
		if (form.moduloID() != null && !form.moduloID().isBlank() && !form.moduloID().equalsIgnoreCase(evasao.getModulo().getID())) {
			Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
			garantirModuloPertencenteATurma(modulo, evasao.getMatricula().getTurma());
			garantirModuloEmAndamentoOuConcluído(modulo);
			evasao.setModulo(modulo);
		}
		if (form.data() != null) {
			evasao.setData(form.data());
		}
		if (form.motivo() != null && !form.motivo().isBlank()) {
			evasao.setMotivo(form.motivo());
		}
		return evasao;
	}
	
	private void garantirModuloPertencenteATurma(Modulo modulo, Turma turma) {
		if (!modulo.getTurma().equals(turma)) {
			throw new ErroDeValidacaoDeEvasaoException(
					String.format("Não é possível editar a evasão pois o módulo %s não pertence a turma %s",
							modulo.getCodigo(), turma.getCodigo()));
		}
	}
	
	private void garantirModuloEmAndamentoOuConcluído(Modulo modulo) {
		if (modulo.getStatus() == ModuloStatus.NAO_INICIADO) {
			throw new ErroDeValidacaoDeEvasaoException(String.format(
					"Não é possível editar a evasão pois o módulo %s ainda não foi iniciado", modulo.getCodigo()));
		}
	}
}
