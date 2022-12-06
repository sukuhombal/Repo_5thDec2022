package pages;

import org.openqa.selenium.By;

public class HomePage {
	public static By obj_Logout_Link = By.xpath("//a[@id='logoutLink']");
	public static By obj_HomePage_Text = By.xpath("//td[@class='pagetitle']");
	public static By obj_Shortcut_Window = By.xpath("//div[@style='display: block;']");
	public static By obj_Shortcut_close_btn = By.id("gettingStartedShortcutsMenuCloseId");
}
