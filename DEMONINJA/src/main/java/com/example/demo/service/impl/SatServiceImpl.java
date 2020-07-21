package com.example.demo.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.demo.model.Factura;
import com.example.demo.model.SatDeclaraResponse;
import com.example.demo.service.SatService;
import com.example.demo.utils.Utilities;


@Service
public class SatServiceImpl implements SatService {
	
	public static final Logger logger = LoggerFactory.getLogger(SatServiceImpl.class);
	
	@Value("${sat.facturas.emitidas.path}")
	private String pathEmitidas;

	@Value("${sat.facturas.recibidas.path}")
	private String pathRecibidas;

	@Override
	public SatDeclaraResponse getResumeFacturas() {		
		
		File filesEmitidas = new File(pathEmitidas);
		File filesRecibidas = new File(pathRecibidas);
		
		ArrayList<File> fileListEmitidas = new ArrayList<File>(Arrays.asList(filesEmitidas.listFiles()));
		ArrayList<File> fileListRecibidas = new ArrayList<File>(Arrays.asList(filesRecibidas.listFiles()));
		
		HashMap<String, Object> facturasEmitidasProcesadas = new HashMap<>();
		HashMap<String, Object> facturasRecibidasProcesadas = new HashMap<>();
				
		SatDeclaraResponse response = new SatDeclaraResponse();
		
		facturasEmitidasProcesadas = Utilities.procesarFacturas(fileListEmitidas);
		facturasRecibidasProcesadas = Utilities.procesarFacturas(fileListRecibidas);
		
		
		response.setTotalRecibidas(Float.toString((Float)facturasRecibidasProcesadas.get("total")));
		response.setSubTotalRecibidas(Float.toString((Float)facturasRecibidasProcesadas.get("subtotal")));
		response.setFacturasRecibidas((List<Factura>)facturasRecibidasProcesadas.get("facturas"));
		
		response.setTotalEmitidas(Float.toString((Float)facturasEmitidasProcesadas.get("total")));
		response.setSubTotalEmitidas(Float.toString((Float)facturasEmitidasProcesadas.get("subtotal")));
		response.setFacturasEmitidas((List<Factura>)facturasEmitidasProcesadas.get("facturas"));
		

		return response;

	}

}
