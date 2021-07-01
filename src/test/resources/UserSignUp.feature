@tag
Feature: User Sign Up
  I want to validate that a user can successfully sign up and upload a profile picture

  @tag1
  Scenario Outline: Title of your scenario outline
    Given User launches Upet website
    When User clicks the sign up button
      | click | createNewAccount | xpath=//span[contains(text(),'NEWBIE? CREATE ACCOUNT')] |
    And User fills out form
      | type | <firstname> | xpath=//input[@name='name']     |
      | type | <lastname>  | xpath=//input[@name='lastName'] |
      | type | <email>     | xpath=//input[@name='email']    |
      | type | <password>  | xpath=//input[@name='password'] |
    And User clicks create account
      | click | createAccountbutton | xpath=//span[contains(text(),'Create Account')] |
    And User clicks user profile
      | click | userProfile | css=#currentUser |
    And User uploads picture
      | upload | heic-image.HEIC | css=#uploadPhoto                      |
      | click  | saveButton      | xpath=//span[contains(text(),'Save')] |
    Then User validates that image is successfully uploaded
      | validate | heic-image.HEIC | xpath=//img[@alt='profile image'] |

    Examples: 
      | email  | password  | firstname | lastname |
      | $EMAIL | $PASSWORD | $FNAME    | $LNAME   |
