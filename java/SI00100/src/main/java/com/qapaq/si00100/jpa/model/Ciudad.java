package com.qapaq.si00100.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qapaq.si00100.Constantes;
import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.validadores.CiudadNombre;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad jpa en base a la consulta de la tabla "SELECT
 * id_ciudad, nombre, latitud, longitud, usuario, usuario_fecha,
 * usuario_programa FROM SI_001_00.ciudad;"
 * 
 * El indice es id_ciudad autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "ciudad", schema = "SI_001_00")
@Getter
@Setter
@ToString
@CiudadNombre(titulo = "nombre", message = "E-SI00100-6")
@JsonIgnoreProperties({ "idModulo", "estado", "usuario", "usuarioFecha" })
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ciudad")
	private Long idCiudad;

	@Transient
	@JsonProperty("idxModulo")
	private String idxModulo;

	@NotNull(message = "E-SI00100-2")
	@Column(name = "nombre", length = 128)
	private String nombre;

	@NotNull(message = "E-SI00100-2")
	
	@Pattern(regexp = Constantes.LATITUD_PATTERN, message = "E-SI00100-9")
	@Column(name = "latitud", length = 64)
	private String latitud;

	@NotNull(message = "E-SI00100-2")	
	@Pattern(regexp = Constantes.LONGITUD_PATTERN, message = "E-SI00100-10")
	@Column(name = "longitud", length = 64)
	private String longitud;

	@Column(name = "usuario", length = 128)
	private String usuario;

	@Column(name = "usuario_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date usuarioFecha;

	@Column(name = "usuario_programa", length = 256)
	private String usuarioPrograma;

	/**
	 * Relacion con Ciudad-Clima
	 */
	@OneToMany(targetEntity = Clima.class, fetch = FetchType.LAZY)	
	@JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad" )
	@OrderBy("usuarioFecha Asc")
	private List<Clima> climaList;


	/**
	 * Relacion con Ciudad-Localizacion
	 */
	@OneToMany(targetEntity = Localizacion.class, fetch = FetchType.LAZY)	
	@JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")	
	@OrderBy("usuarioFecha Asc")	
	private List<Localizacion> localizacionList;

	/**
	 * No se puede borrar si hay registros de clima asociados.
	 * @throws ForeignKeyException
	 */
	@PreRemove
	public void preRemove() throws ForeignKeyException {
		if (climaList != null && !climaList.isEmpty()) {
			throw new ForeignKeyException("E-SI00100-7");
		}

		if (localizacionList != null && !localizacionList.isEmpty()) {
			throw new ForeignKeyException("E-SI00100-7");
		}
	}
}
