package selFramework.frameworkdev.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import selFramework.frameworkdev.AbstractComponets.AbstractComponents;

public class OrderDetails extends AbstractComponents {
	WebDriver driver;
	public OrderDetails(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary")
	WebElement greetingMes;
	By thankingMessage = By.cssSelector(".hero-primary");
	
	public boolean getThanksmes(String mes) {
		
		waitForElementVisibility(thankingMessage);
		boolean message = greetingMes.getText().equalsIgnoreCase(mes);
		return message;
	}
}
