package com.example.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Contact;

@Repository
public interface ContactRepository{
	
	//Persistimos un nuevo documento Contacto en BD
	Collection<Contact> createContact(List<Contact> contact);
	
	Contact createContact(Contact contact);
	
	Page<Contact> findAll(Pageable pageable);
		
	//Obtenemos toda la lista de contactos en BD. Debe haber un limite.
	List<Contact> getAllContacts();
	
	//Recuperamos un Contacto por su ID
	Contact getContactById(String id);
	
	//Actualizamos un contacto ya existente en BD
	Contact updateContact(Contact contact);
	
	//Eliminamos logicamente un contacto de la BD por ID.
	long deleteContact(String id);
	
	Contact updateContactById(String id, String file);

}
