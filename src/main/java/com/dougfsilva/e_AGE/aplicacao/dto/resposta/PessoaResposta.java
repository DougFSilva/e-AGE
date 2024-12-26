package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.pessoa.Pessoa;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = { "RG" })
public class PessoaResposta {

	private String ID;
	private UsuarioResposta usuario;
	private String nome;
	private Sexo sexo;
	private String RG;
	private String telefone;
	private String email;
	private LocalDate dataDeNascimento;
	private Endereco endereco;
	private String foto;
	
	public PessoaResposta(Pessoa pessoa) {
		this.ID = pessoa.getID();
		this.usuario = UsuarioResposta.deUsuario(pessoa.getUsuario());
		this.nome = pessoa.getNome();
		this.sexo = pessoa.getSexo();
		this.RG = pessoa.getRG();
		this.telefone = pessoa.getTelefone();
		this.email = pessoa.getEmail().getEndereco();
		this.dataDeNascimento = pessoa.getDataDeNascimento();
		this.endereco = pessoa.getEndereco();
		this.foto = pessoa.getFoto();
	}
	
}
