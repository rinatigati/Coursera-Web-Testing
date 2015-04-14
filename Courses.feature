Feature: As a user
	I want the ability to add, drop, view courses that I choose on Coursera
	So that I can adequately manage the courses I am taking and drop the ones I don't like
	
	Scenario: I add a course to my courses
		Given I am logged into Coursera
		And I want to add Social Psychology
		When I enroll in Social Psychology
		Then My courses dashboard will have Social Psychology as one of the courses
		
	Scenario: I want to delete a course from my courses
		Given I am logged into Coursera
		And I already have added Social Psychology
		When I leave the course
		Then My courses dashboard should say that I haven't signed up for any courses yet
		
	Scenario: I want to continue with the course I have added
		Given I am logged into Coursera
		And I have already added Social Psychology
		When I click to continue the course
		Then I should be redirected to its page with the option for me to take a quiz
		
	Scenario: I want to check when a class I signed up for startw
		Given I am logged into Coursera
		And I have already joined 'Enhance Your Career and Employability Skills'
		When I check it in My Courses
		Then It should tell me when the course starts
		
	Scenario: I am enrolled in a class and I want to see how much the certificate would cost
		Given I am logged into Coursera
		And I have already joined 'Programming for Everybody (Python)'
		When I click on the option to earn a verified Certificate
		Then It should direct me to the page with the price for that certificate