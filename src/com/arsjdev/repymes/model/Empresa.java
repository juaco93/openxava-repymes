package com.arsjdev.repymes.model;

import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.hibernate.envers.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

import com.arsjdev.repymes.calculators.*;
import com.arsjdev.repymes.validator.*;
import com.arsjedev.repymes.actions.*;

import lombok.*;

@Entity @Data
public class Empresa {
	
	@Id @Column(length = 7) @ReadOnly 
	@DefaultValueCalculator(value = IdEmpresaCalculator.class)
	private Integer idempresa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate
	private EmpresaSector sector;
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate
	private EmpresaCategoria categoria;
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate	
	private EmpresaTipo tipoEmpresa;
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate	
	private Departamento departamento;
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate
	private Localidad localidad;
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate
	private EmpresaTipoSociedad tipoSociedad;
	private String cuit;
	private String nombreFantasia;
	private String nroIngresosBrutos;
	private Float facturacion;
	private Date inicioActividad;
	
	//@OnChange(EmpresaActividadPrincipalOnChangeAction.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@NoModify
	@NoCreate
	private EmpresaCodigoActividad actividadPrincipal;
	
	private String domicilioFiscal;
	private String domicilioActividad;
	private String responsable;
	private String responsableDni;
	private String razonSocial;
	private String telefono;
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	@NoModify
	@NoCreate	
	private AfipCategoriaMonotributo categoriaMonotributo;
	private Float superficieTotal;
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate	
	private PersonalCantidad personalCantidad;
	private String exporta;
	private String observaciones;
	private String necesidades;
	
	
	@PrePersist
	public void ActualizarEstadoDeEmpresa() throws  IllegalStateException {
		/*Estado nuevoEstado = new Estado();
		nuevoEstado.setFecha(new Date());
		nuevoEstado.setDescripcion("INICIADO");
		nuevoEstado.setObservaciones("Inicio del trámite.");
		nuevoEstado.setUsuario(Users.getCurrent());
		nuevoEstado.setTramite(this);
		this.setEstado(nuevoEstado.getDescripcion());
		this.setFechaDeUltimaActualizacion(LocalDate.now());
		*/
		
		//XPersistence.getManager().persist(nuevoEstado);

	}
}
