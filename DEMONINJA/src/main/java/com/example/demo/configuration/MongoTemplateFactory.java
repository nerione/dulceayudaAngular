package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
//import org.springframework.security.config.core.GrantedAuthorityDefaults;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@SuppressWarnings("deprecation")
@Configuration
public class MongoTemplateFactory {

	@Bean
	public MongoTemplate getMongoTemplate() {
		
		MappingMongoConverter converter = new MappingMongoConverter(getMongoDbFactory(), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory(), converter);
		return mongoTemplate;
	}

	private static MongoDbFactory getMongoDbFactory() {
		//MongoClient mClient = new MongoClient("localhost:27017");
		//Conexion mongo local
		//MongoClient mClient = new MongoClient(new MongoClientURI("mongodb://ninjaneri:ninjaneri@localhost:27017/?authSource=admin"));
		//Conexion mongo remoto Heroku app
		MongoClient mClient = new MongoClient(new MongoClientURI("mongodb://heroku_7hcwm1jc:a5heakp18eep58b27qoedp2isr@ds149353.mlab.com:49353/heroku_7hcwm1jc"));
		//conexion mongo remoto mlab
		SimpleMongoDbFactory s = new SimpleMongoDbFactory(mClient,"heroku_7hcwm1jc");
		//SimpleMongoDbFactory s = new SimpleMongoDbFactory(mClient,"proyectoFinal");
		
		return s;
	}
	
	
	//@Bean
	//GrantedAuthorityDefaults grantedAuthorityDefaults() {
	    //return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
	//}
	
	

}
