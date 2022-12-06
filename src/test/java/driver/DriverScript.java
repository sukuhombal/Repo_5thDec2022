package driver;

import java.lang.reflect.Method;
import java.util.Map;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import methods.Datatable;
import methods.TaskModuleMethods;
import methods.UserModuleMethods;
import reports.ReportUtils;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static ReportUtils reports = null;
	public static Datatable datatable = null;
	public static TaskModuleMethods taskMethods = null;
	public static UserModuleMethods userMethods = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String screenshotLocation = null;
	
	@BeforeSuite
	public void loadClasses() {
		try {
			appInd = new AppIndependentMethods();
			appDep = new AppDependentMethods();
			datatable = new Datatable();
			userMethods = new UserModuleMethods();
			taskMethods = new TaskModuleMethods();
			reports = new ReportUtils();
			extent = reports.startExtentReport("AutomationResports");
		}catch(Exception e) {
			System.out.println("Exception in 'loadClasses()' method. " + e.getMessage());
		}
	}
	
	
	@DataProvider(name = "testData")
	public Object[][] getDataProvider(){
		return datatable.createDataProvider("Runner", "Controller");
	}
	
	
	@Test(dataProvider = "testData")
	public void runTestScripts(Map<String, String> data) {
		Class cls = null;
		Object obj = null;
		Method script = null;
		try {
			cls = Class.forName(data.get("ClassName"));
			obj = cls.getDeclaredConstructor().newInstance();
			script = obj.getClass().getMethod(data.get("ScriptName"));
			
			boolean blnRes = (boolean) script.invoke(obj);
			if(blnRes) {
				reports.writeReport(null, "Pass", "The test script '"+data.get("ScriptName")+"' was passed");
			}else {
				reports.writeReport(null, "Fail", "The test script '"+data.get("ScriptName")+"' was failed");
			}
		}catch(Exception e) {
			System.out.println("Exception in 'runTestScripts()' method. " + e.getMessage());
		}finally {
			cls = null;
			obj = null;
			script = null;
		}
	}
}
