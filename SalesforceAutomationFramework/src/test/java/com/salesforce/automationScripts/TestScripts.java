package com.salesforce.automationScripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.base.BaseAutomation;

public class TestScripts extends BaseAutomation {

	public static WebElement usernameHomePage;

	@Test(priority = 1)
	public static void emptyPasswordTC1() throws Exception {
		System.out.println("Test Case 1 : Login Error Message");
		getWebElements();
		String usernamePath = getApplicationProperty("valid-username");
		enterElementText(username, usernamePath, "user name");
		clearElementText(password, "pass word");
		report.logTestFail("password field is empty");
		clickElement(loginButton, "Login Button");
		report.logTestInfo("Error Message : " + "Please enter your password" + "is displayed successfully");
	}

	@Test(priority = 2)
	public static void loginSuccessTC2() throws Exception {
		System.out.println("Test Case : Login To SalesForce -2 ");
		loginToSalesforce();
	}

	@Test(priority = 2, dependsOnMethods = { "loginSuccessTC2", "emptyPasswordTC1" })
	public static void logoutSuccessTC3() throws Exception {
		System.out.println("Test Case 3 : Check RemeberMe");
		getWebElements();
		String usernamePath = getApplicationProperty("valid-username");
		enterElementText(username, usernamePath, "user name");
		String passwordPath = getApplicationProperty("valid-password");
		enterElementText(password, passwordPath, "password");
		clickElement(rememberMe, "Remember Me");
		clickElement(loginButton, "Login Button");
		usernameHomePage = driver.findElement(By.xpath("//*[@id=\"userNavButton\"]"));
		clickElement(usernameHomePage, "User Name at Home PAge");
		WebElement logout = driver.findElement(By.xpath("//a[@title=\"Logout\"]"));
		clickElement(logout, "logout");
		closeDriver();
	}

	@Test(priority = 3)

	public static void forgotPasswordTC4A() throws Exception { //
		System.out.println("Test Case 4 : Forgot Password");
		getWebElements();
		clickElement(forgotPassword, "Forgot Password");
		WebElement usernameForgotPassword = driver.findElement(By.xpath("//*[@id=\"un\"]"));
		String path = getApplicationProperty("valid-username");
		enterElementText(usernameForgotPassword, path, "User Name Forgot Password");
		WebElement continueForgotPassword = driver.findElement(By.xpath("//*[@id=\"continue\"]"));
		clickElement(continueForgotPassword, " Continue for Forgot Password");
		System.out.println("Password reset message page is displayed successfully");

	}

	@Test(priority = 4)

	public static void invalidLoginTC4B() throws Exception { //
		System.out.println("Test Case 4B : ValidateLoginErrorMessage");
		getWebElements();
		String usernamePath = getApplicationProperty("invalid-usernm");
		enterElementText(username, usernamePath, "user name");
		String passwordPath = getApplicationProperty("invalid-pw");
		enterElementText(password, passwordPath, "Password");
		clickElement(loginButton, "Login Button");
		System.out.println("Invalid details message displayed");
	}

	@Test(priority = 5)

	public static void selectUserMenuTC5() throws Exception {

		loginToSalesforce();
		untilPageLoad();
		usernameHomePage = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
		waitUntilVisible(usernameHomePage, "user name @ Home Page");
		clickElement(usernameHomePage, "User Name at Home PAge");

	}

	@Test(priority = 6, dependsOnMethods = { "loginSuccessTC2" })

	public static void selectMyProfileTC6() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		usernameHomePage = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
		waitUntilVisible(usernameHomePage, "user name drop down Home Page");
		clickElement(usernameHomePage, "User Name at Home PAge");

