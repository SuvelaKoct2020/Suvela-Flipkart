package Asssignment.flipkart;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class IphoneData {

	private static WebDriver driver;
	ArrayList<Details> info = new ArrayList<Details>();
	static String driverPath = "C:\\Users\\91898\\git\\repository2\\flipkart\\flipkart\\Drivers\\chromedriver.exe";
	String filepath = "E:\\file.csv";

	@BeforeMethod
	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);

	}

	@Test(priority = 1)
	public static void getResult() {
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.className("_29YdH8")).click(); // Close pop up window
		driver.findElement(By.name("q")).sendKeys("iPhone under Rs.40000" + Keys.ENTER); // search iPhones
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority = 2)
	public static void getData() throws IOException {
		ArrayList<Details> info = new ArrayList<Details>(); // Create ArrayList of class Details
		List<WebElement> data = driver.findElements(By.className("_3wU53n")); // Get iphone's Name and storage
		List<WebElement> price = driver.findElements(By.xpath("//div[starts-with(@class,'_1vC4OE')]")); // Get iphone's
																										// price
		List<WebElement> ratings = driver.findElements(By.className("hGSR34")); // Get iphone's ratings

		if (data.size() != 0) {
			for (int j = 0; j < data.size(); j++) {

				WebElement element = price.get(j);
				String amount = element.getText();
				int amont = Integer.parseInt(amount.replaceAll("[^0-9]", "").toString());
				if (amont < 40000) {
					String Data = data.get(j).getText();
					String Price = price.get(j).getText();
					String Ratings = ratings.get(j).getText();
					Price = Price.replaceAll("[^0-9]", "");
					Integer i = Integer.valueOf(Price);
					Details d = new Details(Data, i, Ratings);
					info.add(d); // Add the data in Array

				}
			}
		}
		Comparator<Details> comparator = new Comparator<Details>() { // Creating custom comparator
			public int compare(Details o1, Details o2) {
				return (o1.getPrice() - o2.getPrice());

			}
		};

		// sort by price using a custom comparator
		Collections.sort(info, comparator);
		FileWriter fw = new FileWriter("filepath");
		for (Details d : info) {

			fw.write(d.getName() + ", " + d.getPrice() + "," + d.getRatings() + System.lineSeparator()); // Write data
																											// into csv
																											// file
			System.out.println("Details: " + d.getName() + ", " + d.getPrice() + "," + d.getRatings());
		}
		fw.close();

	}

	public int compareTo(Details d) { // Comparing Details
		return (d.getPrice() - d.getPrice());

	}

	@AfterClass
	public static void quitBrowser() {
		driver.close(); // Close browser
	}
}
