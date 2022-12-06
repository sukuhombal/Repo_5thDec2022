package pages;

import org.openqa.selenium.By;

public class LoginPage {
	public static By obj_UserName_Edit = By.xpath("//input[@id='username']");
	public static By obj_Password_Edit = By.xpath("//input[@name='pwd']");
	public static By obj_Login_Btn =  By.xpath("//a[@id='loginButton']");
	public static By obj_Login_Header = By.id("headerContainer");
	public static By obj_Login_Logo = By.xpath("//img[contains(@src, 'timer.png')]");
	public static By obj_StartExplore_Btn = By.xpath("//span[@class='startExploringText']");
	
}
