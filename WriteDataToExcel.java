package Sales_details_R_and_R_Imports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataToExcel {

	private  static String outputFile;   
    
    public WriteDataToExcel(String filePath) {
    	outputFile = filePath;
    }
    public  void writeExcel(HashMap<Integer, Object[]> data) throws FileNotFoundException, IOException  {   
   
  
    	 // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();
  
        // spreadsheet object
        XSSFSheet spreadsheet
            = workbook.createSheet(" Combined Category Sales Data ");
  
        // creating a row object
        XSSFRow row, firstrow;
        int firstCellId = 0;
        firstrow = spreadsheet.createRow(0);
        
        Object[] columnName = {"OrderItemSku", "OrderItemDescription", "OrderItemQuantity", "OrderItemUnitPrice"};
        for (Object obj : columnName) {
        	Cell firstcell = firstrow.createCell(firstCellId++);
        	firstcell.setCellValue((String)obj);
        }
        
        
        
        
        Set<Integer> keyid = data.keySet();
  
        for (Integer key : keyid) {
  
            row = spreadsheet.createRow(key);
            Object[] objectArr = data.get(key);
            int cellid = 0;
  
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }
  
        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream(
            new File(outputFile));
  
        workbook.write(out);
        out.close();
    }
    
}
