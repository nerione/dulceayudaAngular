package com.example.demo.threads;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class Outlet {
	
	
	public static void main(String...strings) throws Exception{
		
		try {
			
			PigFactory cerdo = new PigFactory();
			cerdo.setEat("PAPILLA");
			cerdo.setId(1);
			cerdo.setNombre("BABE");
			
			PigFactory cerdoClon = new PigFactory();
			PigFactory cerdoNulo = null;
			
			System.out.println();
			
			Assert.notNull(cerdoNulo, "El cerdo no esta vacio");//(cerdo);
			
			BeanUtils.copyProperties(cerdo, cerdoClon);
			
			System.out.println("Cerdo original :" + cerdo.toString());
			System.out.println("Cerdo Clonado :" + cerdoClon.toString());
			
		}catch(Exception e) {
			System.out.println("Falló");
			System.out.println(e.toString());
		}		
		
		
		
	}
	
	
	
}
