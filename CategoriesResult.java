
package Sales_details_R_and_R_Imports;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Microsoft
 */
public class CategoriesResult {
	private FileInputStream filePath;
	private String ctgName;
	private ArrayList<Cell> ctgList;
	private ArrayList<Double> qtyList;
	private ArrayList<Double> priceList;
	private ArrayList<Double> shipAmtList;
	private HashMap<Integer, Object[]> ctgData;

	public CategoriesResult(FileInputStream file1, String ctgName) {
		this.filePath = file1;
		this.ctgName = ctgName;
		ctgList = new ArrayList<Cell>();
		qtyList = new ArrayList();
		priceList = new ArrayList();
		shipAmtList = new ArrayList();
		ctgData = new HashMap<Integer, Object[]>();
	}

	public HashMap<Integer, Object[]> getFile1Columns() throws IOException, InvalidFormatException {
		XSSFWorkbook workbook;
		Integer val =  2;
		try {
			workbook = (XSSFWorkbook) WorkbookFactory.create(filePath);
			// Get the first sheet.
			XSSFSheet sheet = workbook.getSheetAt(0);
			// we will search for below column names in first row
			String ctgColName = "OrderItemSku";
			String descColName = "OrderItemDescription";
			String qtyColName = "OrderItemQuantity";
			String priceColName = "OrderItemUnitPrice";
			String shipAmtColName = "OrderItemShippingAmount";

			Integer ctgColNo = null, descColNo=null, qtyColNo = null, priceColNo = null, shipAmtColNo = null;

			// output all not null values to the list

			// Get the first cell to find the column names.
			Row row = sheet.getRow(0);
			for (Cell cell : row) {
				// get column numbers for specified column names.
				if (cell.getStringCellValue().equals(ctgColName)) {
					ctgColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(descColName)) {
					descColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(qtyColName)) {
					qtyColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(priceColName)) {
					priceColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(shipAmtColName)) {
					shipAmtColNo = cell.getColumnIndex();
				}
			}
			if (ctgColNo != null) {
				for (Row row1 : sheet) {
					// excluding first row column heading to get the values of columns not heading
					if (row1.getRowNum() != 0) {
						Cell ctgCell = row1.getCell(ctgColNo);
						Cell descCell = row1.getCell(descColNo);
						Cell qtyCell = row1.getCell(qtyColNo);
						Cell priceCell = row1.getCell(priceColNo);
						Cell shipAmtCell = row1.getCell(shipAmtColNo);

						// convert these cells to double for calculations
						double qtyVal, priceVal, shipAmtVal;
						if (qtyCell == null) {
							qtyVal = 0;
						} else {
							qtyVal = qtyCell.getNumericCellValue();
						}
						if (priceCell == null) {
							priceVal = 0;
						} else {
							priceVal = priceCell.getNumericCellValue();
						}
						if (shipAmtCell == null) {
							shipAmtVal = 0;
						} else {
							shipAmtVal = shipAmtCell.getNumericCellValue();
						}
						String ctgName = ctgCell.getStringCellValue();
						String descName = descCell.getStringCellValue();
						// find specific ctg in each row of SKU column
						ArrayList<String> ctg = findCtg();
						boolean checkCtg = false;
						if (ctg.get(0)=="") {
							if (ctgName.contains("-CMP-") ||ctgName.contains("GATTN")|| ctgName.contains("-S-WVU") || ctgName.contains("SMKYMNT") || ctgName.contains("GAPEACH18") || ctgName.contains("CO flag") || ctgName.contains("S-Asheville18") || ctgName.contains("BIGBEND") || ctgName.contains("-C-") || ctgName.contains("-N-")
									|| ctgName.contains("-SKI-") || ctgName.contains("-CTY-")|| ctgName.contains("ROSW18")|| ctgName.contains("NAPA17")|| ctgName.contains("BOSTON17")|| ctgName.contains("Nashville")|| ctgName.contains("-CA-")||
									ctgName.contains("-CAL-")|| ctgName.contains("-TX-")|| ctgName.contains("-FL-")|| ctgName.contains("DALLAS17")|| ctgName.contains("CAL18")||
									ctgName.contains("RVA17")|| ctgName.contains("-LA18")|| ctgName.contains("-MD-")|| ctgName.contains("CAL18")|| ctgName.contains("S-TX17")||ctgName.contains("-RT-")|| 
									ctgName.contains("-LK-")|| ctgName.contains("-P-")|| ctgName.contains("CUSTOM")|| ctgName.contains("GRAD")|| 
									ctgName.contains("-HS-")|| ctgName.contains("-LK-")) {
								checkCtg = false;
							} else {
								checkCtg = true;
							}
						} else {
							for(String str : ctg) {
								if(ctgName.contains(str)) {
									checkCtg = true;
									break;
								}
							}
						}

						if (ctgCell == null) {
							// Nothing in the cell in this row, skip it
						} else {
							if (checkCtg) {
								ctgList.add(ctgCell);
								qtyList.add(qtyVal);
								priceList.add(priceVal);
								shipAmtList.add(shipAmtVal);
								ctgData.put(val, new String[] {ctgName, descName, String.valueOf(qtyVal), String.valueOf(priceVal)});
	                              val++;
							}
						}
					}

				}

			} else {
				System.out.println("could not find column ");
			}
		} catch (EncryptedDocumentException ex) {
			Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ctgData;
	}

	public HashMap<Integer, Object[]> getFile2Columns(Integer val) throws IOException, InvalidFormatException {
		XSSFWorkbook workbook;
		
		try {
			workbook = (XSSFWorkbook) WorkbookFactory.create(filePath);
			// Get the first sheet.
			XSSFSheet sheet = workbook.getSheetAt(0);
			// we will search for below column names in first row
			String ctgColName = "sku";
			String descColName = "description";
			String qtyColName = "quantity";
			String salesColName = "product sales";
			String shipAmtColName = "shipping credits";
			String amazonColName = "fulfillment";

			Integer ctgColNo = null, descColNo = null, qtyColNo = null, salesColNo = null, shipAmtColNo = null, amazonColNo = null;

			// Get the first cell to find the column names.
			Row row = sheet.getRow(0);
			for (Cell cell : row) {
				// get column numbers for specified column names.
				if (cell.getStringCellValue().equals(ctgColName)) {
					ctgColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(descColName)) {
					descColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(qtyColName)) {
					qtyColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(salesColName)) {
					salesColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(shipAmtColName)) {
					shipAmtColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(amazonColName)) {
					amazonColNo = cell.getColumnIndex();
				}
			}
			if (ctgColNo != null) {
				for (Row row1 : sheet) {
					// excluding first row column heading to get the values of columns not heading
					if (row1.getRowNum() != 0) {
						Cell ctgCell = row1.getCell(ctgColNo);
						Cell descCell = row1.getCell(descColNo);
						Cell qtyCell = row1.getCell(qtyColNo);
						Cell salesCell = row1.getCell(salesColNo);
						Cell shipAmtCell = row1.getCell(shipAmtColNo);
						Cell amazonCell = row1.getCell(amazonColNo);
						// convert these cells to double for calculations
						double qtyVal, salesVal, shipAmtVal;
						if (qtyCell == null) {
							qtyVal = 0;
						} else {
							qtyVal = qtyCell.getNumericCellValue();
						}
						if (salesCell == null) {
							salesVal = 0;
						} else {
							salesVal = salesCell.getNumericCellValue();
						}
						if (shipAmtCell == null) {
							shipAmtVal = 0;
						} else {
							shipAmtVal = shipAmtCell.getNumericCellValue();
						}
						String ctgStr;
						String descStr = descCell.getStringCellValue();
						if(ctgCell!=null) {
							ctgStr = ctgCell.getStringCellValue();
						}else {
							ctgStr=null;
						}
						String amazonVal;
						if(amazonCell!=null) {
							amazonVal = amazonCell.getStringCellValue();
						}else {
							amazonVal=null;
						}
						// find specific ctg in each row of SKU column
						ArrayList<String> ctg = findCtg();
						boolean checkCtg = false;
						if (ctg.get(0)=="") {
							if(ctgStr!=null) {
								if (ctgStr.contains("-CMP-") || ctgStr.contains("GATTN")|| ctgStr.contains("-S-WVU") || ctgStr.contains("SMKYMNT") || ctgStr.contains("GAPEACH18") || ctgStr.contains("CO flag") || ctgStr.contains("S-Asheville18") || ctgStr.contains("BIGBEND") || ctgStr.contains("-C-") || ctgStr.contains("-N-")
										|| ctgStr.contains("-SKI-") || ctgStr.contains("-CTY-")|| ctgStr.contains("ROSW18")|| ctgStr.contains("NAPA17")|| ctgStr.contains("BOSTON17")|| ctgStr.contains("Nashville")|| ctgStr.contains("-CA-")||
										ctgStr.contains("-CAL-")|| ctgStr.contains("-TX-")|| ctgStr.contains("-FL-")|| ctgStr.contains("DALLAS17")|| ctgStr.contains("CAL18")||
										ctgStr.contains("RVA17")|| ctgStr.contains("-LA18")|| ctgStr.contains("-MD-")|| ctgStr.contains("CAL18")|| ctgStr.contains("S-TX17")||ctgStr.contains("-RT-")|| 
										ctgStr.contains("-LK-")|| ctgStr.contains("-P-")|| ctgStr.contains("CUSTOM")|| ctgStr.contains("GRAD")|| 
										ctgStr.contains("-HS-")|| ctgStr.contains("-LK-")) {
									checkCtg = false;
								} else {
									checkCtg = true;
								}
							}
							
						} else {
							if(ctgStr!=null) {
                        		for(String str : ctg) {
    								if(ctgStr.contains(str)) {
    									checkCtg = true;
    									break;
    								}
    								
    							}
                        	}
							
						}
						if (ctgCell == null) {
							// Nothing in the cell in this row, skip it
						} else {
							if (checkCtg && ctgStr!=null && amazonVal!=null) {
								// check in column j if it contains value Amazon then add calculations else
								// leave
								if (amazonVal.equals("Amazon")) {
									ctgList.add(ctgCell);
									qtyList.add(qtyVal);
									// calculate unit price by dividing column O with G
									priceList.add(salesVal / qtyVal);
									shipAmtList.add(shipAmtVal);
									
									ctgData.put(val, new String[] {ctgStr, descStr, String.valueOf(qtyVal), String.valueOf(salesVal / qtyVal)});
		                              val++;
								}

							}
						}
					}
					
				}
				
			} else {
				System.out.println("could not find column ");
			}
		} catch (EncryptedDocumentException ex) {
			Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ctgData;
	}

	private ArrayList findCtg() {
		ArrayList<String> ctg = new ArrayList<String>();
		if (ctgName == "Camping") {
			ctg.add("-CMP-");
			ctg.add("GATTN");
			ctg.add("-S-WVU");
			ctg.add("SMKYMNT");
			ctg.add("GAPEACH18");
			ctg.add("CO flag");
			ctg.add("S-Asheville18");
			ctg.add("BIGBEND");
		} else if (ctgName == "College") {
			ctg.add("-C-");
		} else if (ctgName == "NASCAR") {
			ctg.add("-N-");
		} else if (ctgName == "SKI") {
			ctg.add("-SKI-");
		} else if (ctgName == "City") {
			ctg.add("-CTY-");
			ctg.add("ROSW18");
			ctg.add("NAPA17");
			ctg.add("BOSTON17");
			ctg.add("Nashville");
			ctg.add("-CA-");
			ctg.add("-CAL-");
			ctg.add("-TX-");
			ctg.add("-FL-");
			ctg.add("DALLAS17");
			ctg.add("CAL18");
			ctg.add("RVA17");
			ctg.add("-LA18");
			ctg.add("-MD-");
			ctg.add("CAL18");
			ctg.add("S-TX17");
			
		}else if (ctgName == "Realtree") {
			ctg.add("-RT-");	
		}else if (ctgName == "Pet") {
			ctg.add("-P-");	
		}else if (ctgName == "CUSTOM") {
			ctg.add("CUSTOM");	
		}else if (ctgName == "Graduation") {
			ctg.add("GRAD");	
		}else if (ctgName == "High School") {
			ctg.add("-HS-");	
		}else if (ctgName == "Lake") {
			ctg.add("-LK-");	
		}
		else {
			ctg.add("");// for Uncategorized
		}

		return ctg;
	}

	public double calculateTotalSales() {
		double ctgSales = 0.0;
		int val = 0;

		while (qtyList.size() > val) {
			double sales = ((qtyList.get(val) * priceList.get(val)) + shipAmtList.get(val));
			ctgSales += sales;

			val++;
		}

		return ctgSales;
	}

	public double calculateUnitsSold() {
		double ctgUnitSold = 0.0;
		int val = 0;
		while (qtyList.size() > val) {
			ctgUnitSold += qtyList.get(val);
			val++;
		}

		return ctgUnitSold;
	}

	public double calculateShippingAmt() {
		double ctgShipAmt = 0.0;
		int val = 0;
		while (this.shipAmtList.size() > val) {
			ctgShipAmt += this.shipAmtList.get(val);
			val++;
		}
		return ctgShipAmt;
	}
}
