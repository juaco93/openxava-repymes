package com.arsjdev.repymes.model;

import javax.persistence.*;

import org.openxava.model.*;

import lombok.*;

@Entity @Data
public class PersonalCantidad extends Identifiable{
	String descripcion;
	Float promedio;
}
