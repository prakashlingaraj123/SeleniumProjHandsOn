package selFramework.frameworkdev.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import selFramework.frameworkdev.BaseTestComponents.BaseClass;

public class ProductCatalogTest extends BaseClass{
	@Test(groups = {"ProdCat"})
	public void filterTest() {
		boolean fil = productsObj.checkFilter("Filters");
		Assert.assertTrue(fil);
		
	}
	@Test
	public void showingResultsTest() {
		boolean shw = productsObj.showingResultd("Showing");
		Assert.assertTrue(shw);
	}
	
}
