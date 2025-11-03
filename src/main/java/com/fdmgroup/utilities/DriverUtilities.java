package com.fdmgroup.utilities;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverUtilities {
	private static DriverUtilities driverUtilities;
	private WebDriver driver;
	
	private DriverUtilities() {
		
	}
	
	public static DriverUtilities getInstance() {
		if (driverUtilities == null) {
			driverUtilities = new DriverUtilities();
		}
		return driverUtilities;
	}
	
	public WebDriver getDriver() {
		if(driver == null) {
			createDriver();
		}
		return driver;
	}
	
	public static void resetDriver() {
		driverUtilities.driver.quit();
		driverUtilities.driver = null;
		driverUtilities = null;
	}

	private void createDriver() {
		String driverName = getDriverName();
		
		switch(driverName) {
		case("Chrome"):
			this.driver = new ChromeDriver();
			break;
		case("Firefox"):
			this.driver = new FirefoxDriver();
			break;
		case("Edge"):
			this.driver = new EdgeDriver();
			break;
		default:
			break;
		}
	}

	private String getDriverName() {
		Properties config = new Properties();
		try {
			config.load(new FileInputStream("src/test/resources/config.properties"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return config.getProperty("browser");
	}
}
