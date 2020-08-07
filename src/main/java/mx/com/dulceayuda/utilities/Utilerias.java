package mx.com.dulceayuda.utilities;

import java.security.SecureRandom;

public class Utilerias {
	
	public static final String STRINGNUMEROS="1234567890";

	public static final int TAMANIO_TOKEN = 6;
	
	//GENERACION OTP PARA VALIDACION NUM TEL	
		public static String generateOTP()
		{

			StringBuilder builder = new StringBuilder();
			SecureRandom ranGen = new SecureRandom();

			for (int i = 0; i < TAMANIO_TOKEN ; i++) {
				int character = ranGen.nextInt(STRINGNUMEROS.length());
				builder.append(character);
			}
			System.out.println(builder);
			return builder.toString();
		}

}
