package selFramework.frameworkdev.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selFramework.frameworkdev.AbstractComponets.AbstractComponents;

public class ProductCatalog extends AbstractComponents{
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css= ".mb-3")
	List<WebElement> products;
	//driver.findElement(By.cssSelector(".la-ball-scale-multiple"))
	@FindBy(css=".la-ball-scale-multiple")
	WebElement spinner;
	@FindBy(css="#sidebar #burgundy")
	WebElement filters;
	@FindBy(id="res")
	WebElement showingresults;
	By productsEle = By.cssSelector(".mb-3");
	By ProductName = By.cssSelector("b");
	By AddtoCart = By.cssSelector(".card-body button:last-of-type");
	
	
	//WebElement ele = products.stream().filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(item))
			//.findFirst().orElse(null);
	
	public List<WebElement> getProductlList() {
		waitForElementVisibility(productsEle);
		return products;
	}
	
	public WebElement getProductbyName(String item) {
		WebElement ele = products.stream().filter(prod -> prod.findElement(ProductName).getText().equals(item))
				.findFirst().orElse(null);
		return ele;
	}
	
	public void addTocart(String item) {
		WebElement prodname = getProductbyName(item);
		prodname.findElement(AddtoCart).click();
		waitForElementInvisibility(spinner);
	}
	
	public boolean checkFilter(String filter) {
		waitForElementVisibility(productsEle);
		boolean fil = filters.getText().equalsIgnoreCase(filter);
		return fil;
	}
	public boolean showingResultd(String results) {
		waitForElementVisibility(productsEle);
		boolean fil = showingresults.getText().contains(results);
		return fil;
	}
	
	
}
