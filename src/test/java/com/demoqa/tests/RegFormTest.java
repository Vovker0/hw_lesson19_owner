package com.demoqa.tests;

import com.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

public class RegFormTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    void regFormFillAllSuccessTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .setGender(testData.gender)
                .setMobile(testData.mobile)
                .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                .setSubjects(testData.selectedSubjects)
                .setHobbies(testData.selectedHobbies)
                .uploadPicture(testData.picture1)
                .setAddress(testData.address1)
                .setState(testData.state)
                .setCity(testData.city)
                .submitForm();

        registrationPage.checkResultTableOpened()
                .checkSubmittedValue("Student Name", testData.firstName + " " + testData.lastName)
                .checkSubmittedValue("Student Email", testData.userEmail)
                .checkSubmittedValue("Gender", testData.gender)
                .checkSubmittedValue("Mobile", testData.mobile)
                .checkSubmittedValue("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                .checkSubmittedValue("Subjects", String.join(", ", testData.selectedSubjects))
                .checkSubmittedValue("Hobbies", String.join(", ", testData.selectedHobbies))
                .checkSubmittedValue("Picture", testData.picture1)
                .checkSubmittedValue("Address", testData.address1)
                .checkSubmittedValue("State and City", testData.state + " " + testData.city);
    }

    @Test
    void regFormFillMinSuccessTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setMobile(testData.mobile)
                .submitForm();

        registrationPage.checkResultTableOpened()
                .checkSubmittedValue("Student Name", testData.firstName + " " + testData.lastName)
                .checkSubmittedValue("Gender", testData.gender)
                .checkSubmittedValue("Mobile", testData.mobile);

    }

    @Test
    void regFormAllBlankNegativeTest() {

        registrationPage.openPage()
                .removeBanners()
                .submitForm();

        registrationPage.checkResultTableNotOpened();
    }

    @Test
    void regFormFillNotAllNecessaryFieldsNegativeTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                .setSubjects(testData.selectedSubjects)
                .setHobbies(testData.selectedHobbies)
                .setAddress(testData.address1)
                .setState(testData.state)
                .setCity(testData.city)
                .submitForm();

        registrationPage.checkResultTableNotOpened();
    }
}