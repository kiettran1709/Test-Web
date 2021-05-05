package test.TestNG;
import java.util.Random;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;



public class TestNG_PayBill {
	 
		public WebDriver driver;
//		public static String path = System.getProperty("user.dir");
//		
//		public static String driverPath= path + "\\driver\\chromedriver.exe";

		public void returnToMenu() throws Exception {
			try {
				WebElement backToMenu = driver.findElement(By.name("menu"));
				backToMenu.click(); 
				Thread.sleep(1000);
			}catch(Exception e) {
				System.out.println("Cannot back to Menu");
			}
		}

//		
//		Tiến Hành Back to Menu 
		
		@Test(description = "Click to Menu", priority= 8)
		public void clickToCollections() throws Exception{
			try {
				WebElement listClick = driver.findElement(By.name("menu"));
				listClick.click();
				Thread.sleep(1000);
				AssertJUnit.assertTrue(true);
			}catch(Exception e) {
				e.printStackTrace();
				AssertJUnit.assertTrue(false);
			}
		}
		


		@Test(description = "click to Product random", priority = 9)
		public void testBuyNow() throws InterruptedException {
			try {
				for(int i=0; i<=3; i++){
			Random random = new Random();
			String num = String.valueOf(random.nextInt(10)+1);
			System.out.println(num);
			String product = "productCode" + num;
			String buynow = "buynow"+num;
			String idProduct_Before = driver.findElement(By.id(product)).getText();
			WebElement buyNow = driver.findElement(By.id(buynow));
			buyNow.click();
			Thread.sleep(1000);		
			String idProduct_After = driver.findElement(By.id(product)).getText();
			AssertJUnit.assertEquals(idProduct_After, idProduct_Before);
			returnToMenu();
				}
				
			}catch(Exception e) {
				AssertJUnit.assertTrue(false);
			}
				
		}
			
			
			@Test(description = "Click btn Pay",priority = 10)
			public void clickBtnPay() throws Exception{
				try {
					WebElement btnPay = driver.findElement(By.className("Pay_btn"));
					btnPay.click();
				}catch(Exception e) {
					AssertJUnit.assertTrue(false);
				}
			}
		
			@Test(description = "FilltoBill",priority = 11)
			public void filltoBill() throws Exception{
					WebElement element;
				try {
//					Field Fullname
					element = driver.findElement(By.name("ct-name"));
					element.sendKeys("MonnieClubs");
					
//					Field Address					
					element = driver.findElement(By.name("ct-address"));
					element.sendKeys("FPT Polytechnic");
					
//					field phone numbers
					element = driver.findElement(By.name("ct-phone-num"));
					element.sendKeys("0909709999");
					Thread.sleep(1000);
					AssertJUnit.assertTrue(true);
				}catch(Exception e) {
					e.printStackTrace();
					AssertJUnit.assertTrue(false);
				}
			}
			
			@Test( description = "click btn Confirm",priority = 12)
			public void clickbtnConfirm() throws Exception{
				try {
					WebElement btnConfirm = driver.findElement(By.name("yeucau"));
					Thread.sleep(1000);
					btnConfirm.click();
					
					AssertJUnit.assertTrue(true);
				}catch(Exception e) {
					e.printStackTrace();
					AssertJUnit.assertTrue(false);
				}
				
			}
		
//		kết thúc quá trình test
		@AfterClass
		public void out() {
			driver.close();
			driver.quit();
		}
		
}
