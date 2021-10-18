package com.arsjdev.repymes.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.model.*;

import lombok.*;

@Entity @Data
public class AfipCategoriaMonotributo extends Identifiable {
	String categoria;
	Integer ingresosBrutosHasta;

}
