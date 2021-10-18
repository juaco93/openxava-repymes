
package com.openxava.naviox.model;

import javax.persistence.*;
import org.openxava.annotations.*;


/**
 * 
 * @author Javier Paniza 
 */
@View(members = ""
		+ "logo;"
		+ "user;"
		+ "password;"
		+ "signUpButton"
		)
@View(name="Unlock", members="password")
public class SignIn {
	
	@Column(length=60) 
	@LabelFormat(LabelFormatType.SMALL)
	@Required 
	//@OnChange(UserUserOnChange.class)
	private String user; 

	@Column(length=30) @Stereotype("PASSWORD")
	@LabelFormat(LabelFormatType.SMALL)
	@Required  
	private String password;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
	
	 
	@LabelFormat(value = LabelFormatType.NO_LABEL)	 
	@Stereotype("LABEL")
	@Column @ReadOnly	
	public String getLogo() {
		return "";	
	}
	
	@LabelFormat(value = LabelFormatType.NO_LABEL)	 
	@Stereotype("LABEL")
	@Column @ReadOnly	
	public String getSignUpButton() {
		return "";	
	}
	
	@LabelFormat(value = LabelFormatType.NO_LABEL)	 
	@Stereotype("LABEL")
	@Column @ReadOnly	
	public String getForgotPasswordButton() {
		return "";	
	}
	
}