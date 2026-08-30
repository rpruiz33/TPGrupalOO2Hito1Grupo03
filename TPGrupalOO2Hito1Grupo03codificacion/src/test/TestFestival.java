package test;

import java.time.LocalDate;

import negocio.CajeroABM;
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
 		
		CajeroABM c=new CajeroABM();
		try {
			System.out.println(c.agregar("Perez", "Juan", "11111111", LocalDate.of(2222,2,2), LocalDate.of(2222,2,12), 235365.12, "manana"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CajeroABM c1=new CajeroABM();
		try {
			System.out.println(c1.agregar("Perez", "Juan", "11111111", LocalDate.of(2222,2,2), LocalDate.of(2222,2,12), 235365.12, "manana"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
