package methods;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import driver.DriverScript;
import pages.HomePage;
import pages.LoginPage;

public class AppDependentMethods extends DriverScript{
	
	/*******************************
	 * Method Name	: waitForElement()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean waitForElement(WebDriver oBrowser, By objBy, 
			String waitForReason, String text, long timeOut) {
		WebDriverWait oWait = null;
		try {
			oWait = new WebDriverWait(oBrowser, Duration.ofSeconds(timeOut));
			switch(waitForReason.toLowerCase()) {
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(objBy));
					break;
				case "text":
					oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, text));
					break;
				case "value":
					oWait.until(ExpectedConditions.textToBePresentInElementValue(objBy, text));
					break;
				case "visibility":
					oWait.until(ExpectedConditions.visibilityOfElementLocated(objBy));
					break;
				case "invisibility":
					oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
					break;
				case "alert":
					oWait.until(ExpectedConditions.alertIsPresent());
					break;
				default:
					System.out.println("Invalid wait reason/condition '"+waitForReason+"' specified");
					return false;
			}
			return true;
		}catch(Exception e) {
			System.out.println("Exception in 'waitForElement()' method. " + e.getMessage());
			return false;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: loginToActiTime()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean loginToActiTime(WebDriver oBrowser, String userType, String userName, String password) {
		try {
			Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_UserName_Edit, userName), "Failed to enter the userName");
			Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_Password_Edit, password), "Failed to enter the password");
			Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.obj_Login_Btn), "Failed to click the 'Login' button");
			
			if(userType.equalsIgnoreCase("New")) {
				waitForElement(oBrowser, LoginPage.obj_StartExplore_Btn, "Clickable", "", 10);
				Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.obj_StartExplore_Btn), "Failed to click 'Statr Exploring actiTime' button");
				reports.writeReport(oBrowser, "Screenshot", "Login with New user");
			}else {
				waitForElement(oBrowser, HomePage.obj_HomePage_Text, "Text", "Enter Time-Track", 10);
				reports.writeReport(oBrowser, "Screenshot", "Login with Existing user");
			}
			
			//Handle the shortcut window (Optional)
			if(appInd.verifyOptionalElementPresent(oBrowser, HomePage.obj_Shortcut_Window)) {
				Assert.assertTrue(appInd.clickObject(oBrowser, HomePage.obj_Shortcut_close_btn), "Failed to click the close shortcut button");
			}
			
			Assert.assertTrue(appInd.verifyText(oBrowser, HomePage.obj_HomePage_Text, "Text", "Enter Time-Track"), "Failed to display the Home Page title");
			reports.writeReport(oBrowser, "Screenshot", "login to actiTime was successful");
			return true;
		}catch(Exception e) {
			reports.writeReport(oBrowser, "Exception", "Exception in 'loginToActiTime()' method. "+ e.getMessage());
			return false;
		}catch(AssertionError e) {
			reports.writeReport(oBrowser, "Exception", "Assertion Exception in 'loginToActiTime()' method. "+ e.getMessage());
			return false;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: logoutFromActiTime()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean logoutFromActiTime(WebDriver oBrowser) {
		try {
			Assert.assertTrue(appInd.clickObject(oBrowser, HomePage.obj_Logout_Link), "Failed to click the 'Logout' button");
			waitForElement(oBrowser, LoginPage.obj_Login_Logo, "Clickable", "", 10);			
			Assert.assertTrue(appInd.verifyText(oBrowser, LoginPage.obj_Login_Header, "Text", "Please identify yourself"), "Failed to display the Login Page header");
			reports.writeReport(oBrowser, "Screenshot", "logout from actiTime was successful");
			return true;
		}catch(Exception e) {
			reports.writeReport(oBrowser, "Exception", "Exception in 'logoutFromActiTime()' method. "+ e.getMessage());
			return false;
		}catch(AssertionError e) {
			reports.writeReport(oBrowser, "Exception", "Assertion Exception in 'logoutFromActiTime()' method. "+ e.getMessage());
			return false;
		}
	}
	
	
	/*******************************
	 * Method Name	: navigateURL()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean navigateURL(WebDriver oBrowser, String strURL) {
		try {
			oBrowser.navigate().to(strURL);
			waitForElement(oBrowser, LoginPage.obj_Login_Btn, "Clickable", "", 10);
			Assert.assertTrue(appInd.compareValue(oBrowser, oBrowser.getTitle(), "actiTIME - Login"), "Failed to display the login page title");
			reports.writeReport(oBrowser, "Screenshot", "URL was navigates successful");
			return true;
		}catch(Exception e) {
			reports.writeReport(oBrowser, "Exception", "Exception in 'navigateURL()' method. "+ e.getMessage());
			return false;
		}catch(AssertionError e) {
			reports.writeReport(oBrowser, "Exception", "Assertion exception in 'navigateURL()' method. "+ e.getMessage());
			return false;
		}
	}
}
