package tests;

import base.BaseTest;
import pages.JobApplyPage;
import org.testng.annotations.Test;

public class JobApply extends BaseTest {



    @Test
    public void searchAndApplyJobsOnNaukri()
    {
        JobApplyPage jobApply = new JobApplyPage(driver);
        LoginTest login = new LoginTest();
        login.testValidLogin();
        jobApply.clickOnSearchButton();
        jobApply.enterSearchingDesignationAndExperience("SDET engineer", "3 years");
        jobApply.applyJobsOnNaukri();
    }
}
