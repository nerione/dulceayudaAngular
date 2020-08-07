package mx.com.dulceayuda.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoDb {
	
	@Bean
	public MongoTemplate getMongoTemplate() {
		
		//MappingMongoConverter converter = new MappingMongoConverter(getMongoDbFactory(), new MongoMappingContext());
		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(getMongoDbFactory()), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory(), converter);
		return mongoTemplate;
	}

	private static MongoDbFactory getMongoDbFactory() {
		//Conexion mongo local
		MongoClient mClient = new MongoClient(new MongoClientURI("mongodb://ninjaneri:ninjaneri@localhost:27017/?authSource=admin"));
		
		SimpleMongoDbFactory s = new SimpleMongoDbFactory(mClient,"dulceayudaportal");
		
		return s;
	}

}
