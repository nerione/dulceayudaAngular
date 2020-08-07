package mx.com.dulceayuda.exceptions;


public class AltaUsuarioException extends Exception {
	
	
	private static final long serialVersionUID = 7772612093421770644L;
	private int codigo;
	
	
	public AltaUsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public AltaUsuarioException(String message, int codigo) {
		super(message);
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}

}
