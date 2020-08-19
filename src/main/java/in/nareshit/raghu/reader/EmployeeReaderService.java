package in.nareshit.raghu.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import in.nareshit.raghu.model.Employee;

@Component
public class EmployeeReaderService {

	@Value("${app.fileLoc}")
	private String fileLoc;

	public List<Employee> readData(){
		List<Employee> emps = null;
		try {
			emps = new ArrayList<>();
			File file = new File(fileLoc);
			Workbook book = new XSSFWorkbook(file);
			Sheet sheet  = book.getSheet("EMPLOYEES");
			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row row =  itr.next();
				emps.add(
						new Employee(
								(int)row.getCell(0).getNumericCellValue(), 
								row.getCell(1).getStringCellValue(), 
								row.getCell(2).getNumericCellValue()
								)
						);
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emps;
	}


}
