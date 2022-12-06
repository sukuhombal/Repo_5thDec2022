package testScripts;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import driver.DriverScript;

public class UserModuleScripts extends DriverScript{
	/*******************************
	 * Method Name	: TS_LoginLogout()
	 * purpose		:
	 * Author		:
	 * Test case ID	: TC_101
	 *******************************/
	public boolean TS_LoginLogout() {
		WebDriver oBrowser = null;
		Map<String, String> dataMap = null;
		try {
			test = extent.startTest("TS_LoginLogout");
			dataMap = datatable.getExcelData("TestData", "TC_101");
			oBrowser = appInd.launchBrowser(appInd.getPropData("browserName"));
			Assert.assertTrue(appDep.navigateURL(oBrowser, appInd.getPropData("appURL")), "Failed to navigate the URL");
			Assert.assertTrue(appDep.loginToActiTime(oBrowser, "Existing", dataMap.get("UserName"), dataMap.get("Password")), "Failed to login to actiTime");
			Assert.assertTrue(appDep.logoutFromActiTime(oBrowser), "Failed to logout from actiTime");
			return true;
		}catch(Exception e) {
			reports.writeReport(oBrowser, "Exception", "Exception in 'TS_LoginLogout()' test script. " + e.getMessage());
			return false;
		}catch(AssertionError e) {
			reports.writeReport(oBrowser, "Exception", "Assertion exception in 'TS_LoginLogout()' test script. "+ e.getMessage());
			return false;
		}finally {
			reports.endExtentReport(test);
			oBrowser.close();
			oBrowser = null;
			dataMap = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: TS_createandDeleteUser()
	 * purpose		:
	 * Author		:
	 * Test case ID	: TC_102 
	 *******************************/
	public boolean TS_createandDeleteUser() {
		WebDriver oBrowser = null;
		Map<String, String> dataMap = null;
		String userName = null;
		try {
			test = extent.startTest("TS_createandDeleteUser");
			dataMap = datatable.getExcelData("TestData", "TC_102");
			oBrowser = appInd.launchBrowser(appInd.getPropData("browserName"));
			Assert.assertTrue(appDep.navigateURL(oBrowser, appInd.getPropData("appURL")), "Failed to navigate the URL");
			Assert.assertTrue(appDep.loginToActiTime(oBrowser, "Existing", dataMap.get("UserName"), dataMap.get("Password")), "Failed to login to actiTime");
			userName = userMethods.createUser(oBrowser, dataMap);
			Assert.assertTrue(userMethods.deleteUser(oBrowser, userName), "Failed to delete the user");
			Assert.assertTrue(appDep.logoutFromActiTime(oBrowser), "Failed to logout from actiTime");
			return true;
		}catch(Exception e) {
			reports.writeReport(oBrowser, "Exception", "Exception in 'TS_createandDeleteUser()' test script. " + e.getMessage());
			return false;
		}catch(AssertionError e) {
			reports.writeReport(oBrowser, "Exception", "Assertion exception in 'TS_createandDeleteUser()' test script. "+ e.getMessage());
			return false;
		}finally {
			reports.endExtentReport(test);
			oBrowser.close();
			oBrowser = null;
			dataMap = null;
		}
	}
	
	
	
	/*******************************
	 * Method Name	: TS_createUser_LoginWithNewUser_DeleteUser()
	 * purpose		:
	 * Author		:
	 * Test case ID	: TC_103
	 *******************************/
	public boolean TS_createUser_LoginWithNewUser_DeleteUser() {
		WebDriver oBrowser1 = null;
		WebDriver oBrowser2 = null;
		Map<String, String> dataMap = null;
		String userName = null;
		try {
			test = extent.startTest("TS_createUser_LoginWithNewUser_DeleteUser");
			dataMap = datatable.getExcelData("TestData", "TC_103_1");
			oBrowser1 = appInd.launchBrowser(appInd.getPropData("browserName"));
			Assert.assertTrue(appDep.navigateURL(oBrowser1, appInd.getPropData("appURL")), "Failed to navigate the URL");
			Assert.assertTrue(appDep.loginToActiTime(oBrowser1, "Existing", dataMap.get("UserName"), dataMap.get("Password")), "Failed to login to actiTime");
			userName = userMethods.createUser(oBrowser1, dataMap);
			
			dataMap = datatable.getExcelData("TestData", "TC_103_2");
			oBrowser2 = appInd.launchBrowser(appInd.getPropData("browserName"));
			Assert.assertTrue(appDep.navigateURL(oBrowser2, appInd.getPropData("appURL")), "Failed to navigate the URL");
			Assert.assertTrue(appDep.loginToActiTime(oBrowser2, "New", dataMap.get("UserName"), dataMap.get("Password")), "Failed to login to actiTime");
						
			Assert.assertTrue(userMethods.deleteUser(oBrowser1, userName), "Failed to delete the user");
			return true;
		}catch(Exception e) {
			reports.writeReport(oBrowser1, "Exception", "Exception in 'TS_createUser_LoginWithNewUser_DeleteUser()' test script. " + e.getMessage());
			return false;
		}catch(AssertionError e) {
			reports.writeReport(oBrowser1, "Exception", "Assertion exception in 'TS_createUser_LoginWithNewUser_DeleteUser()' test script. "+ e.getMessage());
			return false;
		}finally {
			reports.endExtentReport(test);
			oBrowser1.close();
			oBrowser1 = null;
			oBrowser2.close();
			oBrowser2 = null;
			dataMap = null;
		}
	}
}
