package com.browse.testcase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Spanish {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		String url;
		String allText = "";
		List<String> textToTranslate = new ArrayList<String>();
		List<String> translatedText = new ArrayList<String>();
		//driver = new ChromeDriver();
		driver=new FirefoxDriver();
		String charSplit[];
		// open url
		driver.get("https://elpais.com/");
		Thread.sleep(1000);
		//wait for page to load for 10 seconds
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		//maximize the window
		driver.manage().window().maximize();
		Thread.sleep(1000);
		// Accept the popup
		driver.findElement(By.xpath("//button[@id=\"didomi-notice-agree-button\"]")).click();
		//wait for 10 seonds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// navigate on opinion button
		driver.findElement(By.xpath("//div[@id='csw']//a[contains(text(),'Opinión')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// fetch first 5 articles
		List<WebElement> title = driver.findElements(By.xpath("//div//header[contains(@class,'c_h')]//h2/a[1]"));
		List<WebElement> content = driver
				.findElements(By.xpath("//div//header[contains(@class,'c_h')]//following-sibling::p"));
		for (int i = 0; i < 5; i++) {
			//get text of the element and store n a variable
			String fetchedText = title.get(i).getText();
			System.out.println(" Article title at " + (i + 1) + " is " + fetchedText);
			textToTranslate.add(i, fetchedText);
			String paragraph = content.get(i).getText();
			System.out.println(" Article content at " + (i + 1) + " is " + paragraph);
			System.out.println();
		}
		// test case 2
		Thread.sleep(10000);
		//navigate to google translate
		driver.navigate().to("https://translate.google.com");
		//wait for page to load
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		for (int j = 0; j < textToTranslate.size(); j++) {
			System.out.println("Text to translate at position " + (j + 1) +" is " + textToTranslate.get(j));
			// driver.findElement(By.xpath("//div[@class='n4sEPd']/c-wiz")).click();
			driver.findElement(By.xpath("//div[@class='n4sEPd']//div[@class='QFw9Te']/textarea")).click();
			// driver.findElement(By.xpath("//div[@class='n4sEPd']/c-wiz")).sendKeys(textToTranslate.get(j));
			driver.findElement(By.xpath("//div[@class='n4sEPd']//div[@class='QFw9Te']/textarea"))
					.sendKeys(textToTranslate.get(j));
			// driver.findElement(By.xpath("//div[@class='n4sEPd']/c-wiz")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			// String convertedText =
			// driver.findElement(By.xpath("//div[@class='OPPzxe']/c-wiz")).getText();
			String convertedText = driver.findElement(By.xpath("//div[@class='lRu31']//span[@class='ryNqvb']"))
					.getText();
			translatedText.add(j, convertedText);
			allText = allText + convertedText + " ";
			/*
			 * System.out.print( "Text to translate is " + textToTranslate.get(j) +
			 * " and translated text is " + convertedText);
			 */
			System.out.println("Translated text is " + convertedText);
			/*
			 * driver.findElement(By.xpath("//div[@class='lRu31']//span[@class='ryNqvb']")).
			 * click();
			 * driver.findElement(By.xpath("//div[@class='lRu31']//span[@class='ryNqvb']")).
			 * clear();
			 */
			driver.findElement(By.xpath("//div[@class='n4sEPd']//div[@class='QFw9Te']/textarea")).clear();
			Thread.sleep(2000);

		}
		System.out.println("Entire text is " + allText);
		charSplit = allText.split(" ");

		/*
		 * System.out.println("The size of translatedText size is " +
		 * translatedText.size()); for (int k = 1; k < translatedText.size(); k++) {
		 * System.out.println(" Translated text at position " + k + " is " +
		 * translatedText.get(k)); }
		 * 
		 * Iterator it = translatedText.iterator(); while (it.hasNext()) {
		 * System.out.println(it.next() + " "); }
		 */

		for (int a = 0; a < charSplit.length; a++) {
			System.out.println("Char at position " + a + " is " + charSplit[a]);
		}

		/*
		 * for (int b = 0; b < charSplit.length; b++) { boolean alreadyCounted = false;
		 * for (int c = 0; c < b; c++) { if (charSplit[b] == charSplit[c]) {
		 * alreadyCounted = true; break; } }
		 * 
		 * if (!alreadyCounted) { int count = 0; for (int d = 0; d < charSplit.length;
		 * d++) { if (charSplit[b].equalsIgnoreCase(charSplit[d])) { count++; } }
		 * System.out.println(charSplit[b] + " - Repeated " + count + " times"); }
		 * 
		 * }
		 */
		
		HashMap<String, Integer> wordCountMap  = new HashMap<String, Integer>();
		for (String word : charSplit) {
            // If the word is already in the map, increment its count, otherwise add it with count 1
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
		
		/*
		 * for (String word : wordCountMap.keySet()) { System.out.println(word + ": " +
		 * wordCountMap.get(word)); }
		 */
		 boolean found = false;
	        for (String word : wordCountMap.keySet()) {
	            if ((wordCountMap.get(word) == 2) || (wordCountMap.get(word) > 2)) {
	                System.out.println(word);
	                found = true;
	            }
	        }

	        // If no words are found that occur exactly twice
	        if (!found) {
	            System.out.println("No words found that occur exactly twice.");
	        }
		
		
		driver.navigate().back();
		Thread.sleep(2000);
		driver.quit();
	}

}
