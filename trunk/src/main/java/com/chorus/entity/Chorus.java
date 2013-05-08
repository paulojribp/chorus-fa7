package com.chorus.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author kete@sagaranatech.com
 */
@Entity
public class Chorus {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHORUS_SEQ")
	@SequenceGenerator(name="CHORUS_SEQ", sequenceName="CHORUS_SEQ", allocationSize=1)
	private Integer	id;
	
	private String	mensagem;
	
	@ManyToOne
	@JoinColumn(name = "usuarioid")
	private Usuario	usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datahora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDatahora() {
		return datahora;
	}

	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}
	
}
