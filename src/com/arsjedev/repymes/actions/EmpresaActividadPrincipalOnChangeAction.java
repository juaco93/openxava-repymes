package com.arsjedev.repymes.actions;

import java.util.logging.*;

import org.openxava.actions.*;

public class EmpresaActividadPrincipalOnChangeAction extends ViewBaseAction implements IOnChangePropertyAction {

	public void execute() throws Exception {

		Logger logger = Logger.getLogger("global");
		logger.info("CARGARCOMBO---->BUSCANDO TRAMITE DESDE NOVEDADES");

		// Tramites tramite = (Tramites) getView().getRoot().getEntity();

		//Integer key = getView().getValueInt("tramiteNumero");
		String actividadPrincipal = getView().getValueString("actividadPrincipal");
		
		logger.info("CARGARCOMBO---->Texto escrito-->"+actividadPrincipal);
		String descripcionTipoDeTramite = "";
		/*
		if (!idTipoDeTramite.equals("")) {
			TipoDeTramite tipo = (TipoDeTramite) XPersistence.getManager().createQuery("from TipoDeTramite t WHERE t.id = :key ")
					.setParameter("key", idTipoDeTramite)
					.getSingleResult();

			descripcionTipoDeTramite = tipo.getDescripcion();

			logger.info("CARGARCOMBO---->ESTADO DEL TRAMITE SELECCIONADO--->" + descripcionTipoDeTramite);
			
			
		}*/
		
		logger.info("CARGARCOMBO---->TIPO DE TRAMITE--->" + descripcionTipoDeTramite);
		
		if(descripcionTipoDeTramite.equals("Pedido de ODE")) {
			getView().setHidden("importeAutorizado", false);
			getView().setEditable("importeAutorizado", true);
		}else {
			//getView().setValue("importeAutorizado",null);
			getView().setHidden("importeAutorizado", true);
			getView().setEditable("importeAutorizado", false);
			
		}

	}

	@Override
	public void setChangedProperty(String propertyName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNewValue(Object value) {
		// TODO Auto-generated method stub

	}

}