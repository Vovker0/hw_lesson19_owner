package com.demoqa.tests;

import com.demoqa.pages.RegistrationPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("RegFormTest")
@Owner("Vladimir")
public class RegFormTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    @Tag("Positive")
    @DisplayName("Успешное заполнение всех полей регистрационной формы")
    void regFormFillAllSuccessTest() {
        step("Открываем страницу", () -> registrationPage.openPage());
        step("Удаляем мешающие баннеры", () -> registrationPage.removeBanners());
        step("Заполняем все поля регистрационной формы", () -> {
            registrationPage.setFirstName(testData.firstName)
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
        });
        step("Проверяем введённые данные в итоговой форме", () -> {
            registrationPage.checkResultTableOpened()
                    .checkSubmittedValue("Student Name", testData.firstName + " " + testData.lastName)
                    .checkSubmittedValue("Student Email", testData.userEmail)
                    .checkSubmittedValue("Gender", testData.gender)
                    .checkSubmittedValue("Mobile", testData.mobile)
                    .checkSubmittedValue("Date of Birth",
                            testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                    .checkSubmittedValue("Subjects", String.join(", ", testData.selectedSubjects))
                    .checkSubmittedValue("Hobbies", String.join(", ", testData.selectedHobbies))
                    .checkSubmittedValue("Picture", testData.picture1)
                    .checkSubmittedValue("Address", testData.address1)
                    .checkSubmittedValue("State and City", testData.state + " " + testData.city);
        });
    }

    @Test
    @Tag("Positive")
    @DisplayName("Успешное заполнение только обязательных полей регистрационной формы")
    void regFormFillMinSuccessTest() {
        step("Открываем страницу", () -> registrationPage.openPage());
        step("Удаляем мешающие баннеры", () -> registrationPage.removeBanners());
        step("Заполняем обязательные поля регистрационной формы", () -> {
            registrationPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setGender(testData.gender)
                    .setMobile(testData.mobile)
                    .submitForm();
        });
        step("Проверяем введённые данные в итоговой форме", () -> {
            registrationPage.checkResultTableOpened()
                    .checkSubmittedValue("Student Name", testData.firstName + " " + testData.lastName)
                    .checkSubmittedValue("Gender", testData.gender)
                    .checkSubmittedValue("Mobile", testData.mobile);
        });
    }

    @Test
    @Tag("Negative")
    @Tag("shortest")
    @DisplayName("Негативный тест без заполнения каких-либо полей регистрационной формы")
    void regFormAllBlankNegativeTest() {
        step("Открываем страницу", () -> registrationPage.openPage());
        step("Удаляем мешающие баннеры", () -> registrationPage.removeBanners());
        step("Нажимаем кнопку 'Submit'", () -> registrationPage.submitForm());
        step("Проверяем, что итоговая форма не открылась", () -> registrationPage.checkResultTableNotOpened());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Негативный тест, не все обязательные поля регистрационной формы были заполнены")
    void regFormFillNotAllNecessaryFieldsNegativeTest() {
        step("Открываем страницу", () -> registrationPage.openPage());
        step("Удаляем мешающие баннеры", () -> registrationPage.removeBanners());
        step("Заполняем некоторые поля регистрационной формы, не все обязательные", () -> {
            registrationPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.userEmail)
                    .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                    .setSubjects(testData.selectedSubjects)
                    .setHobbies(testData.selectedHobbies)
                    .setAddress(testData.address1)
                    .setState(testData.state)
                    .setCity(testData.city)
                    .submitForm();
        });
        step("Проверяем, что итоговая форма не открылась", () -> registrationPage.checkResultTableNotOpened());
    }
}