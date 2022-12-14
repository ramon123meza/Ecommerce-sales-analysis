
package Sales_details_R_and_R_Imports;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.NumberFormat;


/**
 *
 * @author Microsoft
 */
public class GUIForm extends javax.swing.JFrame {

    /**
     * Creates new form GUIForm
     */
    public GUIForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lbl1FilePath = new javax.swing.JLabel();
        lbl2FilePath = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Open File 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbl1FilePath.setText("-");

        lbl2FilePath.setText("-");

        jButton2.setText("Open File 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Show Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl2FilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(lbl1FilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 21, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl1FilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl2FilePath)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser = new JFileChooser();            
        int i = filechooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            File f = filechooser.getSelectedFile();
            String filepath1 = f.getPath();
            lbl1FilePath.setText(filepath1);
            
        }       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser = new JFileChooser();            
        int i = filechooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            File f = filechooser.getSelectedFile();
            String filepath2 = f.getPath();
            lbl2FilePath.setText(filepath2);
            
        }  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add handling code here:
    	NumberFormat nf=NumberFormat.getCurrencyInstance();
    	DecimalFormat df = new DecimalFormat("0.00");
    	try {
        runResults("Camping");
		/*runResults("College");
		runResults("NASCAR");
		runResults("SKI");
		runResults("City");
		runResults("Realtree");
		runResults("Pet");
		runResults("CUSTOM");
		runResults("Graduation");
		runResults("High School");
		runResults("Lake");
		runResults("Beach");*/
		
		
		} catch (IOException ex) {
		Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		/*try {
		    runSalesbySKU("Camping");
		    runSalesbySKU("College");
		    runSalesbySKU("NASCAR");
		    runSalesbySKU("SKI");
		    runSalesbySKU("City");
		    runSalesbySKU("Realtree");
		    runSalesbySKU("Pet");
		    runSalesbySKU("CUSTOM");
		    runSalesbySKU("Graduation");
		    runSalesbySKU("High School");
		    runSalesbySKU("Lake");
		    runSalesbySKU("Beach");
		    
		} catch (IOException ex) {
		    Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
		double cmpSales = runFinalResults("Camping");
		double clgSales = runFinalResults("College");
		double nSales = runFinalResults("NASCAR");
		double skiSales = runFinalResults("SKI");
		double ctySales = runFinalResults("City");
		double rtSales = runFinalResults("Realtree");
		double custSales = runFinalResults("CUSTOM");
		double gradSales = runFinalResults("Graduation");
		double petSales = runFinalResults("Pet");
		double hsSales = runFinalResults("High School");
		double lkSales = runFinalResults("Lake");
		double beachSales = runFinalResults("Beach");
		
		double totalSales = cmpSales + clgSales + nSales + skiSales + ctySales +rtSales +custSales +gradSales + petSales + hsSales +lkSales+ beachSales;
		
		double cmpSalesPerc = (cmpSales*100)/totalSales;
		double clgSalesPerc = (clgSales*100)/totalSales;
		double nSalesPerc = (nSales*100)/totalSales;
		double skiSalesPerc = (skiSales*100)/totalSales;
		double ctySalesPerc = (ctySales*100)/totalSales;
		double rtSalesPerc = (rtSales*100)/totalSales;
		double custSalesPerc = (custSales*100)/totalSales;
		double gradSalesPerc = (gradSales*100)/totalSales;
		double petSalesPerc = (petSales*100)/totalSales;
		double hsSalesPerc = (hsSales*100)/totalSales;
		double lkSalesPerc = (lkSales*100)/totalSales;
		double beachSalesPerc = (beachSales*100)/totalSales;
		
		
		System.out.println("--------------Final Sales by Category Summary-------------------");
		System.out.println("Category       Total-Sales                     Sales%");
		System.out.println("Camping      "+nf.format(cmpSales)+"        "+df.format(cmpSalesPerc)+"%");
		System.out.println("College      "+nf.format(clgSales)+"        "+df.format(clgSalesPerc)+"%");
		System.out.println("NASCAR      " +nf.format(nSales)+"          "+df.format(nSalesPerc)+"%");
		System.out.println("SKI       "+   nf.format(skiSales)+"        "+df.format(skiSalesPerc)+"%");
		System.out.println("City      "+   nf.format(ctySales)+"        "+df.format(ctySalesPerc)+"%");
		System.out.println("Realtree     "+nf.format(rtSales)+"         "+df.format(rtSalesPerc)+"%");
		System.out.println("CUSTOM       "+nf.format(custSales)+"       "+df.format(custSalesPerc)+"%");
		System.out.println("Graduation    "+nf.format(gradSales)+"      "+df.format(gradSalesPerc)+"%");
		System.out.println("Pet           "+nf.format(petSales)+"       "+df.format(petSalesPerc)+"%");
		System.out.println("High School   "+nf.format(hsSales)+"        "+df.format(hsSalesPerc)+"%");
		System.out.println("Lake   "+       nf.format(lkSales)+"        "+df.format(lkSalesPerc)+"%");
		System.out.println("Beach "+nf.format(beachSales)+"     "+df.format(beachSalesPerc)+"%");
		}
		catch (IOException ex) {
		Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
		}
    	try {
            System.out.println("\n---------------Camping Subcategories------------------");
            runSubCtg("Camping", "Adventure Awaits");
            runSubCtg("Camping", "Bear");
            runSubCtg("Camping", "Explore the Outdoors");
            runSubCtg("Camping", "Happy Camper");
            runSubCtg("Camping", "Tent");
            runSubCtg("Camping", "The Great Outdoors");
            runSubCtg("Camping","Uncategorized");
            System.out.println("\n---------------College Subcategories------------------");
            runSubCtg("College", "Abilene Christian University");
            runSubCtg("College", "Alabama Crimson Tide");
            runSubCtg("College", "Appalachian State");
            runSubCtg("College", "Auburn Tigers");
            runSubCtg("College", "Clemson");
            runSubCtg("College", "Colorado Buffaloes");
            runSubCtg("College", "Colorado State Rams");
            runSubCtg("College", "Florida State");
            runSubCtg("College", "Georgia Bulldogs");
            runSubCtg("College", "Gonzaga");
            runSubCtg("College", "Iowa Hawkeyes");
            runSubCtg("College", "Iowa State");
            runSubCtg("College", "Kansas Jayhawks");
            runSubCtg("College", "Maryland");
            runSubCtg("College", "Michigan State");
            runSubCtg("College", "Penn State");
            runSubCtg("College", "Radford University");
            runSubCtg("College", "Stanford University");
            runSubCtg("College", "Texas Tech");
            runSubCtg("College", "Tulane");
            runSubCtg("College", "University of Houston");
            runSubCtg("College", "University of Tampa Spartans");
            runSubCtg("College", "Utah Utes");
            runSubCtg("College", "Virginia Cavaliers");
            runSubCtg("College", "Akron Zips");
            runSubCtg("College", "Alabama State University");
            runSubCtg("College", "Albany State University");
            runSubCtg("College", "Alcorn State");
            runSubCtg("College", "Arkansas Razorbacks");
            runSubCtg("College", "Arkansas State");
            runSubCtg("College", "Ball State University");
            runSubCtg("College", "Baylor Bears ");
            runSubCtg("College", "Boise State Broncos");
            runSubCtg("College", "Boston Terriers");
            runSubCtg("College", "Bowling Green Falcons");
            runSubCtg("College", "Brigham Young Cougars");
            runSubCtg("College", "Butler Bulldogs");
            runSubCtg("College", "California State University");
            runSubCtg("College", "Campbell University");
            runSubCtg("College", "Category Submitted Under");
            runSubCtg("College", "Central Arkansas Bears");
            runSubCtg("College", "Central Michigan University");
            runSubCtg("College", "Christopher Newport");
            runSubCtg("College", "Coastal Carolina University");
            runSubCtg("College", "Davidson College");
            runSubCtg("College", "Dayton Flyers");
            runSubCtg("College", "Delaware Blue Hens");
            runSubCtg("College", "East Carolina Pirates");
            runSubCtg("College", "East Central University Tigers");
            runSubCtg("College", "East Stroudsburg University");
            runSubCtg("College", "East Tennessee State University");
            runSubCtg("College", "East Texas Baptist University");
            runSubCtg("College", "Edinboro University");
            runSubCtg("College", "Elizabeth City");
            runSubCtg("College", "Elon University");
            runSubCtg("College", "Fayetteville State University");
            runSubCtg("College", "Florida A&M Rattlers");
            runSubCtg("College", "Florida Gators");
            runSubCtg("College", "Florida Gulf Coast Eagles");
            runSubCtg("College", "Fort Valley State University");
            runSubCtg("College", "Fresno State Bulldogs");
            runSubCtg("College", "Georgia Southern Eagles");
            runSubCtg("College", "Georgia Tech Yellow Jackets");
            runSubCtg("College", "Grambling State Tigers");
            runSubCtg("College", "Grand Canyon University");
            runSubCtg("College", "Hampton University");
            runSubCtg("College", "Idaho Vandals");
            runSubCtg("College", "Illinois Fighting Illini ");
            runSubCtg("College", "Illinois State Redbirds");
            runSubCtg("College", "Indiana Hoosiers");
            runSubCtg("College", "James Madison Dukes");
            runSubCtg("College", "Kansas State Wildcats");
            runSubCtg("College", "Kennesaw State University");
            runSubCtg("College", "Kentucky Wildcats");
            runSubCtg("College", "Louisiana Tech Bulldogs");
            runSubCtg("College", "Louisville Cardinals ");
            runSubCtg("College", "Loyola University Ramblers");
            runSubCtg("College", "Lubbock Christian University");
            runSubCtg("College", "Marquette Golden Eagles");
            runSubCtg("College", "Memphis Tigers");
            runSubCtg("College", "Mercer University");
            runSubCtg("College", "Miami Hurricanes ");
            runSubCtg("College", "Michigan Wolverines");
            runSubCtg("College", "Midwestern State University");
            runSubCtg("College", "Minnesota Duluth Bulldogs");
            runSubCtg("College", "Minnesota Gophers");
            runSubCtg("College", "Mississippi Rebels");
            runSubCtg("College", "Mississippi State Bulldogs");
            runSubCtg("College", "Missouri Tigers");
            runSubCtg("College", "Montana State Bobcats");
            runSubCtg("College", "Montana Tech");
            runSubCtg("College", "Morehead State University");
            runSubCtg("College", "Murray State University");
            runSubCtg("College", "NC State Wolfpack ");
            runSubCtg("College", "Nebraska Cornhuskers ");
            runSubCtg("College", "New Mexico Lobos");
            runSubCtg("College", "New Mexico State University");
            runSubCtg("College", "Norfolk State University");
            runSubCtg("College", "North Carolina A&T");
            runSubCtg("College", "North Carolina Central Eagles");
            runSubCtg("College", "North Carolina Charlotte");
            runSubCtg("College", "North Carolina Greensboro");
            runSubCtg("College", "North Carolina Wilmington");
            runSubCtg("College", "North Dakota Fighting Hawks");
            runSubCtg("College", "North Dakota State Bison");
            runSubCtg("College", "North Georgia Nighhawks");
            runSubCtg("College", "North Texas");
            runSubCtg("College", "Northeastern State University");
            runSubCtg("College", "Northern Colorado Bears");
            runSubCtg("College", "Northern Illinois Huskies");
            runSubCtg("College", "Northern Iowa Panthers");
            runSubCtg("College", "Northwestern Oklahoma State University");
            runSubCtg("College", "Northwestern State Demons");
            runSubCtg("College", "Northwestern University");
            runSubCtg("College", "Ohio Wesleyan University");
            runSubCtg("College", "Oklahoma State");
            runSubCtg("College", "Old Dominion Monarchs");
            runSubCtg("College", "Oregon State Beavers");
            runSubCtg("College", "Pittsburgh Panthers ");
            runSubCtg("College", "Plymouth State");
            runSubCtg("College", "Purdue Boilermakers");
            runSubCtg("College", "Richmond Spiders");
            runSubCtg("College", "Rider University Broncs");
            runSubCtg("College", "Rutgers Scarlet Knights");
            runSubCtg("College", "Savannah State University");
            runSubCtg("College", "Shaw University Bears");
            runSubCtg("College", "South Dakota State");
            runSubCtg("College", "Southern Illinois");
            runSubCtg("College", "Southern Methodist");
            runSubCtg("College", "Southern Mississippi");
            runSubCtg("College", "Southern Utah University");
            runSubCtg("College", "Southwestern Oklahoma");
            runSubCtg("College", "Stephen F. Austin State University");
            runSubCtg("College", "Syracuse Orange");
            runSubCtg("College", "Tarleton State University");
            runSubCtg("College", "Texas A&M Aggies ");
            runSubCtg("College", "Texas A&M Kingsville Javelinas");
            runSubCtg("College", "Texas Christian University");
            runSubCtg("College", "Texas Southern University");
            runSubCtg("College", "Texas State Bobcats");
            runSubCtg("College", "The University of Texas at Tyler");
            runSubCtg("College", "Toledo Rockets");
            runSubCtg("College", "Troy University");
            runSubCtg("College", "Truman State University");
            runSubCtg("College", "Tuskegee University");
            runSubCtg("College", "UC Davis Aggies");
            runSubCtg("College", "UCLA Bruins");
            runSubCtg("College", "UNC Tar Heels");
            runSubCtg("College", "University of Central Oklahoma");
            runSubCtg("College", "University of Denver Pioneers");
            runSubCtg("College", "University of Illinois at Chicago");
            runSubCtg("College", "University of Louisiana Monroe");
            runSubCtg("College", "University of Southern Indiana");
            runSubCtg("College", "University of Tennessee at Chattanooga");
            runSubCtg("College", "University of Texas of the Permian Basin");
            runSubCtg("College", "UNLV Rebels");
            runSubCtg("College", "UTSA Road Runners");
            runSubCtg("College", "Valparaiso University");
            runSubCtg("College", "Villanova Wildcats");
            runSubCtg("College", "Virginia Cavaliers ");
            runSubCtg("College", "Hokies ");
            runSubCtg("College", "VMI Keydets");
            runSubCtg("College", "Washington & Lee Generals");
            runSubCtg("College", "West Texas A&M Buffaloes");
            runSubCtg("College", "West Virginia Mountaineers");
            runSubCtg("College", "Western Carolina University");
            runSubCtg("College", "Western Kentucky Hilltoppers");
            runSubCtg("College", "Western New Mexico");
            runSubCtg("College", "Wichita State Shockers");
            runSubCtg("College", "William and Mary");
            runSubCtg("College", "Winthrop University");
            runSubCtg("College", "Wyoming Cowboys");
            runSubCtg("College", "South Dakota Coyotes");
            runSubCtg("College", "Marshall Thundering Herd");
            runSubCtg("College", "South Florida Bulls");
            runSubCtg("College","Uncategorized");
            System.out.println("\n---------------NASCAR Subcategories------------------");
            runSubCtg("NASCAR", "Alex Bowman");
            runSubCtg("NASCAR", "Aric Almirola");
            runSubCtg("NASCAR", "Austin Cindric");
            runSubCtg("NASCAR", "Brad Keselowski");
            runSubCtg("NASCAR", "Chase Briscoe");
            runSubCtg("NASCAR", "Chase Elliott");
            runSubCtg("NASCAR", "Chris Buescher");
            runSubCtg("NASCAR", "Christopher Bell");
            runSubCtg("NASCAR", "Clint Bowyer");
            runSubCtg("NASCAR", "Cole Custer");
            runSubCtg("NASCAR", "Dale Jr");
            runSubCtg("NASCAR", "Denny Hamlin");
            runSubCtg("NASCAR", "Erik Jones");
            runSubCtg("NASCAR", "Harrison Burton");
            runSubCtg("NASCAR", "Joey Logano");
            runSubCtg("NASCAR", "Kevin Harvick");
            runSubCtg("NASCAR", "Kurt Busch");
            runSubCtg("NASCAR", "Kyle Busch");
            runSubCtg("NASCAR", "Kyle Larson");
            runSubCtg("NASCAR", "Martin Truex Jr");
            runSubCtg("NASCAR", "Matt DiBenedetto");
            runSubCtg("NASCAR", "Noah Gragson");
            runSubCtg("NASCAR", "Ryan Blaney");
            runSubCtg("NASCAR", "William Byron");
            runSubCtg("NASCAR","Uncategorized");
            System.out.println("\n---------------SKI Subcategories------------------");
            runSubCtg("SKI", "Goggles");
            runSubCtg("SKI", "Board Design");
            runSubCtg("SKI", "Mountain");
            runSubCtg("SKI","Uncategorized");
            System.out.println("\n---------------City Subcategories------------------");
            runSubCtg("City", "Retro");
            runSubCtg("City", "Sunset");
            runSubCtg("City", "Neon");
            runSubCtg("City","Uncategorized");
            System.out.println("\n---------------CUSTOM Subcategories------------------");
            runSubCtg("CUSTOM", "Shot Glass");
            runSubCtg("CUSTOM", "Tumbler");
            runSubCtg("CUSTOM", "Plate Frame");
            runSubCtg("CUSTOM", "Sign");
            runSubCtg("CUSTOM", "Sticker");
            runSubCtg("CUSTOM", "Magnet");
            runSubCtg("CUSTOM", "Uncategorized");
            System.out.println("\n---------------Realtree Subcategories------------------");
            runSubCtg("Realtree","Lanyard");
            runSubCtg("Realtree","Plate frame");
            runSubCtg("Realtree","Tumbler");
            runSubCtg("Realtree","Sticker");
            runSubCtg("Realtree","Uncategorized");
            System.out.println("\n---------------Graduation Subcategories------------------");
            runSubCtg("Graduation","Shot glass");
            runSubCtg("Graduation","Tumbler");
            runSubCtg("Graduation","Plate frame");
            runSubCtg("Graduation","Sign");
            runSubCtg("Graduation","Sticker");
            runSubCtg("Graduation","Magnet");
            runSubCtg("Graduation","Uncategorized");
            System.out.println("\n---------------Pet Subcategories------------------");
            runSubCtg("Pet", "Lanyard");
            runSubCtg("Pet", "Plate Frame");
            runSubCtg("Pet", "Tumbler");
            runSubCtg("Pet", "Sticker");
            runSubCtg("Pet", "Magnet");
            runSubCtg("Pet", "Mailbox");
            runSubCtg("Pet", "Sign");
            runSubCtg("Pet", "Uncategorized");
            System.out.println("\n---------------High School Subcategories------------------");
            runSubCtg("High School", "Lanyard");
            runSubCtg("High School", "Plate Frame");
            runSubCtg("High School", "Tumbler");
            runSubCtg("High School", "Sticker");
            runSubCtg("High School", "Magnet");
            runSubCtg("High School", "Mailbox");
            runSubCtg("High School", "Sign");
            runSubCtg("High School", "Uncategorized");
            System.out.println("\n---------------Lake Subcategories------------------");
            runSubCtg("Lake", "Paddle");
            runSubCtg("Lake", "I Love");
            runSubCtg("Lake", "Uncategorized");
            System.out.println("\n---------------Beach Subcategories------------------");
            runSubCtg("Beach", "Wave Design");
            runSubCtg("Beach", "Turtle");
            runSubCtg("Beach", "Palm");
            runSubCtg("Beach", "Flip Flop");
            runSubCtg("Beach", "Octopus");
            runSubCtg("Beach", "Compas");
            runSubCtg("Beach", "Sunset");
            runSubCtg("Beach", "Nautical");
            runSubCtg("Beach", "Uncategorized");
             
            
        } catch (IOException ex) {
            Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    	setVisible(false);
        dispose();
}
    /**
     * Create array of exceptions in case the dowloaded files cotain errors 
     * @param ctgName
     * @return
     * @throws IOException
     */

    private double runFinalResults(String ctgName) throws IOException{
        FileInputStream filepath1 = null, filepath2=null;
        try {
                    filepath1 = new FileInputStream(lbl1FilePath.getText());
                    filepath2 = new FileInputStream(lbl2FilePath.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        FinalSales ctgSales1 = new FinalSales(filepath1, ctgName);
        FinalSales ctgSales2 = new FinalSales(filepath2, ctgName);
        
        try {
            ctgSales1.getFile1Columns();
            ctgSales2.getFile2Columns();
            
        } catch (IOException ex) {
            Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        double ctgSales = ctgSales1.calculateTotalSales() + ctgSales2.calculateTotalSales();

        filepath1.close();
        filepath2.close();
        
        return ctgSales;
    }

    private void runSubCtg(String ctgName, String subCtgName) throws IOException, InvalidFormatException{
        FileInputStream filepath1 = null, filepath2=null;
       
        try {
        	filepath1 = new FileInputStream(lbl1FilePath.getText());
            filepath2 = new FileInputStream(lbl2FilePath.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        ctgSubcategories subCtg1 = new ctgSubcategories(filepath1, ctgName, subCtgName);
        subCtg1.getFile1Columns("OrderItemDescription","OrderItemSku","OrderItemQuantity","OrderItemUnitPrice","OrderItemShippingAmount");
        double sales1 = subCtg1.calculateTotalSales();
        double price1 = subCtg1.calculateUnitsSold();
        double shipAmt1 = subCtg1.calculateShippingAmt();
        
        filepath1.close();
       
        ctgSubcategories subCtg2 = new ctgSubcategories(filepath2, ctgName, subCtgName);
        subCtg2.getFile1Columns("description","sku","quantity","product sales","shipping credits");
        double subCtgTotalSales = sales1+subCtg2.calculateTotalSales();
        double subCtgTotalUnitsSold = price1+subCtg2.calculateUnitsSold();
        double subCgShipAmt = shipAmt1+subCtg2.calculateShippingAmt();
        
        
        System.out.println("\n---------------"+subCtgName+"-----------------");
        System.out.println(subCtgName+" Total Sales: "+    subCtgTotalSales);
        System.out.println(subCtgName+" Total Units Sold: "+subCtgTotalUnitsSold);
        System.out.println(subCtgName+" Total Shipping Paid by Customer: "+  subCgShipAmt);
        
        
        filepath2.close();
        
    }
    private void runResults(String ctgName) throws IOException{
        FileInputStream filepath1 = null, filepath2=null;
        try {
        	filepath1 = new FileInputStream(lbl1FilePath.getText());
            filepath2 = new FileInputStream(lbl2FilePath.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        CategoriesResult ctgResult1 = new CategoriesResult(filepath1, ctgName);
        CategoriesResult ctgResult2 = new CategoriesResult(filepath2, ctgName);
        
        try {
        	HashMap<Integer, Object[]> file1Data = ctgResult1.getFile1Columns();
        	HashMap<Integer, Object[]> file2Data = ctgResult2.getFile2Columns(file1Data.size());
        	WriteDataToExcel poiWriteData = new WriteDataToExcel(ctgName+".xlsx") ;
        	System.out.println(file1Data.keySet());
        	System.out.println(file2Data.keySet());
        	
        	HashMap <Integer, Object[]> combineDetails = file1Data;
            
            for (Integer s : file2Data.keySet()) {
                combineDetails.put(s, file2Data.get(s));
            }
        	poiWriteData.writeExcel(combineDetails);

            
        } catch (IOException ex) {
            Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        double totalSales = ctgResult1.calculateTotalSales()+ctgResult2.calculateTotalSales();
        double unitsSold = ctgResult1.calculateUnitsSold()+ctgResult2.calculateUnitsSold();
        double shipAmt = ctgResult1.calculateShippingAmt()+ctgResult2.calculateShippingAmt();
        
        printResults(ctgName, totalSales, unitsSold, shipAmt);
        
        filepath1.close();
        filepath2.close();
    }
    private void printResults(String ctgName, double totalSales, double unitsSold, double shipAmt){
    	NumberFormat nf=NumberFormat.getCurrencyInstance();
        System.out.println("---------------------------"+ctgName+" Results--------------------------");
        System.out.println(ctgName + " Total Sales: " +                     nf.format(totalSales));
        System.out.println(ctgName + " Total Units Sold: " +                         (unitsSold));
        System.out.println(ctgName + " Total Shipping Paid By Customer: " + nf.format(shipAmt));
    }
    private void runSalesbySKU(String ctgName) throws IOException{
    	
    FileInputStream filepath1 = null, filepath2=null;
    NumberFormat nf=NumberFormat.getCurrencyInstance();
    try {
    	filepath1 = new FileInputStream(lbl1FilePath.getText());
        filepath2 = new FileInputStream(lbl2FilePath.getText());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    CtgSalesbySKU salesbySKU1 = new CtgSalesbySKU(filepath1, ctgName);
    CtgSalesbySKU salesbySKU2 = new CtgSalesbySKU(filepath2, ctgName);

    try {

        salesbySKU1.getFile1Columns();
        salesbySKU2.getFile2Columns();

    } catch (IOException ex) {
        Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(GUIForm.class.getName()).log(Level.SEVERE, null, ex);
    }
    HashMap <String, Double> itemSales1 = salesbySKU1.calculateTotalSales();
    HashMap <String, Double> itemSales2 = salesbySKU2.calculateTotalSales();
    
    HashMap <String, Double> combineDetails = itemSales1;
    
    for (String s : itemSales2.keySet()) {
      if (combineDetails.containsKey(s)) {
        combineDetails.put(s, itemSales2.get(s) + combineDetails.get(s));
      } else {
        combineDetails.put(s, itemSales2.get(s));
      }
    }
    
    Map<String, Double> sortedSales = combineDetails
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));
    System.out.println("------------------------"+ctgName+" Sales by SKU-----------------------------");
    System.out.println("Item    -    Sales Amount");    		
    for (Map.Entry<String, Double> entry : sortedSales.entrySet()) {
	System.out.println(entry.getKey() + " = " + nf.format(entry.getValue()));
}
    HashMap <String, Double> itemUnitSold1 = salesbySKU1.calculateUnitsSold();
    HashMap <String, Double> itemUnitSold2 = salesbySKU2.calculateUnitsSold();
    
    HashMap <String, Double> combineUnitSold = itemUnitSold1;
    
    for (String s : itemUnitSold2.keySet()) {
      if (combineUnitSold.containsKey(s)) {
        combineUnitSold.put(s, itemUnitSold2.get(s) + combineUnitSold.get(s));
      } else {
        combineUnitSold.put(s, itemUnitSold2.get(s));
      }
    }
    
    Map<String, Double> sortedUnitSold = combineUnitSold
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));
    System.out.println("------------------------"+ctgName+" Sales by SKU-----------------------------");
    System.out.println("Item    -    Units Sold");
    for (Map.Entry<String, Double> entry : sortedUnitSold.entrySet()) {
	System.out.println(entry.getKey() + " = " + entry.getValue());
}
    filepath1.close();
    filepath2.close();
}
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* Nimbus (introduced in Java SE 6) 
         *  
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIForm().setVisible(true);
            }
        });
    }

    // Variables declaration 
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel lbl1FilePath;
    private javax.swing.JLabel lbl2FilePath;
    // End of variables declaration//GEN-END:variables
}
