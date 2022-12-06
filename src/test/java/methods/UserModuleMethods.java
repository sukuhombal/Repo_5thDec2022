package methods;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import driver.DriverScript;
import pages.UserModulePage;

public class UserModuleMethods extends DriverScript{
	/*******************************
	 * Method Name	: createUser()
	 * purpose		:
	 * Author		:
	 *******************************/
	public String createUser(WebDriver oBrowser, Map<String, String> testData) {
		String userName = null;
		try {
			Assert.assertTrue(appInd.clickObject(oBrowser, UserModulePage.obj_USERS_Menu), "Failed to click the 'USERS' menu");
			appDep.waitForElement(oBrowser, UserModulePage.obj_AddUser_Btn, "Clickable", "", 10);
			Assert.assertTrue(appInd.clickObject(oBrowser, UserModulePage.obj_AddUser_Btn), "Failed to click the 'Add  Users' button");
			appDep.waitForElement(oBrowser, UserModulePage.obj_Users_FirstName_Edit, "Clickable", "", 10);
			
			//Enter user details
			userName = testData.get("User_LastName") + ", " + testData.get("User_FirstName");
			Assert.assertTrue(appInd.setObject(oBrowser, UserModulePage.obj_Users_FirstName_Edit, testData.get("User_FirstName")), "Failed to enter the value in the 'First Name' edit field");
			Assert.assertTrue(appInd.setObject(oBrowser, UserModulePage.obj_Users_LastName_Edit, testData.get("User_LastName")), "Failed to enter the value in the 'Last Name' edit field");
			Assert.assertTrue(appInd.setObject(oBrowser, UserModulePage.obj_Users_Email_Edit, testData.get("User_Email")), "Failed to enter the value in the 'Email' edit field");
			Assert.assertTrue(appInd.setObject(oBrowser, UserModulePage.obj_Users_UserName_Edit, testData.get("User_UserName")), "Failed to enter the value in the 'User Name' edit field");
			Assert.assertTrue(appInd.setObject(oBrowser, UserModulePage.obj_Users_Password_Edit, testData.get("User_Password")), "Failed to enter the value in the 'Password' edit field");
			Assert.assertTrue(appInd.setObject(oBrowser, UserModulePage.obj_Users_RetypePwd_Edit, testData.get("User_Retype_Password")), "Failed to enter the value in the 'Retype Psssword' edit field");
			
			Assert.assertTrue(appInd.clickObject(oBrowser, UserModulePage.obj_CreateUser_Btn), "Failed to click 'Creat User' button");
			appDep.waitForElement(oBrowser, By.xpath(UserModulePage.obj_UserList_Grid + "/span[text()='"+userName+"']"), "Text", userName, 10);
			
			Assert.assertTrue(appInd.verifyElementPresent(oBrowser, By.xpath(UserModulePage.obj_UserList_Grid + "/span[text()='"+userName+"']")));
			reports.writeReport(oBrowser, "Screenshot", "The user '"+userName+"' was created successful");
			return userName;
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'createUser()' method. " + e.getMessage());
			return null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: deleteUser()
	 * purpose		:
	 * Author		:
	 *******************************/
	public boolean deleteUser(WebDriver oBrowser, String userName) {
		try {
			Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath(UserModulePage.obj_UserList_Grid + "/span[text()='"+userName+"']")), "Failed to click on the '"+userName+"' link");
			appDep.waitForElement(oBrowser, UserModulePage.obj_DeleteUser_Btn, "Clickable", "", 10);
			Assert.assertTrue(appInd.clickObject(oBrowser, UserModulePage.obj_DeleteUser_Btn), "Failed to click 'Delete User' button");
			appDep.waitForElement(oBrowser, null, "Alert", "", 10);
			oBrowser.switchTo().alert().accept();
			appDep.waitForElement(oBrowser, UserModulePage.obj_DeleteUser_Btn, "Invisibility", "", 5);
			
			Assert.assertTrue(appInd.verifyElementNotPresent(oBrowser, By.xpath(UserModulePage.obj_UserList_Grid + "/span[text()='"+userName+"']")), "The user '"+userName+"' still exist");
			reports.writeReport(oBrowser, "Screenshot", "The user '"+userName+"' was deleted successful");
			return true;
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'deleteUser()' method. " + e.getMessage());
			return false;
		}
	}
}
