package com.arsjdev.repymes.model;

import javax.persistence.*;

import org.openxava.model.*;

import lombok.*;

@Entity @Data
public class Departamento extends Identifiable{
	String descripcion;
}
