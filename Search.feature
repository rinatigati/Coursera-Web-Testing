Feature: As a user
	I want the ability to search Coursera for topics and courses I am interested in
	So that I can view their description pages and potentially choose to enroll in them
	
	Scenario: I search Coursera for a particular subject (ruby) and am shown courses on Ruby
	Given I am logged into Coursera
	And I am interested in classes on Ruby
	When I search for 'Ruby' in the search bar
	Then I am displayed a search result page with courses about Ruby
	
	Scenario: I search Coursera for courses by a specific professor
	Given I am logged into Coursera
	And I am interested in CS courses taught by 'John Hart'
	When I search for 'John Hart' in the Cousera search bar
	Then I should be shown courses that are taught by that professor on Coursera
	
	Scenario: I search for courses taught in German
	Given I am logged into Coursera
	And I am interested in courses taught in the German language
	And I know the University of Zurich has partnered with Coursera
	When I select the 'German' category from under languages
	Then I should be shown the course Informatik für Ökonomen which is offered by University of Zurich
	
	Scenario: I search for something that is clearly not a course
	Given I am logged into Coursera
	And I search for something completely unrelated to classes
	When I search for 'potatoes' in the search bar
	Then I should be told that no results were found.
	
	Scenario: I know the course name and I search for that particular course
	Given I am logged into Coursera
	And I want to find 'Introduction to Programming with MATLAB'
	When I search for it in the search bar
	Then I should have a link to that course page
	
	Scenario: I search for a course by category/subject
	Given I am logged into Coursera
	And I want to find courses for physics
	When I select the physics category
	Then I should be shown courses on Quantum Physics
	
	Scenario: I search for courses based on the university
	Given I am logged into Coursera
	And I want to find courses offered by the University of Pittsburgh
	When I search for 'University of Pittsburgh' in the search bar
	Then I should be shown courses that the University offers on Coursera