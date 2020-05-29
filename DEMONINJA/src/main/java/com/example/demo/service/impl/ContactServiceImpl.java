package com.example.demo.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactRepository, PagingAndSortingRepository<Contact, String>{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ContactServiceImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
		
	Query query;

	@Override
	public Collection<Contact> createContact(List<Contact> contact) {
		return mongoTemplate.insertAll(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return mongoTemplate.findAll(Contact.class);
	}

	@Override
	public Contact getContactById(String id) {
		Query q = new Query();
		q.addCriteria(new Criteria().where("_id").is(id));
		return mongoTemplate.findOne(q, Contact.class);
	}

	@Override
	public Contact updateContact(Contact contact) {
		Query query = new Query();
		query.addCriteria(new Criteria().where("_id").is(contact.getId()));
		Contact c = mongoTemplate.findOne(query, Contact.class);
		if(c != null) {
			mongoTemplate.save(contact);
			log.info("El contacto fue actualizado correctamente " + contact.toString());
		}
		
		return null;
	}

	@Override
	public long deleteContact(String id) {
		Query query = new Query();
		
		query.addCriteria(new Criteria().where("_id").is(id));
		Contact c = mongoTemplate.findOne(query, Contact.class);
		if(c != null) {
			
			return mongoTemplate.remove(c).getDeletedCount();
			
		}
		
		return 0;
	}

	@Override
	public Contact createContact(Contact contact) {
		int n = mongoTemplate.findAll(Contact.class).size();
		contact.setId(Integer.toString(++n));
		return mongoTemplate.save(contact);

	}



	@Override
	public Page<Contact> findAll(Pageable pageable) {
		Query query = new Query();
		query.with(pageable);
		log.info("TOTAL REGISTROS: " + mongoTemplate.count(query, Contact.class));
		List<Contact> result = mongoTemplate.find(query, Contact.class);
		return PageableExecutionUtils.getPage(result, pageable, () -> mongoTemplate.count(new Query(), Contact.class));
	}
	
	
	@Override
	public Contact updateContactById(String id, String file) {
		Query query = new Query();
		query.addCriteria(new Criteria().where("_id").is(id));
		Contact c = mongoTemplate.findOne(query, Contact.class);
		if(c != null) {
			c.setFile(file);
			mongoTemplate.save(c);
			log.info("El contacto fue actualizado correctamente " + c.toString());
		}
		
		return null;
	}

	@Override
	public <S extends Contact> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Contact> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Contact> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Contact> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Contact> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Contact entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Contact> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Contact> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
