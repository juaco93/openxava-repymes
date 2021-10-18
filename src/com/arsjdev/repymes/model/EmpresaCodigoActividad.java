package com.arsjdev.repymes.model;

import javax.persistence.*;

import org.openxava.model.*;

import lombok.*;

@Entity @Data
public class  EmpresaCodigoActividad extends Identifiable{
	String codigo;
	String descripcion;
	String descripcionLarga;
}
