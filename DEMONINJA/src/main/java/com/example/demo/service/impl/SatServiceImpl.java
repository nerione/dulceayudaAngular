package com.example.demo.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.example.demo.model.Factura;
import com.example.demo.model.SatDeclaraResponse;
import com.example.demo.service.SatService;


@Service
public class SatServiceImpl implements SatService {
	
	private static final String CONCEPTO = "Pago de nómina";

	public static final Logger logger = LoggerFactory.getLogger(SatServiceImpl.class);

	@Override
	public SatDeclaraResponse getResumeFacturas(ArrayList<File> fileListEmitidas, ArrayList<File> fileListRecibidas) {
		
		

		SatDeclaraResponse respuesta = new SatDeclaraResponse();
		
		Float granTotalEmitidas = 0F, granSubTotalEmitidas = 0F, granTotalRecibidas = 0F, granSubTotalRecibidas = 0F;
		List<Factura> facturasEmitidas = new ArrayList<>();
		List<Factura> facturasRecibidas = new ArrayList<>();
		String tag = null;


		for(File fileItem : fileListEmitidas) {



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
					granSubTotalEmitidas += factura.getSubTotal();
					granTotalEmitidas += factura.getTotal();
					facturasEmitidas.add(factura);

				}


			}catch(Exception e) {
				
				logger.error("Ocurrio un error {} ", e.getCause().toString());

			}
			
			
		}
		
		respuesta.setSubTotalEmitidas(Float.toString(granSubTotalEmitidas));
		respuesta.setTotalEmitidas(Float.toString(granTotalEmitidas));
		respuesta.setFacturasEmitidas(facturasEmitidas);
		
		
		
		for(File fileItem : fileListRecibidas) {



			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				Document document = dbBuilder.parse(fileItem);
				//document.normalize();

				for(int i = 0; i < document.getChildNodes().getLength(); i++) {
					
					Factura factura = new Factura();
					factura.setFacturaId(fileItem.getName());

					logger.info("Procesando FACTURAS RECIBIDAS {} ****************************************************************************************", fileItem.getName());
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
					
					if(!(factura.getDescripcion().contains("Pago de n"))) {
						granSubTotalRecibidas += factura.getSubTotal();
						granTotalRecibidas += factura.getTotal();
						facturasRecibidas.add(factura);
					}

				}


			}catch(Exception e) {
				
				logger.error("Ocurrio un error {} ", e.getCause().toString());

			}
		}
		
		respuesta.setFacturasRecibidas(facturasRecibidas);
		respuesta.setSubTotalRecibidas(Float.toString(granSubTotalRecibidas));
		respuesta.setTotalRecibidas(Float.toString(granTotalRecibidas));

		return respuesta;

	}

}