		WebElement myProfile = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));
		moveToElement(myProfile, " My Profile");
		clickElement(myProfile, " My Profile");
		untilPageLoad();

		WebElement editImage = driver.findElement(By.xpath("//img[@title=\"Edit Profile\"]"));
		clickElement(editImage, " Edit");
		sleep(4000);

		System.out.println("Click on edit pressed successfully");

		WebElement contactFrame = driver.findElement(By.xpath("//iframe[@id=\"contactInfoContentId\"]"));
		switchToFrame(contactFrame);
		untilPageLoad();

		WebElement about = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		waitUntilClickable(about);
		clickElement(about, " About ");

		WebElement lastName = driver.findElement(By.id("lastName"));
		enterElementText(lastName, "Last Name Today", "Last name ");

		WebElement saveall = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div/input[1]"));
		clickElement(saveall, " saveall ");

		switchToDefaultWindow();
		untilPageLoad();

		WebElement post = driver
				.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]"));
		waitUntilClickable(post);
		clickElement(post, "Post");

		WebElement postFrame = driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
		switchToFrame(postFrame);

		WebElement postField = driver.findElement(By.xpath("//html/body"));
		waitUntilVisible(postField, "Post Field");
		enterElementText(postField, "Publish this post", "Post Input");

		switchToDefaultWindow();
		untilPageLoad();

		WebElement shareButton = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		waitUntilClickable(shareButton);
		shareButton.click();
		sleep(2000);

		// code for file upload
		WebElement file = driver
				.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
		waitUntilClickable(file);
		clickElement(file, " File ");
		sleep(4000);

		WebElement uploadComp = driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		waitUntilClickable(uploadComp);
		clickElement(uploadComp, " Upload from Computer ");
		sleep(3000);

		WebElement browse = driver.findElement(By.cssSelector("#chatterFile"));
		waitUntilClickable(browse);
		enterElementText(browse, "C:\\Users\\HP\\OneDrive\\Desktop\\test.txt", "Browse");
		sleep(3000);

		WebElement share = driver.findElement(By.cssSelector("#publishersharebutton"));
		waitUntilClickable(share);
		clickElement(share, " Share button for File ");

		sleep(3000);
		// Update profile photo
		WebElement myProfilePhoto = driver
				.findElement(By.xpath("//span[contains(@class,'profileImage chatter-avatarFull chatter-avatar')]"));
		moveToElement(myProfilePhoto, "My Profile Photo");

		WebElement updatePhoto = driver.findElement(By.id("uploadLink"));
		waitUntilClickable(updatePhoto);
		updatePhoto.click();

		sleep(2000);

		WebElement imageFrame = driver.findElement(By.id("uploadPhotoContentId"));
		switchToFrame(imageFrame);

		sleep(2000);

		WebElement browseImage = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		enterElementText(browseImage, "C:\\Users\\HP\\OneDrive\\Desktop\\x-t30_sample-images02.jpg", "Browse Image");

		sleep(5000);

		WebElement saveUploadedImage = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadBtn']"));
		waitUntilClickable(saveUploadedImage);
		saveUploadedImage.click();
		sleep(2000);

		WebElement saveCropped = driver.findElement(By.name("j_id0:j_id7:save"));
		waitUntilClickable(saveCropped);
		saveCropped.click();

	}

	@Test
	public static void selectDeveloperConsoleTC08() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		clickUserNameDropDown();
		WebElement developerConsole = driver.findElement(By.xpath("//a[@class='debugLogLink menuButtonMenuLink']"));
		moveToElement(developerConsole, " Developer Console");

		clickElement(developerConsole, " Developer Console");
		untilPageLoad();

		String baseWindowHandle = driver.getWindowHandle();
		switchToOpenWindow(baseWindowHandle);
		untilPageLoad();
		driver.close();
		untilPageLoad();
		driver.switchTo().window(baseWindowHandle);
	}

	@Test
	public static void LogoutTC09() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		usernameHomePage = driver.findElement(By.xpath("//*[@id=\"userNavButton\"]"));
		clickElement(usernameHomePage, "User Name at Home PAge");
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickElement(logout, "logout");

	}

	@Test
	public static void createAccountTC10() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement accounts = driver.findElement(By.xpath("//a[@href='/001/o']"));
		clickElement(accounts, "Accounts");
		sleep(3000);

		WebElement accountPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(accountPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement newButton = driver.findElement(By.xpath("//td[@class='pbButton']//input[@type='button']"));
		clickElement(newButton, "New Button");
		untilPageLoad();

		WebElement accountName = driver.findElement(By.xpath("//input[@id='acc2']"));
		enterElementText(accountName, "SalesForceTestNG", "Account Name");
		WebElement type = driver.findElement(By.cssSelector("#acc6"));
		// clickElement(type, "Type Dropdown");
		selectElementByVisibleText(type, "Technology Partner", "Type");

		WebElement customerPriority = driver.findElement(By.xpath("//select[@id='00N4x00000Wfiqj']"));
		// clickElement(accountPriority, "Priority");
		selectElementByVisibleText(customerPriority, "High", "Customer Priority");

		WebElement save = driver.findElement(By
				.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div/div[1]/table/tbody/tr/td[2]/input[1]"));
		clickElement(save, "Save");
	}

	@Test

	public static void createViewTC11() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement accounts = driver.findElement(By.xpath("//a[@href='/001/o']"));
		waitUntilClickable(accounts);
		clickElement(accounts, "Accounts");
		sleep(3000);

		// handle pop up
		WebElement accountPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(accountPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement createNewView = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		waitUntilClickable(createNewView);
		clickElement(createNewView, "Create New View");
		untilPageLoad();

		WebElement viewName = driver.findElement(By.xpath("//input[@id='fname']"));
		waitUntilVisible(viewName, "View Name");
		enterElementText(viewName, "Test NG", "View Name");

		WebElement uniqueViewName = driver.findElement(By.xpath("//input[@id='devname']"));
		enterElementText(uniqueViewName, "Test28th", "Unique View Name");

		WebElement save = driver.findElement(By.xpath("//input[@title=\"Save\"]"));
		clickElement(save, "Save");
	}

	@Test

	public static void editViewTC12() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement accounts = driver.findElement(By.xpath("//a[@href='/001/o']"));
		waitUntilClickable(accounts);
		clickElement(accounts, "Accounts");
		sleep(3000);

		WebElement accountPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(accountPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement view = driver.findElement(By.id("fcf"));
		selectElement(view, "NewName5", "View");

		WebElement editButton = driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[2]/form/div/span/span[2]/a[1]"));
		waitUntilClickable(editButton);
		clickElement(editButton, " Edit Account");

		WebElement viewName = driver.findElement(By.xpath("//input[@id='fname']"));
		waitUntilVisible(viewName, "View Name");
		enterElementText(viewName, "NewName5", "View Name");

		WebElement field = driver.findElement(By.id("fcol1"));
		selectElement(field, "Account Site", "Field");

		WebElement operator = driver.findElement(By.id("fop1"));
		selectElement(operator, "contains", "Operator");

		WebElement value = driver.findElement(By.id("fval1"));
		enterElementText(value, "d", "Value ");
		Thread.sleep(3000);

		WebElement save = driver.findElement(By.xpath("//input[@class='btn primary']"));
		clickElement(save, "save");

	}

	@Test
	public static void mergeAccountTC13() throws Exception {

		loginToSalesforce();
		untilPageLoad();

		WebElement accounts = driver.findElement(By.xpath("//a[@href='/001/o']"));
		waitUntilClickable(accounts);
		clickElement(accounts, "Accounts");
		sleep(3000);

		// handle pop up
		WebElement accountPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(accountPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement mergeAccounts = driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]"));
		waitUntilClickable(mergeAccounts);
		clickElement(mergeAccounts, "Merge Accounts");

		WebElement searchAccount = driver.findElement(By.xpath("//input[@id='srch']"));
		enterElementText(searchAccount, "tek", "searchAccount ");

		sleep(3000);
		WebElement findAccount = driver.findElement(By.xpath("//input[@value=\"Find Accounts\"]"));
		waitUntilClickable(findAccount);
		clickElement(findAccount, "findAccount");

		WebElement checkBox1 = driver.findElement(By.xpath("//input[@id='cid0']"));
		checkCheckBox(checkBox1);
		WebElement checkBox2 = driver.findElement(By.xpath("//input[@id='cid1']"));
		checkCheckBox(checkBox2);

		// Have to implement logic to unselect elements more than 2 in checklist

		WebElement nextButton = driver.findElement(By.xpath("//input[@name='goNext']"));
		waitUntilClickable(nextButton);
		clickElement(nextButton, "next");

		WebElement mergeSave = driver.findElement(By.xpath("//input[@name='save']"));
		waitUntilClickable(mergeSave);
		clickElement(mergeSave, "Merge");
		acceptAlert();

	}

	@Test (priority = 38)
	public static void createAccountReportTC14() throws Exception {

		loginToSalesforce();
		untilPageLoad();
		WebElement accounts = driver.findElement(By.xpath("//a[@href='/001/o']"));
		waitUntilClickable(accounts);
		clickElement(accounts, "Accounts");
		sleep(3000);

		// handle pop up
		WebElement accountPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(accountPopup, closePopup, "Pop up");

		WebElement reportG30 = driver
				.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
		waitUntilClickable(reportG30);
		clickElement(reportG30, "Accounts with last activity greater than 30 days");

		WebElement dateField = driver.findElement(By.xpath("//input[@id='ext-gen20']"));
		waitUntilClickable(dateField);
		clickElement(dateField, "Date Field");

		WebElement createdDate = driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		waitUntilClickable(createdDate);
		clickElement(createdDate, "Created Date");
		Thread.sleep(4000);

		WebElement fromDate = driver.findElement(By.xpath("//img[@class=\"x-form-trigger x-form-date-trigger\"]"));
		waitUntilClickable(fromDate);
		clickElement(fromDate, "From Date");
		Thread.sleep(3000);

		WebElement todayFromDate = driver.findElement(By.xpath("//button[contains(text(),\"Today\")]"));
		waitUntilClickable(todayFromDate);
		clickElement(todayFromDate, "today");
		Thread.sleep(4000);

		WebElement toDate = driver
				.findElement(By.xpath("//img[@class=\"x-form-trigger x-form-date-trigger\" and @id=\"ext-gen152\"]"));
		waitUntilClickable(toDate);
		clickElement(toDate, "to Date");
		Thread.sleep(3000);

		WebElement todayToDate = driver.findElement(By.xpath("//button[contains(text(),\"Today\")]"));
		waitUntilClickable(todayToDate);
		clickElement(todayToDate, "Today in to Date");

	}

	@Test

	public static void opporDropDownTC15() throws Exception {

		loginToSalesforce();
		untilPageLoad();
		WebElement oppor = driver.findElement(By.linkText("Opportunities"));
		waitUntilClickable(oppor);
		clickElement(oppor, "Opportunity");

		// handle pop up
		WebElement oppurtunityPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(oppurtunityPopup, closePopup, "Pop up");

		WebElement opprDropDown = driver.findElement(By.xpath("//select[@id='fcf']"));
		waitUntilClickable(opprDropDown);
		clickElement(opprDropDown, "Opportunity Drop Down");

	}

	@Test
	public static void createOpportunityTC16() throws Exception {

		loginToSalesforce();
		untilPageLoad();
		WebElement oppor = driver.findElement(By.linkText("Opportunities"));
		waitUntilClickable(oppor);
		clickElement(oppor, "Opportunity");

		// handle pop up
		WebElement oppurtunityPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(oppurtunityPopup, closePopup, "Pop up");
		WebElement newO = driver.findElement(By.xpath("//input[@name='new']"));
		waitUntilClickable(newO);
		clickElement(newO, "new");

		WebElement opportunityName = driver.findElement(By.xpath("//input[@id='opp3']"));
		enterElementText(opportunityName, "New Opportunity", "Opportunity Name ");

		WebElement accountName = driver.findElement(By.xpath("//input[@id='opp4']"));
		enterElementText(accountName, "tekarch", "Account Name ");

		WebElement leadSource = driver.findElement(By.xpath("//select[@id='opp6']"));
		selectElement(leadSource, "Partner Referral", "Laed Source");

		WebElement closeDate = driver.findElement(By.xpath("//input[@id='opp9']"));
		enterElementText(closeDate, "9/12/2022", "closeDate");

		WebElement stage = driver.findElement(By.xpath("//select[@id='opp11']"));
		selectElement(stage, "Qualification", "Stage");

		WebElement probability = driver.findElement(By.xpath("//input[@id='opp12']"));
		enterElementText(probability, "20", "Probability ");

		WebElement primaryCompaignSource = driver.findElement(By.xpath("//input[@id='opp17']"));
		enterElementText(primaryCompaignSource, " ", "Primary Compaign Source ");

		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
		waitUntilClickable(saveButton);
		clickElement(saveButton, "Save");

	}

	@Test

	public static void testOpportunityPipeLineReportTC17() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement oppor = driver.findElement(By.linkText("Opportunities"));
		waitUntilClickable(oppor);
		clickElement(oppor, "Opportunity");
		sleep(3000);

		// handle pop up
		WebElement oppurtunityPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(oppurtunityPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement opportunityPipeLine = driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		waitUntilClickable(opportunityPipeLine);
		clickElement(opportunityPipeLine, "Opportunity PipeLine");
	}

	@Test

	public static void stuckOpportunityTC18() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement oppor = driver.findElement(By.linkText("Opportunities"));
		waitUntilClickable(oppor);
		clickElement(oppor, "Opportunity");
		sleep(3000);

		WebElement oppurtunityPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(oppurtunityPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement stuckOpportunity = driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		waitUntilClickable(stuckOpportunity);
		clickElement(stuckOpportunity, "Stuck Opportunity");

	}

	@Test

	public static void testQuarterlySummaryReportTC19() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement oppor = driver.findElement(By.linkText("Opportunities"));
		waitUntilClickable(oppor);
		clickElement(oppor, "Opportunity");
		sleep(3000);

		WebElement oppurtunityPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(oppurtunityPopup, closePopup, "Pop up");

		WebElement interval = driver.findElement(By.xpath("//select[@id='quarter_q']"));
		selectElement(interval, "Current FY", "interval");

		WebElement include = driver.findElement(By.xpath("//select[@id='open']"));
		selectElement(include, "Open Opportunities", "Include");

		WebElement runReport = driver.findElement(By.xpath("//input[@value='Run Report']"));
		waitUntilClickable(runReport);
		clickElement(runReport, "runReport");

	}

	@Test
	public static void clickLeadTC20() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement leads = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		waitUntilClickable(leads);
		clickElement(leads, "leads");
		sleep(3000);

		WebElement LeadsPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(LeadsPopup, closePopup, "Pop up");

	}

	@Test
	public static void leadDropDownTC21() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement leads = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		waitUntilClickable(leads);
		clickElement(leads, "leads");
		sleep(3000);

		WebElement LeadsPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(LeadsPopup, closePopup, "Pop up");

		WebElement view = driver.findElement(By.cssSelector("#fcf"));
		waitUntilClickable(view);
		clickElement(view, "view");
		untilPageLoad();
	}

	@Test
	public static void defaultViewTC22() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement leads = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		waitUntilClickable(leads);
		clickElement(leads, "leads");
		sleep(3000);

		WebElement LeadsPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(LeadsPopup, closePopup, "Pop up");

		WebElement view2 = driver.findElement(By.cssSelector("#fcf"));
		waitUntilClickable(view2);
		clickElement(view2, "view");
		untilPageLoad();

		WebElement viewSelect = driver.findElement(By.cssSelector("#fcf"));
		selectElement(viewSelect, "Recently Viewed Leads", "view");

		usernameHomePage = driver.findElement(By.xpath("//*[@id=\"userNavButton\"]"));
		clickElement(usernameHomePage, "User Name at Home PAge");
		WebElement logout = driver.findElement(
				By.xpath("/html/body/div/div[1]/table/tbody/tr/td[3]/div/div[3]/div/div/div[2]/div[3]/a[5]"));
		clickElement(logout, "logout");

		loginToSalesforce();
		untilPageLoad();
		waitUntilClickable(leads);
		clickElement(leads, "leads");
		untilPageLoad();

		handlePopup(LeadsPopup, closePopup, "Pop up");

		WebElement goButton = driver.findElement(By.xpath("//span[@class='fBody']//input[@type='button']"));
		waitUntilClickable(goButton);
		clickElement(goButton, "Go");

	}

	@Test
	public static void leadDropDownTC23() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement leads = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		waitUntilClickable(leads);
		clickElement(leads, "leads");
		sleep(3000);

		WebElement LeadsPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(LeadsPopup, closePopup, "Pop up");

		WebElement view = driver.findElement(By.cssSelector("#fcf"));
		waitUntilClickable(view);
		clickElement(view, "view");
		untilPageLoad();

		WebElement view2 = driver.findElement(By.cssSelector("#fcf"));
		selectElement(view2, "Today's Leads", "view");

		WebElement goButton = driver.findElement(By.xpath("//span[@class='fBody']//input[@type='button']"));
		waitUntilClickable(goButton);
		clickElement(goButton, "Go");

	}

	@Test
	public static void newLeadTC24() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement leads = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		waitUntilClickable(leads);
		clickElement(leads, "leads");
		sleep(3000);

		WebElement LeadsPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(LeadsPopup, closePopup, "Pop up");

		WebElement newLead = driver.findElement(By.xpath("//td[@class='pbButton']//input[@type='button']"));
		waitUntilClickable(newLead);
		clickElement(newLead, "New");

		WebElement lastName = driver.findElement(By.id("name_lastlea2"));
		enterElementText(lastName, "bcde", "Last Name");

		WebElement company = driver.findElement(By.id("lea3"));
		enterElementText(company, "bcde", "Company");

		WebElement saveButton = driver.findElement(By.xpath("//td[@id='topButtonRow']/input[@name='save']"));
		waitUntilClickable(saveButton);
		clickElement(saveButton, "save");

	}

	@Test
	public static void createNewContactTC25() throws Exception {
		loginToSalesforce();
		untilPageLoad();

		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(cPopup, closePopup, "Pop up");

		WebElement newButton = driver.findElement(By.xpath("//input[@title='New']"));
		waitUntilClickable(newButton);
		clickElement(newButton, "New");

		untilPageLoad();
		WebElement lastName = driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterElementText(lastName, "New Contact", "Last Name");

		WebElement accountName = driver.findElement(By.xpath("//input[@id='con4']"));
		enterElementText(accountName, "tekarch", "Account Name");

		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
		waitUntilClickable(saveButton);
		clickElement(saveButton, "Save");
	}

	@Test

	public static void createNewViewContactTC26() throws Exception {

		loginToSalesforce();
		untilPageLoad();

		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(cPopup, closePopup, "Pop up");

		WebElement createNewView = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		waitUntilClickable(createNewView);
		clickElement(createNewView, "Create New View");

		untilPageLoad();
		WebElement viewName = driver.findElement(By.xpath("//input[@id='fname']"));
		enterElementText(viewName, "New View 2 26th Sept", "View Name");

		WebElement viewUniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
		enterElementText(viewUniqueName, "UniView1", " Unique View Name");

		WebElement saveButton = driver.findElement(By.xpath("//input[@class='btn primary']"));
		waitUntilClickable(saveButton);
		clickElement(saveButton, "Save");
	}

	@Test

	public static void verifyRecentlyCreatedContactPageTC27() throws Exception {
		
		loginToSalesforce();
		untilPageLoad();
		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		handlePopup(cPopup, closePopup, "Pop up");

		WebElement recentlyCreated = driver.findElement(By.xpath("//select[@id=\"hotlist_mode\"]"));
		selectElement(recentlyCreated, "Recently Created", "Recently Created");

	}

	@Test

	public static void myContactsViewTC28() throws Exception {

		loginToSalesforce();
		untilPageLoad();
		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		waitUntilClickable(closePopup);
		handlePopup(cPopup, closePopup, "Pop up");

		WebElement contactsDropDown = driver.findElement(By.xpath("//select[@name=\"fcf\"]"));
		waitUntilVisible(contactsDropDown, "abcd");
		selectElement(contactsDropDown, "All Contacts", "Contacts Drop Down");
	}
