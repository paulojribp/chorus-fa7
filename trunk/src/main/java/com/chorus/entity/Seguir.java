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

@Entity
public class Seguir {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGUIR_SEQ")
	@SequenceGenerator(name="SEGUIR_SEQ", sequenceName="SEGUIR_SEQ", allocationSize=1)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "usuarioid")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "seguindo_usuarioid")
	private Usuario seguindo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datahora;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(Usuario seguindo) {
		this.seguindo = seguindo;
	}

	public Date getDatahora() {
		return datahora;
	}

	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}
	
}
