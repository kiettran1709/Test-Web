package test.TestNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.example.demo.UIMap.UIMap;


public class TestNG_Cart {
	  
		public WebDriver driver;
		
		public static String path = System.getProperty("user.dir");
		
		public static String driverPath= path + "\\driver\\chromedriver.exe";
		
//		Open Website
		@Test(suiteName = "checkDell", description = "Open Login page", priority = 1)
		public void launchWebsite() throws Exception {
			try {
				driver.get("http://localhost:8080/ps11038_VoThiCamNguyet_FINAL/");
				
				driver.manage().window().maximize();
				AssertJUnit.assertTrue(true);
			}catch(Exception e) {
				System.out.println(e);
				AssertJUnit.assertTrue(false);
			}
		}
		
//		Fill dữ liệu vào field
		@Test(suiteName = "checkDell", description = "Fill to Login", priority = 2)
		public void fillToLogin() throws Exception{
			try {
				WebElement username = driver.findElement(By.name("txtUsername"));
				
				username.sendKeys("admin");
				
				WebElement password = driver.findElement(By.name("txtPassword"));
				password.sendKeys("123");
				Thread.sleep(1000);
				AssertJUnit.assertTrue(true);
			}catch(Exception e){
				AssertJUnit.assertTrue(false);
			}
		}
		
//		Tìm kiếm btn Login và click
		@Test(suiteName = "checkDell", description = "Click btn Login",priority = 3)
		public void doLogin() throws Exception{
			try {
				WebElement login = driver.findElement(By.id("login"));
				login.click();
				
				Thread.sleep(1000);
				AssertJUnit.assertTrue(true);
			}catch(Exception e) {
				AssertJUnit.assertTrue(false);
			}
		}
		
//		Tiến Hành click vào trang Menu
	
		@Test(suiteName = "checkDell", description = "Click to Menu", priority=4)
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
		
//		Click vao san pham muon mua
//		@Test(description = "click to Product random", priority = 5)
//		public void clickToProduct() throws Exception{
//			try {
//				WebElement productClick = driver.findElement(By.name("product"));
//				productClick.click();
//				Thread.sleep(1000);
//				AssertJUnit.assertTrue(true);
//			}catch(Exception e) {
//				e.printStackTrace();
//				AssertJUnit.assertTrue(0false);
//			}
//		}
		
//		@Test(description = "click to Product random", priority = 5)
//		public void testBuyNow() throws InterruptedException {
//			
//			String idProduct_Before = driver.findElement(By.id("productCode1")).getText();
//			WebElement buyNow = driver.findElement(By.id("buynow1"));
//			buyNow.click();
//			Thread.sleep(1000);
//			
//			String idProduct_After = driver.findElement(By.id("productCode1")).getText();
//			
//			Assert.assertEquals(idProduct_After, idProduct_Before);
//		}
		
		
		@Test(suiteName = "checkDell", description = "click to Product random", priority = 5)
		public void testBuyNow() throws InterruptedException {
			try {
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
			}catch(Exception e) {
				AssertJUnit.assertTrue(false);
			}
			
		}
		
//		Truy cap vao gio hang **Có thể cần hoặc không
//		@Test(description = "Go to Cart", priority = 6)
//		public void clickToCart() {
//			try {
//				WebElement cartClick = driver.findElement(By.name("cart"));
//				cartClick.click();
//				Thread.sleep(1000);
//				AssertJUnit.assertTrue(true);
//			}catch(Exception e) {
//				e.printStackTrace();
//				AssertJUnit.assertTrue(false);
//			}
//		}
		
//		Tang Giam so luong muon mua
		@Test(suiteName = "checkDell", description = "Tang giam", priority = 6)
		public void clickToChange() {
			try {
//				Tang
				 
				for(int i=0; i<=5;i++) {
					WebElement increaseClick = driver.findElement(By.name("tang"));
					increaseClick.click();
					Thread.sleep(500);
					System.out.println(i);
				}
//				Giam
				
				for(int i=0; i<=3;i++) {
					WebElement reduceClick = driver.findElement(By.name("giam"));
					reduceClick.click();
				}
				Thread.sleep(1000);
				AssertJUnit.assertTrue(true);
				
			}catch(Exception e) {
				e.printStackTrace();
				AssertJUnit.assertTrue(false);
			}
		}
		
		@Test(suiteName = "checkDell", description = "delete All", priority = 7)
		public void clickToCart() {
			try {
				WebElement delAllClick = driver.findElement(By.name("delAll"));
				delAllClick.click();
				Thread.sleep(1000);
				AssertJUnit.assertTrue(true);
			}catch(Exception e) {
				e.printStackTrace();
				AssertJUnit.assertTrue(false);
			}
		}
		
		public void returnToMenu() throws Exception {
			try {
				WebElement backToMenu = driver.findElement(By.name("MUAHANGTIEP"));
				backToMenu.click();
				Thread.sleep(400);
				
			}catch(Exception e) {
				System.out.println("Cannot back to Menu");
			}
		}

	
//		Tiến Hành Back to Menu 
		
		@Test(description = "Click to Menu", priority= 8)
		public void bachtoMenu() throws Exception{
			try {
				WebElement listClick = driver.findElement(By.name("MUAHANGTIEP"));
				listClick.click();
				Thread.sleep(1000);
				AssertJUnit.assertTrue(true);
			}catch(Exception e) {
				e.printStackTrace();
				AssertJUnit.assertTrue(false);
			}
		}

		@Test(description = "click to Product random", priority = 9)
		public void clickAgain() throws InterruptedException {
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
			if(i<3) {
			returnToMenu();
			}
				}
				
			}catch(Exception e) {
				AssertJUnit.assertTrue(false);
			}
				
		}
			
			
			@Test(description = "Click btn Pay",priority = 10)
			public void clickBtnPay() throws Exception{
				try {
					WebElement btnPay = driver.findElement(By.name("Pay_btn"));
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
		

//		
////		Tim Ten Theo San Pham
//		@Test(description = "Find By Brand", priority = 8)
//		public void findBybrand() {
//			try {
////				
//				WebElement search = driver.findElement(By.name("search"));
//				search.sendKeys("abc");
////				Giam
//				WebElement searchClick = driver.findElement(By.className("Search_btn"));
//				searchClick.click();
//				Thread.sleep(1000);
//				AssertJUnit.assertTrue(true);
//			}catch(Exception e) {
//				e.printStackTrace();
//				AssertJUnit.assertTrue(false);
//			}
//		}
		
		
//		Set up 
		@BeforeClass
		public void suiteSetUp() {
			try {
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				
				System.out.println(path);
				
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			}catch(Exception e) {
				throw new IllegalStateException("Can't start the Edge Web Driver");
			}
		}
		
//		kết thúc quá trình test
		@AfterClass
		public void out() {
			driver.close();
			driver.quit();
		}
		
}
