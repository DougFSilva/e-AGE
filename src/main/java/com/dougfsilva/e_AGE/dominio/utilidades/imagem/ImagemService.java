package com.dougfsilva.e_AGE.dominio.utilidades.imagem;

import org.springframework.web.multipart.MultipartFile;

public interface ImagemService {

	String salvar(MultipartFile image, TipoDeImagem tipo, String name);
	
	void remover(TipoDeImagem tipo, String name);
	
	String buscarImagemPadrao(TipoDeImagem tipo);
}
