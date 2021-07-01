package steps;

import java.util.Base64;
import java.util.List;

import static com.codeborne.selenide.Condition.*;

import base.TestBase;
import io.cucumber.datatable.DataTable;

public class GenericSteps extends TestBase{

	public void performGenericTests(DataTable dataTable) {
List<List<String>> perform = dataTable.asLists();
		
		for(List<String> s : perform) {
			switch (s.get(0)) {
			
			case "type":
				type(s.get(2),s.get(1));
				break;
				
			case "click":
				click(s.get(2));
				break;
				
			case "upload":
				uploadFile(s.get(2),s.get(1));
				break;
				
			case "validate":
				validateImageUpload(s.get(1),s.get(2));
				break;
			
			}
		}
	}
	
	public void validateImageUpload(String img, String locator) {
		
		System.out.println("VALIDATING...");
		getElement("//div[@role='dialog']").shouldNotBe(visible);
		
		getElement(locator).shouldBe(visible);
		getElement(locator).shouldHave(image);
	}
	
	
	
}
