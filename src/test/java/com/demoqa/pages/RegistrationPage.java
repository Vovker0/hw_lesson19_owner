package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ModalDialog;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    CalendarComponent calendar = new CalendarComponent();
    ModalDialog resultTable = new ModalDialog();

    SelenideElement mainHeader = $(".main-header");
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement emailInput = $("#userEmail");
    SelenideElement genderInput = $("#genterWrapper");
    SelenideElement mobileInput = $("#userNumber");
    SelenideElement birthDateInput = $("#dateOfBirthInput");
    SelenideElement subjectInput = $("#subjectsInput");
    SelenideElement hobbyInput = $("#hobbiesWrapper");
    SelenideElement pictureInput = $("#uploadPicture");
    SelenideElement addressInput = $("#currentAddress");
    SelenideElement stateInput = $("#state");
    SelenideElement cityInput = $("#city");
    SelenideElement submitButton = $("#submit");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        mainHeader.shouldHave(text("Practice Form"));
        return this;
    }

    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setMobile(String value) {
        mobileInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        birthDateInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String value) {
        hobbyInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        pictureInput.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateInput.click();
        stateInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityInput.click();
        cityInput.$(byText(value)).click();
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

    public RegistrationPage checkResultTableOpened() {
        resultTable.checkVisibility()
                .checkTitle("Thanks for submitting the form");
        return this;
    }

    public RegistrationPage checkSubmittedValue(String fieldLabel, String fieldValue) {
        resultTable.checkTableValue(fieldLabel, fieldValue);
        return this;
    }

    public void closeResultTable() {
        resultTable.close();
    }
}