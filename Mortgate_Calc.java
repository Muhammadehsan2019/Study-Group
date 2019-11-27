package GCReddy_Assignments;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Mortgate_Calc {
	static int i;
	static String Homevalue, DownPay, IntRate, LoanTerm, PropTax, PMI, HomeIns, MnthHOA;
	

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		

		// connect to chromedriver libraries
		System.setProperty("webdriver.chrome.driver",
				"E:\\1.PeopleinTech\\1.Selenuim\\Selenium Eclips Prj Files\\G _C_Reddy_Tuturial_Practice\\Drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		for (i = 01; i <=5; i++) {
			
			
			if (i==1) {
			Homevalue = "520000";
			DownPay = "170000";
			IntRate = "4.50";
			LoanTerm = "30";
			PropTax = "7000";
			PMI = "1.0";
			HomeIns = "600";
			MnthHOA = "104";

		} else if (i==2) {
			
			Homevalue = "620000";
			DownPay = "270000";
			IntRate = "4.75";
			LoanTerm = "35";
			PropTax = "10000";
			PMI = "2.0";
			HomeIns = "800";
			MnthHOA = "120";	
		
		}else if (i==3) {
			Homevalue = "650000";
			DownPay = "290000";
			IntRate = "4.50";
			LoanTerm = "30";
			PropTax = "15000";
			PMI = "3.0";
			HomeIns = "8000";
			MnthHOA = "150";	
		}else if (i==4) {
			Homevalue = "550000";
			DownPay = "120000";
			IntRate = "4.75";
			LoanTerm = "30";
			PropTax = "1500";
			PMI = "3.0";
			HomeIns = "10000";
			MnthHOA = "250";	
		} else if (i==5) {
			
			Homevalue = "540000";
			DownPay = "90000";
			IntRate = "5";
			LoanTerm = "30";
			PropTax = "70000";
			PMI = "2.0";
			HomeIns = "1100";
			MnthHOA = "145";	
		
		}

		// Step1: open mortage calculators url
		driver.navigate().to("https://www.mortgagecalculator.org/");

	
		// Get Current window ID
		String Currentwindow_id = driver.getWindowHandle();
		System.out.println(Currentwindow_id);

		// Step1.a: print the url on console
		String url = driver.getCurrentUrl();
		System.out.println(url);

		
		// Step2: Check if you are on "Mortagecalculator page", if yes print the the
		// header/ use boolean
		WebElement pageheader = driver
				.findElement(By.xpath("//*[@id=\"calc\"]/form/section/section[2]/div/div/div[1]/div/h2"));
		boolean status = pageheader.isDisplayed();
		System.out.println("Are we on the right page? " + status);

		// Step3:Capture the "Morgage calculator" title and save to a string and print
		// it
		String capturedheader = driver
				.findElement(By.xpath("//*[@id=\"calc\"]/form/section/section[2]/div/div/div[1]/div/h2")).getText();
		System.out.println("The captured header is: " + capturedheader);

		// Step4: enter the Home Value
		driver.findElement(By.id("homeval")).sendKeys(Homevalue);

		
		// Step5: Enter down payments
		driver.findElement(By.id("downpayment")).sendKeys(DownPay);

		
		// Step6: enter loan amount
		// driver.findElement(By.id("loanamt")).sendKeys("400000");

		
		// Step7: enter interest rate
		driver.findElement(By.id("intrstsrate")).sendKeys(IntRate);
		

		
		// Step8: enter loan term
		driver.findElement(By.id("loanterm")).sendKeys(LoanTerm);

		
		// Step9: select start month
		Select dropdow = new Select(driver.findElement(By.xpath(
				"//*[@id=\"calc\"]/form/section/section[2]/div/div/div[1]/div/div/div[4]/div[1]/div[1]/div[8]/select")));
		dropdow.selectByVisibleText("Dec");

		
		// Step10: enter start year
		driver.findElement(By.id("start_year")).sendKeys("2019");

		
		// Step11: enter property tax
		driver.findElement(By.id("pptytax")).sendKeys(PropTax);

		
		// Step12: enter pmi
		driver.findElement(By.id("pmi")).sendKeys(PMI);

		
		// Step13: enter home ins
		driver.findElement(By.id("hoi")).sendKeys(HomeIns);

		
		// Step14: enter monthly HOA
		driver.findElement(By.id("hoa")).sendKeys(MnthHOA);

		
		// Step15:select loan type
		Select loantype = new Select(driver.findElement(By.name("param[milserve]")));
		loantype.selectByVisibleText("Conventional");

		
		// Step16: click on Cmpare Loan Types
		driver.findElement(By.name("loantypebutton")).click();

		
		// switch to another window
		// Set <String> Captured_ids = driver.getWindowHandles();
		// System.out.println(Captured_ids);

		// System.out.println(Captured_ids.size());
		Thread.sleep(5000);
		driver.switchTo().frame(1);

		System.out.println("swithched to iframe");
		// WORKING WITH SWITCH FROM 1 WINDOW TO ANOTHER
		/*
		 * for (String cap_browser_ids:Captured_ids){ if (cap_browser_ids !=
		 * Currentwindow_id){ driver.switchTo().window(cap_browser_ids); }
		 * 
		 * }
		 */

		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'FHA Loan')]")));
		// Step17: Capture FHA Loan

		String CapturedFHA = driver.findElement(By.xpath("//h1[contains(text(),'FHA Loan')]")).getText();
		System.out.println(CapturedFHA);
		
		// verify and compare the Minimum Down Payment:3.5%
		String expectedMessage = "Minimum Down Payment: 3.5%";
		
		String capturedMessage = driver.findElement(By.xpath("/html/body/div/div[1]/ul/li[1]")).getText();
		
		try {
		Assert.assertEquals(capturedMessage,expectedMessage);
		}catch(AssertionError errormessage) {
			System.out.println(" assertion comparison-------------the comparison was unsuccessfull, they are not the same");
			throw errormessage;
		}
		
		System.out.println("assertion comparison--------------------the comparison was successfull, they are the same");
		
		
		String outputcapval = (capturedMessage==expectedMessage)?"Trenary operator******************the comparison was successfull" : "Trenary operator******************the comparison was not successful";
		System.out.println(outputcapval);
		
		
		driver.switchTo().defaultContent();
		
		// Step18: Close the FHA Loan Page
		driver.findElement(By.xpath("//button[@id='cboxClose']")).click();
		// driver.switchTo().frame(Currentwindow_id);
		System.out.println("X is not responding");

		// driver.close();

		// Step19:Select Buy or Refi
		Select purchasetype = new Select(driver.findElement(By.name("param[refiorbuy]")));
		purchasetype.selectByVisibleText("Buy");

		
		// Step20: Select Credit Rating
		Select CreditRating = new Select(driver.findElement(By.xpath("//*[@id=\"credit_rating\"]")));
		CreditRating.selectByVisibleText("Excellent (720+)");

		Thread.sleep(2000);
		// Step21: Click on show Amortization Tables
		driver.findElement(By.xpath("//a[contains(text(),'Show Amortization Tables')]")).click();

	
		// Step22: Mark Draw Charts, if its mark then don't click on it, if not mark
		// then click on it and mark it
		WebElement option1 = driver.findElement(By.id("draw_charts_i"));
		// this will togle the check box
		option1.click();
		// check wheter the check box is toggled on
		if (option1.isSelected()) {
			System.out.println("The Draw Charts Checkbox is Toggled on");
		} else {
			System.out.println("The Draw Charts checkbox is toggled off, and will be toggled on back");

		}


		// this should toggle off the checkbox
		option1.click();
		// lets see if its toggled off
		if (!option1.isSelected()) {
			System.out.println("The Draw Charts Checkbox is not toggled off!!");
		}

		// Step23: Mark annual amortization tables, if its mark thsen don't click on it,
		// if not mark then click on it and mark it
		WebElement AnnualAmortization = driver.findElement(By.id("show_annual_i"));
		// put the condition

		if (AnnualAmortization.isSelected()) {
			System.out.println("The annual amortization Checkbox is Toggled on");
		} else {
			System.out.println("The annual amortization checkbox is toggled off, and will be toggled on back");

		}

	
		// this should toggle off the checkbox
		AnnualAmortization.click();
		// lets see if its toggled off
		if (!option1.isSelected()) {
			System.out.println("The annual amortization Checkbox is not toggled off!!");
		}

		/*
		 * for (int i=0; i<2; i++) { AnnualAmortization.click();
		 * System.out.println(AnnualAmortization.isSelected());
		 * 
		 * }
		 */
		
		// Step24: show Monthly amortization table, if its mark then don't click on it,
		// if not mark then click on it and mark it
		WebElement ShowMonthlyAmo = driver.findElement(By.id("show_monthly_i"));
		// put the condition
		if (ShowMonthlyAmo.isSelected()) {
			System.out.println("The Show Month Amortization Checkbox is Toggled on ");
		} else {
			System.out.println("The Show Month Amortization Checkbox is Toggled off, and will be toggled on back ");
		}
		
		ShowMonthlyAmo.click();
		// lets see if its toggled off
		if (!ShowMonthlyAmo.isSelected()) {
			System.out.println("he Show Month Amortization Checkbox is Toggled off!!!!");
		}

	

		// Step25.1 Click on Calculate button
		driver.findElement(By.xpath("//*[@id=\"calc\"]/form/section/section[2]/div/div/div[1]/div/div/div[4]/div[1]/div[1]/div[18]/input"))
				.click();

		// Step25:Click on Balance Link for chart, if its mark then don't click on it,
		// if not mark then click on it and mark it
		// WebElement Balance = driver.findElement(By.)

		//Thread.sleep(1000);
		// Step26: Hover on 2049

		//Thread.sleep(1000);
		// Step27: Capture the intrest from the 2049 hover poped box

		//Thread.sleep(1000);
		// Step28: Click on View Loan Breakdown
		driver.findElement(By.xpath("//*[@id=\"toggle_pie\"]")).click();

		//Thread.sleep(1000);
		// Step29: Hover on principal

		//Thread.sleep(1000);
		// Step30: Capture principal and print it on console


		// Step31: Capture Total Monthly Payment from the Mortgage Repayment Summary
		String TotalMonthlyPay = driver.findElement(By.xpath("//*[@id=\"calc\"]/form/section/section[2]/div/div/div[1]/div/div/div[4]/div[2]/div/div[1]/div[1]/h3"))
				.getText();
		System.out.println("The captured total Monthly Pay under Mortgage Repayment Summary is: " + TotalMonthlyPay);

	
		// Step32: From Yearly Amortization Schedule capture the interest for year 2031
		String CapturedInt = driver.findElement(By.xpath("//*[@id=\"schedule\"]/div[14]/div[2]")).getText();
		System.out.println("The Captured Total Interest for the year 2031 is: " + CapturedInt);
		System.out.println("````````````````````````````````````````````````````````````````");
		// Step33: From Monthly Amortization Schedule capture the Principle for the
		// month of Oct, 2020.
		
		System.out.println("The Script for iteration "+ i+"  ran successfully");
		System.out.println("****************************************************");
		}
	
		System.out.println("The Script for all iterations  ran successfully");
	
		// Step34: Close the Browser
		driver.close();
	}

}
