package com.demoqa.tests;

import org.junit.jupiter.api.Test;

public class RegFormTest extends TestBase {

    // переменные для теста
    String firstName = "Bob";
    String lastName = "Marley";
    String userEmail = "bob.marley@gmail.com";
    String gender = "Male";
    String mobile = "2146703687";
    String birthYear = "1999";
    String birthMonth = "May";
    String birthDay = "29";
    String subject1 = "Accounting";
    String subject2 = "Math";
    String hobby1 = "Reading";
    String hobby2 = "Music";
    String picture1 = "pollutant.gif";
    String address1 = "920 S Harwood St, Dallas, TX 75201, USA";
    String state = "Uttar Pradesh";
    String city = "Agra";

    @Test
    void regFormFillAllSuccessTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setMobile(mobile)
                .setDateOfBirth(birthDay, birthMonth, birthYear)
                .setSubject(subject1)
                .setSubject(subject2)
                .setHobby(hobby1)
                .setHobby(hobby2)
                .uploadPicture(picture1)
                .setAddress(address1)
                .setState(state)
                .setCity(city)
                .submitForm();

        registrationPage.checkResultTableOpened()
                .checkSubmittedValue("Student Name", firstName + " " + lastName)
                .checkSubmittedValue("Student Email", userEmail)
                .checkSubmittedValue("Gender", gender)
                .checkSubmittedValue("Mobile", mobile)
                .checkSubmittedValue("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .checkSubmittedValue("Subjects", subject1 + ", " + subject2)
                .checkSubmittedValue("Hobbies", hobby1 + ", " + hobby2)
                .checkSubmittedValue("Picture", picture1)
                .checkSubmittedValue("Address", address1)
                .checkSubmittedValue("State and City", state + " " + city)
                .closeResultTable();
    }

    @Test
    void regFormFillMinSuccessTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(mobile)
                .submitForm();

        registrationPage.checkResultTableOpened()
                .checkSubmittedValue("Student Name", firstName + " " + lastName)
                .checkSubmittedValue("Gender", gender)
                .checkSubmittedValue("Mobile", mobile)
                .closeResultTable();
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
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setDateOfBirth(birthDay, birthMonth, birthYear)
                .setSubject(subject1)
                .setHobby(hobby2)
                .setAddress(address1)
                .setState(state)
                .setCity(city)
                .submitForm();

        registrationPage.checkResultTableNotOpened();
    }
}