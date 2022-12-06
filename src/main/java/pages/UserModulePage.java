package pages;

import org.openqa.selenium.By;

public class UserModulePage {
	public static By obj_USERS_Menu = By.xpath("//div[text()='USERS']");
	public static By obj_AddUser_Btn = By.xpath("//div[text()='Add User']");
	public static By obj_Users_FirstName_Edit = By.xpath("//input[@name='firstName']");
	public static By obj_Users_LastName_Edit = By.xpath("//input[@name='lastName']");
	public static By obj_Users_Email_Edit = By.xpath("//input[@name='email']");
	public static By obj_Users_UserName_Edit = By.xpath("//input[@name='username']");
	public static By obj_Users_Password_Edit = By.xpath("//input[@name='password']");
	public static By obj_Users_RetypePwd_Edit = By.xpath("//input[@name='passwordCopy']");
	public static By obj_CreateUser_Btn = By.xpath("//span[text()='Create User']");
	public static By obj_DeleteUser_Btn = By.xpath("//button[@id='userDataLightBox_deleteBtn']");
	public static String obj_UserList_Grid = "//div[@class='name']";
	
}
