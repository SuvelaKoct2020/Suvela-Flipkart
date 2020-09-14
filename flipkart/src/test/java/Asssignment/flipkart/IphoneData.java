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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class IphoneData {

	private static WebDriver driver;
	ArrayList<Details> info = new ArrayList<Details>();

	@BeforeMethod
	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

	}
	@Test ( priority=1)
	public static void getResult() {
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.className("_29YdH8")).click();
		driver.findElement(By.name("q")).sendKeys("iPhone under Rs.40000" + Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	@Test ( priority=2)
	public static void getData() throws IOException {
		ArrayList<Details> info = new ArrayList<Details>();
		List<String> strings = new ArrayList<String>(info.size());
		List<WebElement> data = driver.findElements(By.className("_3wU53n"));
		List<WebElement> price = driver.findElements(By.xpath("//div[starts-with(@class,'_1vC4OE')]"));
		List<WebElement> ratings = driver.findElements(By.className("hGSR34"));

		if (data.size() != 0) {
			for (int j = 0; j < data.size(); j++) {

				WebElement element = price.get(j);
				String amount = element.getText();
				int amont = Integer.parseInt(amount.replaceAll("[^0-9]", "").toString());
				if (amont <= 40000) {
					String Data = data.get(j).getText();
					String Price = price.get(j).getText();
					String Ratings = ratings.get(j).getText();

					for (Details object : info) {
						strings.add(object != null ? object.toString() : null);
					}

					strings.add(Data + "," + Price + "," + Ratings);
					Price = Price.replaceAll("[^0-9]", "");
					Integer i = Integer.valueOf(Price);
					Details d = new Details(Data, i, Ratings);
					info.add(d);

				}
			}
			System.out.println(info);
		}
		Comparator<Details> comparator = new Comparator<Details>() {
			public int compare(Details o1, Details o2) {
				return (o1.getPrice() - o2.getPrice());

			}
		};

		// sort by price using a custom comparator
		Collections.sort(info, comparator);
		FileWriter fw = new FileWriter("E:\\file.csv");
		for (Details d : info) {

			fw.write(d.getName() + ", " + d.getPrice() + "," + d.getRatings() + System.lineSeparator());
			System.out.println("Details: " + d.getName() + ", " + d.getPrice() + "," + d.getRatings());
		}
		fw.close();

	}

	public int compareTo(Details d) {
		return (d.getPrice() - d.getPrice());

	}
}
