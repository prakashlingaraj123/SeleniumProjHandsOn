package selFramework.frameworkdev.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selFramework.frameworkdev.AbstractComponets.AbstractComponents;

public class YourOrders extends AbstractComponents {
	WebDriver driver;
	public YourOrders(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css=".ng-star-inserted td:nth-child(3)")
	List<WebElement> orderList;
	
	public boolean getOrders(String product) {
		 
		boolean matchProd = orderList.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(product));
		return matchProd;
	}
	
}