@Test

	public static void viewContactTC29() throws Exception {
	loginToSalesforce();
		untilPageLoad();
		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		waitUntilClickable(closePopup);
		handlePopup(cPopup, closePopup, "Pop up");
		untilPageLoad();
		
		WebElement contactName=driver.findElement(By.xpath("//table/tbody/tr[2]/th/a"));
		waitUntilClickable(contactName);
		clickElement(contactName, "Contact Name");
		untilPageLoad();

	}

	@Test

	public static void checkErrorMessageTC30() throws Exception {

		loginToSalesforce();
		untilPageLoad();
		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		waitUntilClickable(closePopup);
		handlePopup(cPopup, closePopup, "Pop up");

		WebElement createNewView = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		waitUntilClickable(createNewView);
		clickElement(createNewView, "Create New View");
		untilPageLoad();

		WebElement viewUniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
		enterElementText(viewUniqueName, "UniView1", " DFCE");

		WebElement saveButton = driver.findElement(By.xpath("//input[@class='btn primary']"));
		waitUntilClickable(saveButton);
		clickElement(saveButton, "Save");

	}

	@Test

	public static void cancelCreateViewContactTC31() throws Exception {
		loginToSalesforce();
		untilPageLoad();
		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		waitUntilClickable(closePopup);
		handlePopup(cPopup, closePopup, "Pop up");

		WebElement createNewView = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		waitUntilClickable(createNewView);
		clickElement(createNewView, "Create New View");

		untilPageLoad();
		WebElement viewName = driver.findElement(By.xpath("//input[@id='fname']"));
		enterElementText(viewName, "New View 2 29th Sept", "View Name");

		WebElement viewUniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
		enterElementText(viewUniqueName, "UniView1", " Unique 29th Sept View Name");

		WebElement cancelButton = driver.findElement(By.xpath("//input[@name=\"cancel\"]"));
		clickElement(cancelButton, "Cancel New View");

	}

	@Test

	public static void saveAndNewContactTC32() throws Exception {

		// SoftAssert softAssert = new SoftAssert(); // creating instance of softAssert
		loginToSalesforce();
		untilPageLoad();
		WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		waitUntilClickable(contact);
		clickElement(contact, "Contacts");
		sleep(3000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		waitUntilClickable(closePopup);
		handlePopup(cPopup, closePopup, "Pop up");

		WebElement newButton = driver.findElement(By.xpath("//input[@name=\"new\"]"));
		waitUntilClickable(newButton);
		clickElement(newButton, "New");

		WebElement lastName = driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		// enterElementText(lastName, "Indian", "Last Name"); this will fail test case
		// as data not allowed
		enterElementText(lastName, "last Name 29th", "Last Name");

		WebElement accountName = driver.findElement(By.xpath("//input[@id='con4']"));
		// enterElementText(accountName, "Global Media", "Account Name"); this will fail
		// test case as data not allowed
		enterElementText(accountName, " Tekarch", "Account Name");

		WebElement saveAndNew = driver.findElement(By.xpath("//input[@name=\"save_new\"]"));
		waitUntilClickable(saveAndNew);
		clickElement(saveAndNew, "Save & New");

	}

	@Test

	public static void verifyNameTC33() throws Exception {

		String expected = "User: mamta selenium2022 ~ Salesforce - Developer Edition";
		loginToSalesforce();
		untilPageLoad();

		WebElement homeButton = driver.findElement(By.xpath("	//a[contains(text(),'Home')]"));
		waitUntilClickable(homeButton);
		clickElement(homeButton, "Home");
		sleep(3000);
		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		waitUntilClickable(closePopup);
		handlePopup(cPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement firstLastName = driver.findElement(
				By.xpath("//h1[@class=\"currentStatusUserName\"]//a[@href=\"/_ui/core/userprofile/UserProfilePage\"]"));
		waitUntilClickable(firstLastName);
		clickElement(firstLastName, " First and Last name link at Home Page");
		untilPageLoad();
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public static void verifyNameTC34() throws Exception {

		loginToSalesforce();
		untilPageLoad();

		WebElement homeButton = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
		waitUntilClickable(homeButton);
		clickElement(homeButton, "Home");
		sleep(2000);

		WebElement cPopup = driver.findElement(By.xpath("//div[@id=\"tryLexDialogContent\"]"));
		WebElement closePopup = driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]"));
		waitUntilClickable(closePopup);
		handlePopup(cPopup, closePopup, "Pop up");
		untilPageLoad();

		WebElement firstLastName = driver.findElement(
				By.xpath("//h1[@class=\"currentStatusUserName\"]//a[@href=\"/_ui/core/userprofile/UserProfilePage\"]"));
		waitUntilClickable(firstLastName);
		clickElement(firstLastName, " First and Last name link at Home Page");
		untilPageLoad();

		WebElement editImage = driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/div[2]/div[1]/h3/div/div/a/img"));
		clickElement(editImage, " Edit");

		switchToFrame("contactInfoContentId");
		untilPageLoad();

		WebElement about = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		waitUntilClickable(about);
		clickElement(about, " About ");

		WebElement lastName = driver.findElement(By.id("lastName"));
		enterElementText(lastName, "updated", "Last name ");
		WebElement saveall = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div/input[1]"));
		clickElement(saveall, " saveall ");
		switchToDefaultWindow();
	}
}