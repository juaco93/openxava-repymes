package com.openxava.naviox.model;

import java.io.*;
import java.math.*;
import java.security.*;
import java.util.*;

import javax.naming.*;
import javax.naming.directory.*;
import javax.persistence.*;

import org.apache.commons.collections.*;
import org.apache.commons.lang3.*;
import org.apache.commons.logging.*;
import org.openxava.annotations.*;
import org.openxava.application.meta.*;
import org.openxava.calculators.*;
import org.openxava.controller.meta.*;
import org.openxava.jpa.*;
import org.openxava.model.meta.*;
import org.openxava.util.*;

import com.openxava.naviox.model.User.*;

import lombok.*;

@Entity @Data
@Table(name="USERS", indexes={
	@Index(columnList="email"),
})
public class User implements java.io.Serializable {
	private static Log log = LogFactory.getLog(User.class);
	@Column(length=60) @Stereotype("EMAIL") 
	private String email;
	@Column(length=41) @DisplaySize(30) 
	@Stereotype("PASSWORD")	
	private String password;
	@Transient
	@Column(length=41) @DisplaySize(30) 
	@Stereotype("PASSWORD")		
	private String repeatPassword; 
	
	@Id @Column(length=30) 
	private String name;
	
	@ReadOnly
	private Date creationDate; 
	
	@ReadOnly
	private Date lastLoginDate;  
	
	@org.hibernate.annotations.Type(type="org.hibernate.type.YesNoType")
	@DefaultValueCalculator(TrueCalculator.class) 
	private boolean active = true;
	
	public static User find(String name) {
		User user = XPersistence.getManager().find(User.class, name);
		if (user != null) return user;
		if (Configuration.getInstance().isUseEmailAsUserName()) {
			if (name.contains("@")) {
				return findByEmail(name);
			}
		}
		return null;
	}
	
	public static User findByEmail(String email) { 
		try {
			Query query = XPersistence.getManager().createQuery("from User f where lower(f.email) = :email"); 
			query.setParameter("email", email.toLowerCase()); 
	 		return (User) query.getSingleResult();
		}
		catch (NoResultException ex) {
			return null;
		}
	}
	
	@PrePersist
	private void prePersit() { 
		creationDate = new Date();
		verifyPasswordsMatch();
	}
	
	@PreUpdate
	private void verifyPasswordsMatch() {
		if (repeatPassword == null) return;
		if (!repeatPassword.equals(password)) {
			Messages errors = new Messages();
			errors.add("passwords_not_match", "password");
			throw new org.openxava.validators.ValidationException(errors);
		}
	}
	
	private void encryptPassword() {
		password = encrypt(password);
	}
	
	private void encryptRepeatPassword() { 
		repeatPassword = encrypt(repeatPassword);
	}
	
	public boolean isAuthorized(String password) { 
		//if (!isActive()) return false;
		return passwordMatches(password);
	}
	
	public boolean passwordMatches(String password) {
		return encrypt(password).equals(this.password);
		
	}
	
	public static String encrypt(String source) {
		try {
	      MessageDigest md = MessageDigest.getInstance("SHA");
	      byte[] bytes = source.getBytes();
	      md.update(bytes);
	      byte[] encrypted= md.digest();
	    	  return new BigInteger(encrypted).toString(16);

		}
		catch (Exception ex) {
			log.error(XavaResources.getString("encrypting_password_problem"), ex); 
			throw new RuntimeException(XavaResources.getString("encrypting_password_problem"), ex);  
		}
	}
	

}
