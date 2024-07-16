package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
public static WebDriver driver;
	/* UnComment line 31 and comment line 29 while running the CossBrowserTesting.xml file */
//public WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups= {"Regression","Sanity","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading config.properties file
		FileReader file = new FileReader("./src//test//resources/config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			//select os
			switch(os)
			{
				case "windows": cap.setPlatform(Platform.WIN11);break;
				case "mac": cap.setPlatform(Platform.MAC);break;
				case "linux": cap.setPlatform(Platform.LINUX);break;
				default: System.out.println("No matching OS found!"); return;
			}
			//select browser
			switch(br)
			{
				case "chrome": cap.setBrowserName("chrome");break;
				case "edge": cap.setBrowserName("MicrosoftEdge");break;
				case "firefox": cap.setBrowserName("firefox");break;
				default: System.out.println("No matching Browser found!"); return;
			}
			driver = new RemoteWebDriver(new URL("http://192.168.1.11:4444/wd/hub"),cap);
		}
		else
		{  //execution_env = local
			switch(br.toLowerCase())
			{
				case "chrome": driver = new ChromeDriver();break;
				case "edge": driver = new EdgeDriver(); break;			 
				default: System.out.println("Wrong browser options");return;			
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master","DataDriven"})
	public void teardown() {
		driver.quit();
	}
	
	public String generateRandomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String generateRandomNumber() {
		String generatedString = RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	public String generateRandomPassword() {
		String generatedString = RandomStringUtils.randomAlphabetic(4)+ "@"+ RandomStringUtils.randomNumeric(3);
		return generatedString;
	}
	
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
