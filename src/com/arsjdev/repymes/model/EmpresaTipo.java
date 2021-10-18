package com.arsjdev.repymes.model;

import javax.persistence.*;

import org.openxava.model.*;

import lombok.*;

@Entity @Data
public class EmpresaTipo extends Identifiable{
	String descripcion;
}
