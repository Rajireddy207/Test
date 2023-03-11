package Com.Tricentis.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static DataFormatter formatter = new DataFormatter();

	public static String[] readExcel(String fileName, String sheetName) throws IOException {
       //fileName="src//test//resources//PEP_Test data.xlsx";
		
		URL fileUrl = ExcelUtilities.class.getResource(fileName);

		File file = new File(fileName);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		} else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		Row rowFirst = sheet.getRow(0); // Added UST

		int columnCount = rowFirst.getLastCellNum(); // Added UST

		Boolean existLoop = false;

		String[] testData = new String[columnCount];

		String cellTestCaseName;

		for (int i = 1; i < rowCount + 1; i++) {

			Row row = sheet.getRow(i);

			cellTestCaseName = formatter.formatCellValue(row.getCell(0));

			
				for (int j = 0; j < row.getLastCellNum(); j++) {

					testData[j] = formatter.formatCellValue(row.getCell(j));

				}

				existLoop = true;

				break;
		}

		return testData;

	}

}
