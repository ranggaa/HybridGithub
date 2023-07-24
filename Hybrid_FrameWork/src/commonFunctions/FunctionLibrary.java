package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
//method for admin login
	public static boolean pBLogin(String username,String password)
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLoginbtn"))).click();
		String Expected ="adminflow";
		String Actual = driver.getCurrentUrl();
		if(Actual.toLowerCase().contains(Expected.toLowerCase()))
		{
			Reporter.log("Admin Login Success::"+Expected+"     "+Actual,true);
			return true;
		}
		else
		{
			Reporter.log("Admin Login Fail::"+Expected+"     "+Actual,true);
			return false;
		}
	}
	//method for clickbranches
	public static void pbBranches()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjClickBrances"))).click();
	}
	//method for new Branch creation
	public static boolean pbNewBranch(String BranchName,String Address1,String Address2,
			String Address3,String Area,String Zipcode,String Country,String State,String City) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjBranchName"))).sendKeys(BranchName);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress1"))).sendKeys(Address1);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress2"))).sendKeys(Address2);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress3"))).sendKeys(Address3);
		driver.findElement(By.xpath(conpro.getProperty("ObjArea1"))).sendKeys(Area);
		driver.findElement(By.xpath(conpro.getProperty("Objzipcode"))).sendKeys(Zipcode);
		new Select(driver.findElement(By.xpath(conpro.getProperty("ObjCountry")))).selectByVisibleText(Country);
		new Select(driver.findElement(By.xpath(conpro.getProperty("Objstate")))).selectByVisibleText(State);
		new Select(driver.findElement(By.xpath(conpro.getProperty("Objcity")))).selectByVisibleText(City);
		driver.findElement(By.xpath(conpro.getProperty("ObjSubmitbtn"))).click();
		Thread.sleep(5000);
		//capture alert message
		String Expected_Alert= driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String Actual_Alert ="new Branch with id";
		if(Expected_Alert.toLowerCase().contains(Actual_Alert.toLowerCase()))
		{
			Reporter.log(Expected_Alert,true);
			return true;
		}
		else
		{
			Reporter.log("Fail to create new Branch",true);
			return false;
		}
		
	}
	//method for branch updation
	public static boolean pBBranchUpdate(String BranchName,String Address,String Area,String ZipCode) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjEdit"))).click();
		WebElement element1 =driver.findElement(By.xpath(conpro.getProperty("ObjBranch")));
		element1.clear();
		element1.sendKeys(BranchName);
		WebElement element2 =driver.findElement(By.xpath(conpro.getProperty("ObjAddress")));
		element2.clear();
		element2.sendKeys(Address);
		WebElement element3 =driver.findElement(By.xpath(conpro.getProperty("ObjArea")));
		element3.clear();
		element3.sendKeys(Area);
		WebElement element4 =driver.findElement(By.xpath(conpro.getProperty("Objzip")));
		element4.clear();
		element4.sendKeys(ZipCode);
		driver.findElement(By.xpath(conpro.getProperty("ObjUpdateBtn"))).click();
		Thread.sleep(4000);
		String Expected_Alert = driver.switchTo().alert().getText();
		Thread.sleep(4000);
		driver.switchTo().alert().accept();
		String Actual_Alert ="Branch updated";
		if(Expected_Alert.toLowerCase().contains(Actual_Alert.toLowerCase()))
		{
			Reporter.log(Expected_Alert,true);
			return true;
		}
		else
		{
			Reporter.log("Fail To Update New Branch",true);
			return false;
		}
		}
	//method for logout
			public static boolean pbLogout()
			{
				driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
				if(driver.findElement(By.xpath(conpro.getProperty("ObjLoginbtn"))).isDisplayed())
				{
					Reporter.log("Admin Logout Success",true);
					return true;
				}
				else
				{
					Reporter.log("Admin Logout Fail",true);
					return false;
				}
	}
	
}












