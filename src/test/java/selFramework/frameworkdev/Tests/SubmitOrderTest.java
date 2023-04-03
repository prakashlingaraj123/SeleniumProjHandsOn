package selFramework.frameworkdev.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.experimental.theories.FromDataPoints;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import selFramework.frameworkdev.AbstractComponets.AbstractComponents;
import selFramework.frameworkdev.BaseTestComponents.BaseClass;
import selFramework.frameworkdev.pageObject.LandingPage;
import selFramework.frameworkdev.pageObject.MyCart;
import selFramework.frameworkdev.pageObject.OrderDetails;
import selFramework.frameworkdev.pageObject.Payment;
import selFramework.frameworkdev.pageObject.ProductCatalog;
import selFramework.frameworkdev.pageObject.YourOrders;

public class SubmitOrderTest extends BaseClass{
	//String item = "ADIDAS ORIGINAL";
	@Test(dataProvider = "dataset",groups= {"SubOrder"})
	public  void placeOrder(HashMap<String,String> input) throws IOException {
		productsObj.addTocart(input.get("item"));
		MyCart cart = productsObj.goToCart();
		boolean match = cart.getCartItem(input.get("item"));
		Assert.assertTrue(match);
		Payment payment = cart.clickCheckout();
		payment.selectCountry("ind", "India");
		payment.cardDetails("789", "Prakash", "ABC");
		OrderDetails order = payment.placeTheOrder();
		boolean message = order.getThanksmes("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(message);
		
	}
	
	@Test(dataProvider = "dataset",dependsOnMethods = {"placeOrder"})
	public void orderValidation(HashMap<String,String> input) {
		YourOrders orders = productsObj.goToYourOrders();
		boolean match = orders.getOrders(input.get("item"));
		Assert.assertTrue(match);
	}
	@DataProvider
	public Object[][] dataset() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\selFramework\\frameworkdev\\JsonData\\SubmitOrderJsonData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
