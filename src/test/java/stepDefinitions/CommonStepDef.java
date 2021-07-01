package stepDefinitions;



import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.GenericSteps;

public class CommonStepDef {
	
	GenericSteps generic = new GenericSteps();

	@Given("User launches Upet website")
	public void user_launches_website() {
		generic.initializeBrowser();
	}
	
	@When("User clicks the sign up button")
	public void user_click_sign_up_button(DataTable data) {
		generic.performGenericTests(data);
	}
	
	@When("User fills out form")
	public void user_fill_out_form(DataTable data) {
		generic.performGenericTests(data);
	}
	
	@When("User clicks create account")
	public void user_click_create_account(DataTable data) {
		generic.performGenericTests(data);
	}
	
	@When("User clicks user profile")
	public void user_clicks_user_profile(DataTable data) {
		generic.performGenericTests(data);
	}
	
	@When("User uploads picture")
	public void user_clicks_upload_picture(DataTable data) {
		generic.performGenericTests(data);
	}
	
	@Then("User validates that image is successfully uploaded") 
	public void user_validates_image_is_uploaded(DataTable data){
		generic.performGenericTests(data);
	}
	
}
