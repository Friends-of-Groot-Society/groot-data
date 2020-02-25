package net.groot.utility;

import java.util.ArrayList;
import net.groot.utility.XlsReader;

public class DataRegisterUtil { 
	static XlsReader reader;
//	
//	public static ArrayList<Object[]> getDataFromExcel() {
//		
//		ArrayList<Object[]> newData = new ArrayList<Object[]>();
//		try {
//			reader = new XlsReader(
//					System.getProperty("user.dir")
//					+ "src/main/java/resources/userData/excel/grootData.xls");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		for (int rowNum = 2; rowNum <= reader.getRowCount("register"); rowNum++) {
//			
//			String email = reader.getCellData("register", "email", rowNum); 
//			String password = reader.getCellData("register", "password", rowNum); 
//			String fName = reader.getCellData("register", "fName", rowNum); 
//			String lName = reader.getCellData("register", "lName", rowNum); 
//			
//			Object obj[] = { email, password, fName, lName };
//			newData.add(obj);
//		}
//		return newData;
//	}
}
