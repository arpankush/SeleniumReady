@UploadResume
Feature: Upload resume in naukri.com

  Scenario: Upload Resume
    Given Verify title is exactly "Jobs - Recruitment - Job Search - Employment -Job Vacancies - Naukri.com"
    And Get parent window handle
    And No other windows are open
    And User clicks on login page
    When User enter username as "arpankushwahapro@gmail.com"
    And User enter password as "WrongPassword"
    And User click on login button
    Then Verify title is exactly "Home | Mynaukri"
    Given User clicks on "Edit Profile" from My Naukri
    And User clicks on "Attach Resume" in Quick Links
    And User upload resume after clicking Update Resume button
