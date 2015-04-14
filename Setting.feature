Feature: As a user
	I want to be able to add, edit, and update information contained inside of my user profile/settings
	So that I can have correct personal information in my Coursera account.
		
		Scenario: Navigate to the user profile form and change the location
			Given I am logged to Coursera
			And I am on the profile page
			When I my change my location to Pittsburgh, PA, United States 
			Then my profile should reflect that change 

		Scenario: Navigate to the user profile form and change "About Me" information
			Given I am logged to Coursera
			And I am on the profile page
			When I my add text to the "About Me" information
			Then my profile should reflect that change 

		Scenario: Navigate to the user setting form and change the time zone
			Given I am logged to Coursera
			And I am on the setting page
			When I my change the text in the "About Me" field
			Then my user setting information should reflect that change 

		Scenario: Navigate to the user setting form and change the name on my account
			Given I am logged to Coursera
			And I am on the setting page
			When I my change the name on the account to "Coursear-Test"
			Then my account name should be "Coursera-Test" in my user settings page 