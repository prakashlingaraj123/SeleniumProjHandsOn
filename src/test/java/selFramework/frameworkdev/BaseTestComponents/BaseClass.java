package selFramework.frameworkdev.BaseTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Network.UserAgent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import selFramework.frameworkdev.pageObject.LandingPage;
import selFramework.frameworkdev.pageObject.ProductCatalog;

public class BaseClass {
	public WebDriver driver;
	Properties prop;
	public ProductCatalog productsObj;

	public WebDriver initilizeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\selFramework\\frameworkdev\\resources\\globalProperties.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(option);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox code
		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge code
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

//Data reader. read data form Json

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap- Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}
	public void getScreenShot(String testcaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\selFramework\\frameworkdev\\ScreenShots"+testcaseName+".png");
		FileUtils.copyFile(source, Dest);
		//return System.getProperty("user.dir")
				//+ "\\src\\test\\java\\selFramework\\frameworkdev\\ScreenShots"+testcaseName+".png";
	}

	@BeforeMethod(alwaysRun = true)
	public ProductCatalog loginApplication() throws IOException {
		driver = initilizeDriver();
		String url = prop.getProperty("url");
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo(url);
		String email = prop.getProperty("email");
		String password = prop.getProperty("password");
		landingpage.loginApplication(email, password);
		productsObj = new ProductCatalog(driver);
		return productsObj;

	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		driver.close();
	}
}
