package com.example.demo.utils;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.example.demo.model.Factura;

/*Clase para obtener el token*/
@Component
public class Utilities {

	private static final Logger logger = LoggerFactory.getLogger(Utilities.class);

	public static final String STRINGNUMEROS="1234567890";

	public static final int TAMANIO_TOKEN = 4;


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




	//Metodo para procesar cfdis Emitidas y Recibidas
	public static HashMap<String, Object> procesarFacturas(List<File> archivos){

		String tag = null;
		Float subtotal = 0F;
		Float total = 0F;
		List<Factura> facturas = new ArrayList<>();
		HashMap<String, Object> resultado = new HashMap<String, Object>();

		for(File fileItem : archivos) {



			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				Document document = dbBuilder.parse(fileItem);
				//document.normalize();

				for(int i = 0; i < document.getChildNodes().getLength(); i++) {

					Factura factura = new Factura();
					factura.setFacturaId(fileItem.getName());

					logger.info("Procesando archivo {} ****************************************************************************************", fileItem.getName());
					Node nodePiso1 = document.getChildNodes().item(i);

					//Primer nivel del arbol
					if(nodePiso1.hasAttributes())
						for(int z = 0; z < nodePiso1.getAttributes().getLength(); z++) {

							tag = nodePiso1.getAttributes().item(z).getNodeName();

							switch(tag) {

							case "SubTotal":
								factura.setSubTotal(Float.valueOf(nodePiso1.getAttributes().item(z).getNodeValue()));
								break;
							case "Total":
								factura.setTotal(Float.valueOf(nodePiso1.getAttributes().item(z).getNodeValue()));
								break;
							case "Descripcion":
								factura.setDescripcion(nodePiso1.getAttributes().item(z).getNodeValue());
								break;

							default:
								break;
							}

						}

					//Segundo nivel del arbol
					for(int x=0; x < nodePiso1.getChildNodes().getLength(); x++) {
						//nodo a recorrer
						Node nodePiso2 = nodePiso1.getChildNodes().item(x);

						if(nodePiso2.hasAttributes())
							for(int y = 0; y < nodePiso2.getAttributes().getLength(); y++) {

								tag = nodePiso2.getAttributes().item(y).getNodeName();

								switch(tag) {

								case "SubTotal":
									factura.setSubTotal(Float.valueOf(nodePiso2.getAttributes().item(y).getNodeValue()));
									break;
								case "Total":
									factura.setTotal(Float.valueOf(nodePiso2.getAttributes().item(y).getNodeValue()));
									break;
								case "Descripcion":
									factura.setDescripcion(nodePiso2.getAttributes().item(y).getNodeValue());
									break;

								default:
									break;
								}

							}


						//tercer nivel del arbol
						for(int w=0; w < nodePiso2.getChildNodes().getLength(); w++) {
							//Nodo a recorrer
							Node nodePiso3 = nodePiso2.getChildNodes().item(w);

							if(nodePiso3.hasAttributes())
								for(int a = 0; a < nodePiso3.getAttributes().getLength(); a++) {

									tag = nodePiso3.getAttributes().item(a).getNodeName();

									switch(tag) {

									case "SubTotal":
										factura.setSubTotal(Float.valueOf(nodePiso3.getAttributes().item(a).getNodeValue()));
										break;
									case "Total":
										factura.setTotal(Float.valueOf(nodePiso3.getAttributes().item(a).getNodeValue()));
										break;
									case "Descripcion":
										factura.setDescripcion(nodePiso3.getAttributes().item(a).getNodeValue());
										break;

									default:
										break;
									}

								}



						}

					}

					//Agregamos objeto factura
					if(!(factura.getDescripcion().contains("Pago de n"))) {
						subtotal += factura.getSubTotal();
						total += factura.getTotal();
						facturas.add(factura);
					}
					

				}


			}catch(Exception e) {

				logger.error("Ocurrio un error {} ", e.getCause().toString());

			}


		}
		
		resultado.put("subtotal", subtotal);
		resultado.put("total", total);
		resultado.put("facturas", facturas);
		
		return resultado;

	}


}
