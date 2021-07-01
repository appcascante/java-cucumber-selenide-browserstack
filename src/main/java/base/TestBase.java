package base;

import static com.codeborne.selenide.Selenide.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import com.codeborne.selenide.ex.*;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selectors;

import static com.codeborne.selenide.Condition.*;

public class TestBase extends Selectors {

	public void initializeBrowser() {

		Configuration.driverManagerEnabled = true;
		Configuration.remote = "http://taeupwork_qSwAs8:JQCA46mFEp1A6bjRJxQz@hub.browserstack.com/wd/hub";
		Configuration.browserCapabilities = setDc();
		Configuration.startMaximized = true;
		open("https://test.app.upet.co/");
	}
	
	public DesiredCapabilities setDc() {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("os", "OS X");
		dc.setCapability("os_version", "Big Sur");
		dc.setCapability("browser", "Safari");
		dc.setCapability("name", "selenide-cucumber-test");
		
		return dc;
	}

	public SelenideElement getElement(String locator) {
		try {
			if (locator.startsWith("id=")) {

				locator = locator.substring(3);
				return $(byId(locator));
			} else if (locator.startsWith("xpath=")) {

				locator = locator.substring(6);
				return $(byXpath(locator));
			} else if (locator.startsWith("css=")) {

				locator = locator.substring(4);
				return $(locator);
			} else if (locator.startsWith("name=")) {

				locator = locator.substring(5);
				return $(byName(locator));
			} else if (locator.startsWith("tag=")) {

				locator = locator.substring(4);
				return $(byTagName(locator));
			} else if (locator.startsWith("class=")) {

				locator = locator.substring(6);
				return $(byClassName(locator));
			} else if (locator.startsWith("text=")) {

				locator = locator.substring(5);
				return $(byText(locator));
			} else {

				return $(byXpath(locator));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void type(String locator, String value) {
		try {

			waitFor(locator);
			

			if (value.startsWith("$")) {
				
				value = stringGenerator(value);
				System.out.println("TO BE TYPED ON " + locator + " - "+value);
				getElement(locator).setValue(value);
				
			} else {
				
				getElement(locator).setValue(value);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void click(String locator) {
		try {
			waitFor(locator);

			System.out.println("TO BE CLICKED " + locator);

			getElement(locator).click(ClickOptions.usingJavaScript());
		} catch (ElementIsNotClickableException e) {
			clickViaJavascript(locator);
		}
	}

	public void clickViaJavascript(String locator) {
		try {
			System.out.println("TO BE CLICKED via javascript " + locator);
			Configuration.clickViaJs = true;
			getElement(locator).click();
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("ELEMENT NOT ON SCREEN. - " + locator);
		}
	}

	public void waitFor(String locator) {
		try {
			Configuration.timeout = 20000;
			getElement(locator).shouldBe(visible);
			getElement(locator).shouldBe(enabled);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String stringGenerator(String value) {
		String type = value.substring(1);
		String toBeReplaced = new String(); 
		String append = new String();
		
		List rules = new ArrayList<>();
		int count = 0;

		switch(type) {
		
		case "EMAIL":
			count = 8;
			rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
					new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1));
			toBeReplaced = "$EMAIL";
			append = "@gmail.com";
			break;
			
		case "PASSWORD":
			count = 9;
			rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
					new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1),new CharacterRule(EnglishCharacterData.Special,1));
			toBeReplaced = "$PASSWORD";
			break;
			
		case "FNAME":
			count = 7;
			rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
					new CharacterRule(EnglishCharacterData.LowerCase, 1));
			toBeReplaced = "$FNAME";
			break;
			
		case "LNAME":
			count = 7;
			rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
					new CharacterRule(EnglishCharacterData.LowerCase, 1));
			toBeReplaced = "$LNAME";
			break;
		
		}
		
//		System.out.println("generated length: "+count);
		PasswordGenerator generator = new PasswordGenerator();
		String generated = generator.generatePassword(count, rules);
		value = generated+append;
		return value;
	}
	
	public void uploadFile(String target, String source) {
		System.out.println(System.getProperty("user.dir")+"\\"+source);
		File file = getElement(target).uploadFile(new File(System.getProperty("user.dir")+"\\"+source));
		
		
		
//		getElement("xpath=//div[@role='dialog']").shouldBe(visible);
	}
	
	

}
