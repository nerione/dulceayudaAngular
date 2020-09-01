package mx.com.dulceayuda.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuariosConteo")
public class UsuarioSequenceEntity implements Serializable {
	
	
	private static final long serialVersionUID = 9177563211265822479L;
	
	@Id
	private String id;
	private int valor;

}
