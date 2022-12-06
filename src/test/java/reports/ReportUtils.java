package reports;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import driver.DriverScript;

public class ReportUtils extends DriverScript{
	/*******************************
	 * Method Name: startExtentReport()
	 * 
	 * 
	 *******************************/
	public ExtentReports startExtentReport(String fileName) {
		String resultPath = null;
		File objResultPath = null;
		File objScreenshotPath = null;
		try {
			resultPath = System.getProperty("user.dir") + "\\Results";
			objResultPath = new File(resultPath);
			if(!objResultPath.exists()) {
				objResultPath.mkdirs();
			}
			
			screenshotLocation = objResultPath + "\\screenshot";
			objScreenshotPath = new File(screenshotLocation);
			if(!objScreenshotPath.exists()) {
				objScreenshotPath.mkdirs();
			}
			
			extent = new ExtentReports(resultPath + "\\" + fileName + ".html");
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			extent.addSystemInfo("Environment", appInd.getPropData("Environment"));
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
			return extent;
		}catch(Exception e) {
			System.out.println("Exception in 'startExtentReport()' method. " + e.getMessage());
			return null;
		}finally {
			resultPath = null;
			objResultPath = null;
			objScreenshotPath = null;
		}
	}
	
	
	/*******************************
	 * Method Name: endExtentReport()
	 * 
	 * 
	 *******************************/
	public void endExtentReport(ExtentTest test) {
		try {
			extent.endTest(test);
			extent.flush();
		}catch(Exception e) {
			System.out.println("Exception in 'endExtentReport()' method. " + e.getMessage());
		}
	}
	
	
	
	/*******************************
	 * Method Name: captureScreenshot()
	 * 
	 * 
	 *******************************/
	public String captureScreenshot(WebDriver oBrowser) {
		File objSource = null;
		String strDestination = null;
		File objDestination = null;
		try {
			if(oBrowser != null) {
				strDestination = screenshotLocation + "\\" + "screenshot_" + appInd.getDateTime("hhmmss")+".png";
				TakesScreenshot ts = (TakesScreenshot) oBrowser;
				objSource = ts.getScreenshotAs(OutputType.FILE);
				objDestination = new File(strDestination);
				FileHandler.copy(objSource, objDestination);
			}
			return strDestination;
		}catch(Exception e) {
			System.out.println("Exception in 'captureScreenshot()' method. " + e.getMessage());
			return null;
		}finally {
			objSource = null;
			strDestination = null;
			objDestination = null;
		}
	}
	
	
	/*******************************
	 * Method Name: writeReport()
	 * 
	 * 
	 *******************************/
	public void writeReport(WebDriver oBrowser, String status, String strMessage) {
		try {
			switch(status.toLowerCase()) {
				case "pass":
					test.log(LogStatus.PASS, strMessage);
					break;
				case "fail":
					test.log(LogStatus.FAIL, strMessage 
							+ " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "warning":
					test.log(LogStatus.WARNING, strMessage);
					break;
				case "exception":
					test.log(LogStatus.FATAL, strMessage 
							+ " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "info":
					test.log(LogStatus.INFO, strMessage);
					break;
				case "screenshot":
					test.log(LogStatus.INFO, strMessage 
							+ " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				default:
					System.out.println("Invalid report status '"+status+"' provided. Please provide the appropriate status");		
			}
		}catch(Exception e) {
			System.out.println("Exception in 'writeReport()' method. " + e.getMessage());
		}
	}
	
}
