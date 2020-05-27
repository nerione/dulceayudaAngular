package com.example.demo.converter;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Contact;
import com.example.demo.model.ContactModel;

import ch.qos.logback.classic.pattern.DateConverter;

@Component
public class ContactConverter {
	
	//Return one Contact Obj
	public Contact contacModel2Contact(ContactModel contactModel) {
		Contact c = new Contact();
		c.setCity(contactModel.getCity());
		c.setDatoInutil(contactModel.getDatoInutil());
		c.setFirstName(contactModel.getFirstName());
		c.setLastName(contactModel.getLastName());
		c.setTelephone(contactModel.getTelephone());
		c.setId(contactModel.getId());
		c.setFechaRegistro(contactModel.getFechaRegistro());
		return c;
	}
	
	
	//Return one ContactModel Obj
	public ContactModel contac2ContactModel( Contact contact) {
		
		ContactModel cm = new ContactModel();
		cm.setCity(contact.getCity());
		cm.setDatoInutil(contact.getDatoInutil());
		cm.setFirstName(contact.getFirstName());
		cm.setId(contact.getId());
		cm.setLastName(contact.getLastName());
		cm.setTelephone(contact.getTelephone());
		cm.setFechaRegistro(contact.getFechaRegistro());
		
		return cm;
	}

}
