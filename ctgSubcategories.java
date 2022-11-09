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
public class ctgSubcategories {
	private FileInputStream filePath;
	private String ctgName;
	private String subCtgName;
	private ArrayList<String> ctgList;
	private ArrayList<String> subCtgList;
	private ArrayList<Double> qtyList;
	private ArrayList<Double> priceList;
	private ArrayList<Double> shipAmtList;
	private ArrayList<String> itemName;
	private ArrayList<Double> itemSales;

	public ctgSubcategories(FileInputStream file1, String ctgName, String subCtgName) {
		this.filePath = file1;
		this.ctgName = ctgName;
		ctgList = new ArrayList();
		qtyList = new ArrayList();
		priceList = new ArrayList();
		shipAmtList = new ArrayList();
		itemName = new ArrayList();
		itemSales = new ArrayList();
		subCtgList = new ArrayList();
		this.subCtgName = subCtgName;
	}

	public ArrayList getItemName() {
		return itemName;
	}

	public ArrayList getItemSales() {
		return itemSales;
	}

	public void getFile1Columns(String subCtgColName, String ctgColName, String qtyColName, String priceColName,
			String shipAmtColName) throws IOException, InvalidFormatException {
		XSSFWorkbook workbook;
		WriteDataToExcel poiWriteData = new WriteDataToExcel("") ;
		HashMap<Integer, Object[]> ctgDetails = new HashMap<Integer, Object[]>();
		int val=0;
		try {
			workbook = (XSSFWorkbook) WorkbookFactory.create(filePath);
			// Get the first sheet.
			XSSFSheet sheet = workbook.getSheetAt(0);
			// we will search for below column names in first row

			Integer ctgColNo = null, qtyColNo = null, priceColNo = null, shipAmtColNo = null, subCtgColNo = null;

			// Get the first cell to find the column names.
			Row row = sheet.getRow(0);
			for (Cell cell : row) {
				// get column numbers for specified column names.
				if (cell.getStringCellValue().equals(ctgColName)) {
					ctgColNo = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().equals(subCtgColName)) {
					subCtgColNo = cell.getColumnIndex();
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
						Cell subCtgCell = row1.getCell(subCtgColNo);
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
							shipAmtVal = 0.0;
						} else {
							shipAmtVal = shipAmtCell.getNumericCellValue();
						}
						String ctgStr;
						if(ctgCell!=null) {
							ctgStr = ctgCell.getStringCellValue();
						}else {
							ctgStr=null;
						}

						String subCtgString = subCtgCell.getStringCellValue();

						// find specific ctg in each row of SKU column
						ArrayList<String> ctg = findCtg();

						String subCtg = findSubCtg(subCtgString);
						// System.out.println("++"+subCtg);
						boolean checkCtg = false;
						boolean checkSubCtg = false;
						 if(ctg.get(0)==""){
							 if(ctgStr!=null) {
								 if(ctgStr.contains("-CMP-") || ctgStr.contains("GATTN")|| ctgStr.contains("-S-WVU") || ctgStr.contains("SMKYMNT") || ctgStr.contains("GAPEACH18") || ctgStr.contains("CO flag") || ctgStr.contains("S-Asheville18") || ctgStr.contains("BIGBEND") || ctgStr.contains("-C-") || ctgStr.contains("-N-")
		 									|| ctgStr.contains("-SKI-") || ctgStr.contains("-CTY-")|| ctgStr.contains("ROSW18")|| ctgStr.contains("NAPA17")|| ctgStr.contains("BOSTON17")|| ctgStr.contains("Nashville")|| ctgStr.contains("-CA-")||
		 									ctgStr.contains("-CAL-")|| ctgStr.contains("-TX-")|| ctgStr.contains("-FL-")|| ctgStr.contains("DALLAS17")|| ctgStr.contains("CAL18")||
		 									ctgStr.contains("RVA17")|| ctgStr.contains("-LA18")|| ctgStr.contains("-MD-")|| ctgStr.contains("CAL18")|| ctgStr.contains("S-TX17")||ctgStr.contains("-RT-")|| 
		 									ctgStr.contains("-LK-")|| ctgStr.contains("-P-")|| ctgStr.contains("CUSTOM")|| ctgStr.contains("GRAD")|| 
		 									ctgStr.contains("-HS-")|| ctgStr.contains("-LK-")){
		                                 checkCtg = false;
		                             }else{
		                                 checkCtg = true;
		                             }
							 }
                             
                         }else {
                        	 if(ctgStr!=null) {
                        		 for(String str : ctg) {
          							if(ctgStr.contains(str)) {
          								checkCtg = true;
          								break;
          							}
          							
          						} 
                        	 }
                        	
                         }

						String[] cmpSubCtg = {"Explore the Outdoors", "Adventure Awaits", "Bear", "The Great Outdoors",
								"Tent", "Happy Camper" };

						String[] clgSubCtg = {"Abilene Christian University", "Alabama Crimson Tide",
								"Appalachian State", "Auburn Tigers", "Clemson", "Colorado Buffaloes",
								"Colorado State Rams", "Florida State", "Georgia Bulldogs", "Gonzaga", "Iowa Hawkeyes",
								"Iowa State", "Kansas Jayhawks", "Maryland", "Michigan State", "Penn State",
								"Radford University", "Stanford University", "Texas Tech", "Tulane",
								"University of Houston", "University of Tampa Spartans", "Utah Utes",
								"Virginia Cavaliers", "Akron Zips", "Alabama State University",
								"Albany State University", "Alcorn State", "Arkansas Razorbacks", "Arkansas State",
								"Arkansas Razorbacks", "Marquette Golden Eagles", "South Dakota State",
								"Ball State University", "Marshall Thundering Herd", "South Florida Bulls",
								"Baylor Bears ", "Memphis Tigers", "Southern Illinois", "Boise State Broncos",
								"Mercer University", "Southern Methodist", "Boston Terriers", "Miami Hurricanes ",
								"Southern Mississippi", "Bowling Green Falcons", "Michigan Wolverines",
								"Southern Utah University", "Brigham Young Cougars", "Midwestern State University",
								"Southwestern Oklahoma", "Butler Bulldogs", "Minnesota Duluth Bulldogs",
								"Stephen F. Austin State University", "California State University",
								"Minnesota Gophers", "Syracuse Orange", "Campbell University", "Mississippi Rebels",
								"Tarleton State University", "Category Submitted Under", "Mississippi State Bulldogs",
								"Texas A&M Aggies ", "Central Arkansas Bears", "Missouri Tigers",
								"Texas A&M Kingsville Javelinas", "Central Michigan University",
								"Montana State Bobcats", "Texas Christian University", "Christopher Newport",
								"Montana Tech", "Texas Southern University", "Coastal Carolina University",
								"Morehead State University", "Texas State Bobcats", "Colorado State Rams",
								"Murray State University", "The University of Texas at Tyler", "Davidson College",
								"NC State Wolfpack ", "Toledo Rockets", "Dayton Flyers", "Nebraska Cornhuskers ",
								"Troy University", "Delaware Blue Hens", "New Mexico Lobos", "Truman State University",
								"East Carolina Pirates", "New Mexico State University", "Tuskegee University",
								"East Central University Tigers", "Norfolk State University", "UC Davis Aggies",
								"East Stroudsburg University", "North Carolina A&T", "UCLA Bruins",
								"East Tennessee State University", "North Carolina Central Eagles", "UNC Tar Heels",
								"East Texas Baptist University", "North Carolina Charlotte",
								"University of Central Oklahoma", "Edinboro University", "North Carolina Greensboro",
								"University of Denver Pioneers", "Elizabeth City", "North Carolina Wilmington",
								"University of Illinois at Chicago", "Elon University", "North Dakota Fighting Hawks",
								"University of Louisiana Monroe", "Fayetteville State University",
								"North Dakota State Bison", "University of Southern Indiana", "Florida A&M Rattlers",
								"North Georgia Nighhawks", "University of Tennessee at Chattanooga", "Florida Gators",
								"North Texas", "University of Texas of the Permian Basin", "Florida Gulf Coast Eagles",
								"Northeastern State University", "UNLV Rebels", "Fort Valley State University",
								"Northern Colorado Bears", "UTSA Road Runners", "Fresno State Bulldogs",
								"Northern Illinois Huskies", "Valparaiso University", "Georgia Southern Eagles",
								"Northern Iowa Panthers", "Villanova Wildcats", "Georgia Tech Yellow Jackets",
								"Northwestern Oklahoma State University", "Virginia Cavaliers ",
								"Grambling State Tigers", "Northwestern State Demons", "Hokies ",
								"Grand Canyon University", "Northwestern University", "VMI Keydets",
								"Hampton University", "Ohio Wesleyan University", "Washington & Lee Generals",
								"Idaho Vandals", "Oklahoma State", "West Texas A&M Buffaloes",
								"Illinois Fighting Illini ", "Old Dominion Monarchs", "West Virginia Mountaineers",
								"Illinois State Redbirds", "Oregon State Beavers", "Western Carolina University",
								"Indiana Hoosiers", "Pittsburgh Panthers ", "Western Kentucky Hilltoppers",
								"James Madison Dukes", "Plymouth State", "Western New Mexico", "Kansas State Wildcats",
								"Purdue Boilermakers", "Wichita State Shockers", "Kennesaw State University",
								"Richmond Spiders", "William and Mary", "Kentucky Wildcats", "Rider University Broncs",
								"Winthrop University", "Louisiana Tech Bulldogs", "Rutgers Scarlet Knights",
								"Wyoming Cowboys", "Louisville Cardinals ", "Savannah State University",
								"Loyola University Ramblers", "Shaw University Bears", "Lubbock Christian University",
								"South Dakota Coyotes" };

						String[] skiSubCtg = {"Goggles", "Board Design", "Mountain" };

						String[] nSubCtg = {"Alex Bowman", "Aric Almirola", "Austin Cindric", "Brad Keselowski",
								"Clint Bowyer", "Chase Briscoe", "Chase Elliott", "Chris Buescher", "Christopher Bell",
								"Cole Custer", "Denny Hamlin", "Erik Jones", "Harrison Burton", "Joey Logano",
								"Dale Jr", "Kevin Harvick", "Kurt Busch", "Kyle Busch", "Kyle Larson",
								"Martin Truex Jr", "Matt DiBenedetto", "Noah Gragson", "Ryan Blaney", "William Byron" };

						String[] ctySubCtg = {"Retro", "Sunset", "Neon" };

						String[] custSubCtg = {"Shot glass", "Tumbler", "Plate frame", "Sign", "Sticker", "Magnet" };

						String[] gradSubCtg = {"Shot glass", "Tumbler", "Plate frame", "Sign", "Sticker", "Magnet" };

						String[] rtSubCtg = {"Lanyard", "Plate frame", "Tumbler", "Sticker" };

						String[] petSubCtg = {"Lanyard", "Plate frame", "Tumbler", "Sticker", "Mailbox", "Sign",
								"Magnet" };
						String[] hsSubCtg = {"Lanyard", "Plate frame", "Tumbler", "Sticker", "Mailbox", "Sign",
								"Magnet" };
						String[] lkSubCtg = {"Paddle", "I Love" };
						String[] beachSubCtg = {"Wave Design", "Turtle", "Palm", "Flip Flop", "Octopus", "Compas", "Sunset", "Nautical"};

                        String[] currCtgArr = new String[200];
                        if(subCtg.equals("")){
                            //check for uncategorized category
                               if(ctgName.equals("Camping")){
                                if(containsWords(subCtgString, cmpSubCtg)==false){
                                    checkSubCtg=true;
                                }
                            }else if(ctgName.equals("College")){
                                if(containsWords(subCtgString, clgSubCtg)==false){
                                    checkSubCtg=true;
                                }
                            }else if(ctgName.equals("SKI")){
                                if(containsWords(subCtgString, skiSubCtg)==false){
                                    checkSubCtg=true;
                                }
                            }else if(ctgName.equals("NASCAR")){
                                if(containsWords(subCtgString, nSubCtg)==false){
                                    checkSubCtg=true;
                                }
                            }else if(ctgName.equals("City")){
                                if(containsWords(subCtgString, ctySubCtg)==false){
                                    checkSubCtg=true;
                            }else if (ctgName.equals("Realtree")) {
								if (containsWords(subCtgString, rtSubCtg) == false) {
									checkSubCtg = true;
								}
							} else if (ctgName.equals("Pet")) {
								if (containsWords(subCtgString, petSubCtg) == false) {
									checkSubCtg = true;
								}
							} else if (ctgName.equals("CUSTOM")) {
								if (containsWords(subCtgString, custSubCtg) == false) {
									checkSubCtg = true;
								}
							} else if (ctgName.equals("Graduation")) {
								if (containsWords(subCtgString, gradSubCtg) == false) {
									checkSubCtg = true;
								}
							} else if (ctgName.equals("High School")) {
								if (containsWords(subCtgString, hsSubCtg) == false) {
									checkSubCtg = true;
								}
							} else if (ctgName.equals("Lake")) {
								if (containsWords(subCtgString, lkSubCtg) == false) {
									checkSubCtg = true;
								}
							}
							else if (ctgName.equals("Beach")) {
								if (containsWords(subCtgString, beachSubCtg) == false) {
									checkSubCtg = true;
								}
							}
                            }
                            
                        }else{
                            
                            checkSubCtg = subCtgString.toLowerCase().contains(subCtg.toLowerCase());
                            //System.out.println("/**"+subCtg);
                        }
                        
                        if (ctgCell == null) {
                            // Nothing in the cell in this row, skip it
                        } else {
                            if(checkCtg && checkSubCtg && ctgStr!=null){

                              ctgList.add(ctgStr);
                              qtyList.add(qtyVal);
                              if(priceColName.equals("product sales")){
                                  priceList.add(priceVal/qtyVal);
                                  
                              }else{
                                  priceList.add(priceVal);
                                  
                              }
                              
                              shipAmtList.add(shipAmtVal);
                              subCtgList.add(subCtg);
                              //System.out.println("**"+subCtgString);
                              
                              
                            } 
                        }
                    }
            }

                    } else{
                System.out.println("could not find column ");
            } 
                                } catch (EncryptedDocumentException ex) {
    Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
}
 
}
	private static boolean containsWords(String inputString, String[] items) {
		boolean found = false;
		for (String item : items) {
			if (inputString.toLowerCase().contains(item.toLowerCase())) {
				found = true;
				break;
			}
		}
		return found;
	}

	private String findSubCtg(String subCtgString) {
		String subCtg;

		if (ctgName == "Camping") {
			if (subCtgName == "Explore the Outdoors" || subCtgName.equals("Adventure Awaits") || subCtgName == "Bear"
					|| subCtgName == "The Great Outdoors" || subCtgName == "Tent" || subCtgName == "Happy Camper") {
				subCtg = subCtgName;
			} else {
				subCtg = "";

			}
		} else if (ctgName == "SKI") {
			if (subCtgName == "Goggles" || subCtgName == "Board Design" || subCtgName == "Mountain") {
				subCtg = subCtgName;
			} else {
				subCtg = "";

			}
		} else if (ctgName == "College") {
			if (subCtgName == "Abilene Christian University" || subCtgName == "Alabama Crimson Tide"
					|| subCtgName == "Appalachian State" || subCtgName == "Auburn Tigers" || subCtgName == "Clemson"
					|| subCtgName == "Colorado Buffaloes" || subCtgName == "Colorado State Rams"
					|| subCtgName == "Florida State" || subCtgName == "Georgia Bulldogs" || subCtgName == "Gonzaga"
					|| subCtgName == "Iowa Hawkeyes" || subCtgName == "Iowa State" || subCtgName == "Kansas Jayhawks"
					|| subCtgName == "Maryland" || subCtgName == "Michigan State" || subCtgName == "Penn State"
					|| subCtgName == "Radford University" || subCtgName == "Stanford University"
					|| subCtgName == "Texas Tech" || subCtgName == "Tulane" || subCtgName == "University of Houston"
					|| subCtgName == "University of Tampa Spartans" || subCtgName == "Utah Utes"
					|| subCtgName == "Virginia Cavaliers" || subCtgName == "Akron Zips"
					|| subCtgName == "Alabama State University" || subCtgName == "Albany State University"
					|| subCtgName == "Alcorn State" || subCtgName == "Arkansas Razorbacks"
					|| subCtgName == "Arkansas State" || subCtgName == "Ball State University"
					|| subCtgName == "Memphis Tigers" || subCtgName == "Southern Illinois"
					|| subCtgName == "Baylor Bears " || subCtgName == "Mercer University"
					|| subCtgName == "Southern Methodist" || subCtgName == "Boise State Broncos"
					|| subCtgName == "Miami Hurricanes " || subCtgName == "Southern Mississippi"
					|| subCtgName == "Boston Terriers" || subCtgName == "Michigan Wolverines"
					|| subCtgName == "Southern Utah University" || subCtgName == "Bowling Green Falcons"
					|| subCtgName == "Midwestern State University" || subCtgName == "Southwestern Oklahoma"
					|| subCtgName == "Brigham Young Cougars" || subCtgName == "Minnesota Duluth Bulldogs"
					|| subCtgName == "Stephen F. Austin State University" || subCtgName == "Butler Bulldogs"
					|| subCtgName == "Minnesota Gophers" || subCtgName == "Syracuse Orange"
					|| subCtgName == "California State University" || subCtgName == "Mississippi Rebels "
					|| subCtgName == "Tarleton State University" || subCtgName == "Campbell University"
					|| subCtgName == "Mississippi State Bulldogs" || subCtgName == "Texas A&M Aggies "
					|| subCtgName == "Category Submitted Under" || subCtgName == "Missouri Tigers"
					|| subCtgName == "Texas A&M Kingsville Javelinas" || subCtgName == "Central Arkansas Bears"
					|| subCtgName == "Montana State Bobcats" || subCtgName == "Texas Christian University"
					|| subCtgName == "Central Michigan University" || subCtgName == "Montana Tech"
					|| subCtgName == "Texas Southern University" || subCtgName == "Christopher Newport"
					|| subCtgName == "Morehead State University" || subCtgName == "Texas State Bobcats"
					|| subCtgName == "Coastal Carolina University" || subCtgName == "Murray State University"
					|| subCtgName == "The University of Texas at Tyler" || subCtgName == "Davidson College"
					|| subCtgName == "NC State Wolfpack " || subCtgName == "Toledo Rockets"
					|| subCtgName == "Dayton Flyers" || subCtgName == "Nebraska Cornhuskers "
					|| subCtgName == "Troy University" || subCtgName == "Delaware Blue Hens"
					|| subCtgName == "New Mexico Lobos" || subCtgName == "Truman State University"
					|| subCtgName == "East Carolina Pirates" || subCtgName == "New Mexico State University"
					|| subCtgName == "Tuskegee University" || subCtgName == "East Central University Tigers"
					|| subCtgName == "Norfolk State University" || subCtgName == "UC Davis Aggies"
					|| subCtgName == "East Stroudsburg University" || subCtgName == "North Carolina A&T"
					|| subCtgName == "UCLA Bruins" || subCtgName == "East Tennessee State University"
					|| subCtgName == "North Carolina Central Eagles" || subCtgName == "UNC Tar Heels"
					|| subCtgName == "East Texas Baptist University" || subCtgName == "North Carolina Charlotte"
					|| subCtgName == "University of Central Oklahoma" || subCtgName == "Edinboro University"
					|| subCtgName == "North Carolina Greensboro" || subCtgName == "University of Denver Pioneers"
					|| subCtgName == "Elizabeth City" || subCtgName == "North Carolina Wilmington"
					|| subCtgName == "University of Illinois at Chicago" || subCtgName == "Elon University"
					|| subCtgName == "North Dakota Fighting Hawks" || subCtgName == "University of Louisiana Monroe"
					|| subCtgName == "Fayetteville State University" || subCtgName == "North Dakota State Bison"
					|| subCtgName == "University of Southern Indiana" || subCtgName == "Florida A&M Rattlers"
					|| subCtgName == "North Georgia Nighhawks" || subCtgName == "University of Tennessee at Chattanooga"
					|| subCtgName == "Florida Gators" || subCtgName == "North Texas"
					|| subCtgName == "University of Texas of the Permian Basin"
					|| subCtgName == "Florida Gulf Coast Eagles" || subCtgName == "Northeastern State University"
					|| subCtgName == "UNLV Rebels" || subCtgName == "Fort Valley State University"
					|| subCtgName == "Northern Colorado Bears" || subCtgName == "UTSA Road Runners"
					|| subCtgName == "Fresno State Bulldogs" || subCtgName == "Northern Illinois Huskies"
					|| subCtgName == "Valparaiso University" || subCtgName == "Georgia Southern Eagles"
					|| subCtgName == "Northern Iowa Panthers" || subCtgName == "Villanova Wildcats"
					|| subCtgName == "Georgia Tech Yellow Jackets"
					|| subCtgName == "Northwestern Oklahoma State University" || subCtgName == "Virginia Cavaliers "
					|| subCtgName == "Grambling State Tigers" || subCtgName == "Northwestern State Demons"
					|| subCtgName == "Hokies " || subCtgName == "Grand Canyon University"
					|| subCtgName == "Northwestern University" || subCtgName == "VMI Keydets"
					|| subCtgName == "Hampton University" || subCtgName == "Ohio Wesleyan University"
					|| subCtgName == "Washington & Lee Generals" || subCtgName == "Idaho Vandals"
					|| subCtgName == "Oklahoma State" || subCtgName == "West Texas A&M Buffaloes"
					|| subCtgName == "Illinois Fighting Illini " || subCtgName == "Old Dominion Monarchs"
					|| subCtgName == "West Virginia Mountaineers" || subCtgName == "Illinois State Redbirds"
					|| subCtgName == "Oregon State Beavers" || subCtgName == "Western Carolina University"
					|| subCtgName == "Indiana Hoosiers" || subCtgName == "Pittsburgh Panthers "
					|| subCtgName == "Western Kentucky Hilltoppers" || subCtgName == "James Madison Dukes"
					|| subCtgName == "Plymouth State" || subCtgName == "Western New Mexico"
					|| subCtgName == "Kansas State Wildcats" || subCtgName == "Purdue Boilermakers"
					|| subCtgName == "Wichita State Shockers" || subCtgName == "Kennesaw State University"
					|| subCtgName == "Richmond Spiders" || subCtgName == "William and Mary"
					|| subCtgName == "Kentucky Wildcats" || subCtgName == "Rider University Broncs"
					|| subCtgName == "Winthrop University" || subCtgName == "Louisiana Tech Bulldogs"
					|| subCtgName == "Rutgers Scarlet Knights" || subCtgName == "Wyoming Cowboys"
					|| subCtgName == "Louisville Cardinals " || subCtgName == "Savannah State University"
					|| subCtgName == "South Dakota Coyotes" || subCtgName == "Loyola University Ramblers"
					|| subCtgName == "Shaw University Bears" || subCtgName == "Marshall Thundering Herd"
					|| subCtgName == "Lubbock Christian University" || subCtgName == "South Dakota State"
					|| subCtgName == "South Florida Bulls" || subCtgName == "Marquette Golden Eagles"

			) {

				subCtg = subCtgName;
			} else {
				subCtg = "";
			}
		} else if (ctgName == "NASCAR") {
			if (subCtgName == "Alex Bowman" || subCtgName == "Aric Almirola" || subCtgName == "Austin Cindric"
					|| subCtgName == "Brad Keselowski" || subCtgName == "Clint Bowyer" || subCtgName == "Chase Briscoe"
					|| subCtgName == "Chase Elliott" || subCtgName == "Chris Buescher"
					|| subCtgName == "Christopher Bell" || subCtgName == "Cole Custer" || subCtgName == "Denny Hamlin"
					|| subCtgName == "Erik Jones" || subCtgName == "Harrison Burton" || subCtgName == "Joey Logano"
					|| subCtgName == "Dale Jr" || subCtgName == "Kevin Harvick" || subCtgName == "Kurt Busch"
					|| subCtgName == "Kyle Busch" || subCtgName == "Kyle Larson" || subCtgName == "Martin Truex Jr"
					|| subCtgName == "Matt DiBenedetto" || subCtgName == "Noah Gragson" || subCtgName == "Ryan Blaney"
					|| subCtgName == "William Byron") {

				subCtg = subCtgName;
			} else {
				subCtg = "";
			}
		} else if (ctgName == "City") {
			if (subCtgName == "Retro" || subCtgName == "Sunset" || subCtgName == "Neon") {
				subCtg = subCtgName;

			} else {
				subCtg = "";
			}
		} else if (ctgName == "Realtree") {
			if (subCtgName == "Lanyard" || subCtgName == "Plate frame" || subCtgName == "Tumbler"
					|| subCtgName == "Sticker") {
				subCtg = subCtgName;

			} else {
				subCtg = "";
			}
		} else if (ctgName == "CUSTOM") {
			if (subCtgName == "Shot glass" || subCtgName == "Tumbler" || subCtgName == "Plate Frame"
					|| subCtgName == "Sign" || subCtgName == "Sticker" || subCtgName == "Magnet") {
				subCtg = subCtgName;

			} else {
				subCtg = "";
			}
		} else if (ctgName == "Gaduation") {
			if (subCtgName == "Shot glass" || subCtgName == "Tumbler" || subCtgName == "Plate Frame"
					|| subCtgName == "Sign" || subCtgName == "Sticker" || subCtgName == "Magnet") {
				subCtg = subCtgName;

			} else {
				subCtg = "";
			}
		} else if (ctgName == "High School") {
			if (subCtgName == "Lanyard" || subCtgName == "Plate Frame" || subCtgName == "Tumbler"
					|| subCtgName == "Sticker" || subCtgName == "Mailbox" || subCtgName == "Sign"
					|| subCtgName == "Magnet") {
				subCtg = subCtgName;

			} else {
				subCtg = "";
			}
		} else if (ctgName == "Pet") {
			if (subCtgName == "Lanyard" || subCtgName == "Plate Frame" || subCtgName == "Tumbler"
					|| subCtgName == "Sticker" || subCtgName == "Mailbox" || subCtgName == "Sign"
					|| subCtgName == "Magnet") {
				subCtg = subCtgName;
			} else {
				subCtg = "";
			}
		} else if (ctgName == "Lake") {
			if (subCtgName == "Paddle" || subCtgName == "I Love") {
				subCtg = subCtgName;
			} else {
				subCtg = "";
			}
		} else if (ctgName == "Beach") {
			if (subCtgName == "Wave Design" || subCtgName == "Turtle" || subCtgName == "Palm" || subCtgName == "Flip Flop" || subCtgName == "Octopus" || subCtgName == "Compas"  || subCtgName == "Sunset"  || subCtgName == "Nautical") {
				subCtg = subCtgName;
			} else {
				subCtg = "";
			}
		}

		else {
			subCtg = "";
		}
		return subCtg;
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
		double subCtgSales = 0.0;
		int val = 0;
		while (subCtgList.size() > val) {
			subCtgSales += ((qtyList.get(val) * priceList.get(val)) + shipAmtList.get(val));
			val++;
		}
		return subCtgSales;

	}

	public double calculateUnitsSold() {
		double ctgUnitSold = 0.0;
		int val = 0;
		while (this.qtyList.size() > val) {
			ctgUnitSold += this.qtyList.get(val);
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

