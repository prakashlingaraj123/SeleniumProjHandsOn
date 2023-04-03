package selFramework.frameworkdev.pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selFramework.frameworkdev.AbstractComponets.AbstractComponents;

public class Payment extends AbstractComponents{
	WebDriver driver;
	public Payment(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement counteyTestBox;
	
	@FindBy(css=".ta-results.list-group.ng-star-inserted button")
	List<WebElement> countries;
	
	@FindBy(xpath="//div[contains(text(),'CVV Code')]/following-sibling::input")
	WebElement cvv;
	@FindBy(xpath="//div[contains(text(),'Name on Card')]/following-sibling::input")
	WebElement nameOntheCard;
	@FindBy(xpath="//div[contains(text(),'Apply Coupon')]/following-sibling::input")
	WebElement applyCoupanText;
	@FindBy(css=".btn.btn-primary.mt-1")
	WebElement applyCoupan;
	@FindBy(xpath="//ngx-spinner[contains(@class,'ng-star-inserted')]/div")
	WebElement spinner;
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	By countriesDropdown = By.cssSelector(".ta-results.list-group.ng-star-inserted");
	By applycoupanLocator = By.cssSelector(".btn.btn-primary.mt-1");
	
	public void selectCountry(String partialText, String country) {
		counteyTestBox.sendKeys(partialText);
		waitForElementVisibility(countriesDropdown);
		countries.stream().filter(cn -> cn.getText().equals(country)).findAny().orElse(null).click();
	}
	public void cardDetails(String cvvNum, String name, String cupanCode)
	{
		cvv.sendKeys(cvvNum);
		nameOntheCard.sendKeys(name);
		scrollBy();
		applyCoupanText.sendKeys(cupanCode);
		waitforElementClickable(applycoupanLocator);
		applyCoupan.click();
		waitForElementInvisibility(spinner);
	}
	public OrderDetails placeTheOrder() {
		placeOrder.click();
		OrderDetails order = new OrderDetails(driver);
		return order;
	}
	
}
