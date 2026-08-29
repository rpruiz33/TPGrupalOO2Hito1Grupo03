package test;

import java.time.LocalDate;

import negocio.FestivalABM;

public class TestFestival {

	public static void main(String[] args) {
		
		
		FestivalABM f=new FestivalABM();
		try {
			System.out.println(f.agregar("roberto", "verano", LocalDate.of(2,2,2),LocalDate.of(2,2,2) , null, null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		

	}

}
