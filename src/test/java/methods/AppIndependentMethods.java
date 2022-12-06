package methods;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import driver.DriverScript;

public class AppIndependentMethods extends DriverScript{
	/*******************************
	 * Method Name	: getPropData()
	 * purpose		:
	 * Author		:
	 * Reviewer		:
	 * Date Created	:
	 * Date Modified:
	 * Parameter	:
	 * return Type	:
	 *******************************/
	public String getPropData(String keyName) {
		FileInputStream fin = null;
		Properties prop = null;
		try {
			fin = new FileInputStream(System.getProperty("user.dir") + "/Configuration/Config.properties");
			prop = new Properties();
			prop.load(fin);
			
			return prop.getProperty(keyName);
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'getPropData()' method. " + e.getMessage());
			return null;
		}
		finally 
		{
			try {
				fin.close();
				fin = null;
				prop = null;
			}catch(Exception e) {}
		}
	}
	
	
	/*******************************
	 * Method Name	: getDateTime()
	 * purpose		:
	 * Author		:
	 *******************************/
	public String getDateTime(String format) {
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(format);
			return sdf.format(dt);
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'getDateTime()' method. " + e.getMessage());
			return null;
		}finally {
			dt = null;
			sdf = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: clickObject()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean clickObject(WebDriver OBrowser, By objBy) {
		List<WebElement> objEles = null;
		try {
			objEles = OBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				objEles.get(0).click();
				reports.writeReport(OBrowser, "Pass", "The WebElement '"+String.valueOf(objBy)+"' was clicked successful");
				return true;
			}else {
				reports.writeReport(OBrowser, "Fail", "The WebElement '"+String.valueOf(objBy)+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'clickObject()' method. " + e.getMessage());
			return false;
		}
		finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: clickObject()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean clickObject(WebDriver OBrowser, String strObjectName) {
		List<WebElement> objEles = null;
		try {
			objEles = OBrowser.findElements(By.xpath(strObjectName));
			if(objEles.size() > 0) {
				objEles.get(0).click();
				reports.writeReport(OBrowser, "Pass", "The WebElement '"+strObjectName+"' was clicked successful");
				return true;
			}else {
				reports.writeReport(OBrowser, "Fail", "The WebElement '"+strObjectName+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'clickObject()' method. " + e.getMessage());
			return false;
		}
		finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: setObject()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean setObject(WebDriver OBrowser, By objBy, String strData) {
		List<WebElement> objEles = null;
		try {
			objEles = OBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				objEles.get(0).sendKeys(strData);
				reports.writeReport(OBrowser, "Pass", "The data '"+strData+"' is entered in the WebElement '"+String.valueOf(objBy)+"' successful");
				return true;
			}else {
				reports.writeReport(OBrowser, "Fail", "The WebElement '"+String.valueOf(objBy)+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'setObject()' method. " + e.getMessage());
			return false;
		}
		finally {
			objEles = null;
		}
	}
	
	
	/*******************************
	 * Method Name	: setObject()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean setObject(WebDriver OBrowser, String strObjectName, String strData) {
		List<WebElement> objEles = null;
		try {
			objEles = OBrowser.findElements(By.xpath(strObjectName));
			if(objEles.size() > 0) {
				objEles.get(0).sendKeys(strData);
				reports.writeReport(OBrowser, "Pass", "The data '"+strData+"' is entered in the WebElement '"+strObjectName+"' successful");
				return true;
			}else {
				reports.writeReport(OBrowser, "Fail", "The WebElement '"+strObjectName+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'setObject()' method. " + e.getMessage());
			return false;
		}
		finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: clearAndSetObject()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean clearAndSetObject(WebDriver OBrowser, String strObjectName, String strData) {
		List<WebElement> objEles = null;
		try {
			objEles = OBrowser.findElements(By.xpath(strObjectName));
			if(objEles.size() > 0) {
				objEles.get(0).clear();
				objEles.get(0).sendKeys(strData);
				reports.writeReport(OBrowser, "Pass", "The data '"+strData+"' is entered in the WebElement '"+strObjectName+"' successful");
				return true;
			}else {
				reports.writeReport(OBrowser, "Fail", "The WebElement '"+strObjectName+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'clearAndSetObject()' method. " + e.getMessage());
			return false;
		}
		finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: clearAndSetObject()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean clearAndSetObject(WebDriver oBrowser, By objBy, String strData) {
		List<WebElement> objEles = null;
		try {
			objEles = oBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				objEles.get(0).clear();
				objEles.get(0).sendKeys(strData);
				reports.writeReport(oBrowser, "Pass", "The data '"+strData+"' is entered in the WebElement '"+String.valueOf(objBy)+"' successful");
				return true;
			}else {
				reports.writeReport(oBrowser, "Fail", "The WebElement '"+String.valueOf(objBy)+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'clearAndSetObject()' method. " + e.getMessage());
			return false;
		}
		finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: verifyElementPresent()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean verifyElementPresent(WebDriver oBrowser, By objBy) {
		List<WebElement> objEles = null;
		try {
			objEles = oBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				reports.writeReport(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was present int he DOM");
				return true;
			}else {
				reports.writeReport(oBrowser, "Fail", "The WebElement '"+String.valueOf(objBy)+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'verifyElementPresent()' method. " + e.getMessage());
			return false;
		}finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: verifyElementPresent()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean verifyElementPresent(WebDriver oBrowser, String strObjectName) {
		List<WebElement> objEles = null;
		try {
			objEles = oBrowser.findElements(By.xpath(strObjectName));
			if(objEles.size() > 0) {
				reports.writeReport(oBrowser, "Pass", "The element '"+strObjectName+"' was present int he DOM");
				return true;
			}else {
				reports.writeReport(oBrowser, "Fail", "The WebElement '"+strObjectName+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'verifyElementPresent()' method. " + e.getMessage());
			return false;
		}finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: verifyElementNotPresent()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean verifyElementNotPresent(WebDriver oBrowser, String strObjectName) {
		List<WebElement> objEles = null;
		try {
			objEles = oBrowser.findElements(By.xpath(strObjectName));
			if(objEles.size() > 0) {
				reports.writeReport(oBrowser, "Fail", "The WebElement '"+strObjectName+"' was still present in the DOM");
				return false;
			}else {
				reports.writeReport(oBrowser, "Pass", "The element '"+strObjectName+"' was removed from the DOM");
				return true;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'verifyElementNotPresent()' method. " + e.getMessage());
			return false;
		}finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: verifyElementNotPresent()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean verifyElementNotPresent(WebDriver oBrowser, By objBy) {
		List<WebElement> objEles = null;
		try {
			objEles = oBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				reports.writeReport(oBrowser, "Fail", "The WebElement '"+String.valueOf(objBy)+"' was still present in the DOM");
				return false;
			}else {
				reports.writeReport(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was removed from the DOM");
				return true;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'verifyElementNotPresent()' method. " + e.getMessage());
			return false;
		}finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: compareValue()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean compareValue(WebDriver oBrowser, String actual, String expected) {
		try {
			if(actual.equals(expected)) {
				reports.writeReport(oBrowser, "Pass", "The actual '"+actual+"' & expected '"+expected+"' values are matching");
				return true;
			}else {
				reports.writeReport(oBrowser, "Fail", "Mis-match in the both actual '"+actual+"' & expected '"+expected+"' values");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'compareValue()' method. " + e.getMessage());
			return false;
		}
	}
	
	
	/*******************************
	 * Method Name	: verifyText()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean verifyText(WebDriver oBrowser, By objBy, String strObjectType, String expectedText) {
		List<WebElement> objEles = null;
		Select oSelect = null;
		String actualText = null;
		try {
			objEles = oBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				switch(strObjectType.toLowerCase()) {
					case "text":
						actualText = objEles.get(0).getText();
						break;
					case "value":
						actualText = objEles.get(0).getAttribute("value");
						break;
					case "dropdown":
						oSelect = new Select(objEles.get(0));
						actualText = oSelect.getFirstSelectedOption().getText();
						break;
					default:
						reports.writeReport(oBrowser, "Fail", "Invalid object type '"+strObjectType+"'");
				}
				
				if(compareValue(oBrowser, actualText, expectedText)) return true;
				else return false;
			}else {
				reports.writeReport(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was not present in the DOM");
				return true;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'verifyText()' method. " + e.getMessage());
			return false;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: verifyText()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean verifyText(WebDriver oBrowser, String strObjectName, String strObjectType, String expectedText) {
		List<WebElement> objEles = null;
		Select oSelect = null;
		String actualText = null;
		try {
			objEles = oBrowser.findElements(By.xpath(strObjectName));
			if(objEles.size() > 0) {
				switch(strObjectType.toLowerCase()) {
					case "text":
						actualText = objEles.get(0).getText();
						break;
					case "value":
						actualText = objEles.get(0).getAttribute("value");
						break;
					case "dropdown":
						oSelect = new Select(objEles.get(0));
						actualText = oSelect.getFirstSelectedOption().getText();
						break;
					default:
						reports.writeReport(oBrowser, "Fail", "Invalid object type '"+strObjectType+"'");
				}
				
				if(compareValue(oBrowser, actualText, expectedText)) return true;
				else return false;
			}else {
				reports.writeReport(oBrowser, "Pass", "The element '"+strObjectName+"' was not present in the DOM");
				return true;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'verifyText()' method. " + e.getMessage());
			return false;
		}finally {
			objEles = null;
			oSelect = null;
			actualText = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: selectObject()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean selectObject(WebDriver oBrowser, By objBy, String strData) {
		List<WebElement> objEles = null;
		Select oSelect = null;
		try {
			objEles = oBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				oSelect = new Select(objEles.get(0));
				oSelect.selectByVisibleText(strData);
				reports.writeReport(oBrowser, "Pass", "The data '"+strData+"' is selected from the dropdown element '"+String.valueOf(objBy)+"'");
				return true;
			}else {
				reports.writeReport(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was not present in the DOM");
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'selectObject()' method. " + e.getMessage());
			return false;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: verifyOptionalElementPresent()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean verifyOptionalElementPresent(WebDriver oBrowser, By objBy) {
		List<WebElement> objEles = null;
		try {
			objEles = oBrowser.findElements(objBy);
			if(objEles.size() > 0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'verifyOptionalElementPresent()' method. " + e.getMessage());
			return false;
		}finally {
			objEles = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: launchBrowser()
	 * purpose		:
	 * Author		:
	 *******************************/
	public WebDriver launchBrowser(String browserName) {
		WebDriver oDriver =  null;
		try {
			switch(browserName.toLowerCase()) {
				case  "chrome":
					System.setProperty("webdriver.chrome.driver", ".\\Library\\drivers\\chromedriver.exe");
					oDriver = new ChromeDriver();
					break;
				case  "firefox":
					System.setProperty("webdriver.gecko.driver", ".\\Library\\drivers\\geckodriver.exe");
					oDriver = new FirefoxDriver();
					break;
				case  "edge":
					System.setProperty("webdriver.edge.driver", ".\\Library\\drivers\\msedgedriver.exe");
					oDriver = new EdgeDriver();
					break;
				default:
					reports.writeReport(oDriver, "Fail", "Invalid browser name '"+browserName+"' was specicifed");
			}
			
			if(oDriver != null) {
				oDriver.manage().window().maximize();
				oDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				reports.writeReport(oDriver, "Pass", "The '"+browserName+"' browser launched successful");
				return oDriver;
			}else {
				reports.writeReport(oDriver, "Fail", "Failed to launch the '"+browserName+"' browser");
				return null;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'launchBrowser()' method. " + e.getMessage());
			return null;
		}
	}
}
