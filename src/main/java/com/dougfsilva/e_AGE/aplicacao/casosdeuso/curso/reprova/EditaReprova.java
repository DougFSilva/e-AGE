package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.reprova;

import com.dougfsilva.e_AGE.aplicacao.formulario.EditaReprovaForm;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
import com.dougfsilva.e_AGE.dominio.curso.reprova.Reprova;
import com.dougfsilva.e_AGE.dominio.curso.reprova.ReprovaRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeReprovaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaReprova {

	private final ReprovaRepository repository;
	private final ModuloRepository moduloRepository;
	
	public Reprova editar(EditaReprovaForm form) {
		Reprova reprova = editarDados(form);
		return repository.salvar(reprova);
	}
	
	private Reprova editarDados(EditaReprovaForm form) {
		Reprova reprova = repository.buscarPeloIDOuThrow(form.ID());
		if (form.moduloID() != null && !form.moduloID().isBlank() && !form.moduloID().equalsIgnoreCase(reprova.getModulo().getID())) {
			Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
			garantirModuloPertencenteATurma(modulo, reprova.getMatricula().getTurma());
			garantirModuloEmAndamentoOuConcluído(modulo);
			reprova.setModulo(modulo);
		}
		if (form.data() != null) {
			reprova.setData(form.data());
		}
		if (form.causa() != null) {
			reprova.setCausa(form.causa());
		}
		if (form.detalhes() != null) {
			reprova.setDetalhes(form.detalhes());
		}
		return reprova;
	}
	
	private void garantirModuloPertencenteATurma(Modulo modulo, Turma turma) {
		if (!modulo.getTurma().equals(turma)) {
			throw new ErroDeValidacaoDeReprovaException(
					String.format("Não é possível editar a reprova pois o módulo %s não pertence a turma %s",
							modulo.getCodigo(), turma.getCodigo()));
		}
	}
	
	private void garantirModuloEmAndamentoOuConcluído(Modulo modulo) {
		if (modulo.getStatus() == ModuloStatus.NAO_INICIADO) {
			throw new ErroDeValidacaoDeReprovaException(String.format(
					"Não é possível editar a reprova pois o módulo %s ainda não foi iniciado", modulo.getCodigo()));
		}
	}
}
