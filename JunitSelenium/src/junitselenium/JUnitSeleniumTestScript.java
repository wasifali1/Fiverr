/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitselenium;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author user
 */
public class JUnitSeleniumTestScript {

    public static WebDriver driver;
    Map<String, List<String>> objTable = new HashMap<>();
    
     public static void openBrowser(){
         System.setProperty("webdriver.chrome.driver", "E:\\Jars\\chromedriver_win32\\chromedriver.exe");
         driver = new ChromeDriver();
         driver.navigate().to("http://localhost:8080/VehicleDummyWebpage/");
	
     } 
     
     
     public void compareWebpage_FileData(){
        boolean flag = false;
        String appTitle = driver.getTitle();

        System.out.println("Application title is : "+appTitle);
     	try{	 
            objTable = readTable();
        }catch(Exception e){
            
        } 
	Assert.assertNotNull(objTable);
	System.out.println("HashMap 1 containing data of Vehicle Dummy Webpage:" + objTable);
        
        if((flag = compareSourceData(objTable))== true){
            Assert.assertNotNull(false);
            System.out.println("Validating Vehicle Dummy Webpage Data from CSV File:" + flag);
        }
        else{
            System.out.println("Validating Vehicle Dummy Webpage Data from CSV File:" + flag + "  No match Found");
        }
        
     }
     
     
     
     public static void closeBrowser(){
        driver.quit();
     }
     
    public Map<String, List<String>> readTable(){

        List<WebElement> objRows = driver.findElements(By.cssSelector("tr#data"));
        for(int iCount=0; iCount<objRows.size(); iCount++){
            List<WebElement> objCol = objRows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
            List<String> columns = new ArrayList<>();
            for(int col=0; col<objCol.size(); col++){
                columns.add(objCol.get(col).getText());
            }
            objTable.put(String.valueOf(iCount), columns);
        }
        return objTable;
    }
     
    public boolean compareSourceData(Map<String, List<String>> objTable){
        String csvFile = "data/file.csv";
       
        String cvsSplitBy = ",";
        
        Map<String, List<String>> objTable1 = new HashMap<>();
        List<VehicleDTO> data = new ArrayList<>();
        System.out.println("Reading From CSV File:");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();
           
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] str = line.split(cvsSplitBy);
                System.out.println(str[0] + "," + str[1] + "," + str[2]);
                VehicleDTO obj = new VehicleDTO();
                obj.setId(str[0]);
                obj.setVehicleName(str[1]);
                obj.setVehicleColor(str[2]);
                data.add(obj);
            }
            for(int iCount=0;iCount<data.size();iCount++){
                List<String> arr = new ArrayList<>();
                arr.add(data.get(iCount).getId());
                arr.add(data.get(iCount).getVehicleName());
                arr.add(data.get(iCount).getVehicleColor());
                objTable1.put(String.valueOf(iCount), arr);
            }
            
            System.out.println("HashMap 2 containing data of CSV file:" + objTable1);
            boolean flag = objTable.equals(objTable1);
            if(flag){
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    } 
   
}
