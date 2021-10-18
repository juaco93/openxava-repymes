package com.arsjdev.repymes.calculators;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

public class IdEmpresaCalculator implements ICalculator {

		@Override
		public Object calculate() throws Exception {
			Query query = XPersistence.getManager() 
		            .createQuery("select max(idempresa) from Empresa" );
		                                                              
			Integer maxNumber =  (Integer) query.getSingleResult();
	        int nextNumber = (maxNumber == null ) ? 1 : maxNumber;
	        nextNumber = nextNumber + 1;
	        
	        return nextNumber;
		}

	
}
