@ApplyJobs
Feature: Upload resume in naukri.com

  Scenario: Upload Resume
    Given Verify title is exactly "Jobs - Recruitment - Job Search - Employment -Job Vacancies - Naukri.com"
    And Get parent window handle
    And No other windows are open
    And User clicks on login page
    When User enter username as "arpankushwahapro@gmail.com"
    And User enter password as "Naukri2012."
    And User click on login button
    Then Verify title is exactly "Home | Mynaukri"
    Given User clicks on View All in New Jobs in My Job Alerts
    Then Verify title is exactly "Preview Job Alert - Naukri.com"
    And Get parent window handle
    And User selects checkboxes and Apply Jobs
