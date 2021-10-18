package com.arsjdev.repymes.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity @Data
public class Localidad extends Identifiable{
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	@NoModify
	@NoCreate
	Departamento departamento;
	String descripcion;
}
