Feature: As a user
	I want the ability to log in and log out of Coursera
	So that I can search the available courses, take the lessons, and then log out when I'm done.
	
	Scenario: Logging into Coursera with the correct user name and correct password
		Given Correct user name (courseratestcs1699@gmail.com)
		And Correct password (15spring)
		When I attempt to log in
		Then I will be informed that I have tried to log in with either wrong email or wrong password
	
	Scenario: Logging into Coursera with the correct user name but the incorrect password
		Given Correct user name (courseratestcs1699@gmail.com)
		And Incorrect password (spring15)
		When I attempt to log in
		Then I should stay on the same page with the same email and password box with a message saying it was not susccessful. 
		
	Scenario: Logging into Coursera with the incorrect user name but the correct password
		Given Incorrect user name (amazontestcs1699@gmail.com)
		And Correct password (15spring)
		When I try to log in
		Then I should stay on the same log in page with the same email and password inputs. Message should display.
		
	Scenario: Logging out after I logged in successfully
		Given I was able to log in with the correct user name (courseratestcs1699@gmail.com)
		And the correct password (15spring).
		When I click the the sign out button under the Coursera drop down menu
		Then I should be back on the main page and have the option to sign up or log in again