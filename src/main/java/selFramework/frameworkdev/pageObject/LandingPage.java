package selFramework.frameworkdev.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selFramework.frameworkdev.AbstractComponets.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//driver.findElement(By.id("userEmail")).sendKeys("prakash@lp.com");
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.cssSelector("[id='userPassword']")).sendKeys("Prakash3@");
	@FindBy(css="[id='userPassword']")
	WebElement  password;
	
	//driver.findElement(By.cssSelector("[id='login']")).click();
	@FindBy(css="[id='login']")
	WebElement submit;
	
	public void loginApplication(String userMail, String userpassword) {
		userEmail.sendKeys(userMail);
		password.sendKeys(userpassword);
		submit.click();
	}
	
	public void goTo(String url) {
		driver.get(url);
		
	}
	
	
}
