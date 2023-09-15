package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegFormTest {

    // переменные для теста
    String firstName = "Bob";
    String lastName = "Marley";
    String userEmail = "bob.marley@gmail.com";
    String gender = "Male";
    String mobile = "2146703687";
    String birthYear = "1999";
    String birthMnth = "May";
    String birthDay = "29";
    String dobClass = ".react-datepicker__day--0";
    String subject1 = "Accounting";
    String subject2 = "Math";
    String hobby1 = "Reading";
    String hobby2 = "Music";
    String picture1 = "polutant.gif";
    String address1 = "920 S Harwood St, Dallas, TX 75201, USA";
    String state = "Uttar Pradesh";
    String city = "Agra";


    @BeforeAll
    static void beforeAll() {
        // Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1280x960";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void regFormTest() {

        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        // убираем мешающие банеры
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // заполняем форму
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__month-select").selectOption(birthMnth);
        $(dobClass + birthDay + ":not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();

        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();

        $("#uploadPicture").uploadFromClasspath(picture1);

        $("#currentAddress").setValue(address1);

        $("#state").click();
        $("#state").$(byText(state)).click();
        $("#city").click();
        $("#city").$(byText(city)).click();

        $("#submit").click();

        // проверяем данные
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).sibling(0).shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).sibling(0).shouldHave(text(mobile));
        $(".table-responsive").$(byText("Date of Birth")).sibling(0).shouldHave(text(birthDay + " " + birthMnth + "," + birthYear));
        $(".table-responsive").$(byText("Subjects")).sibling(0).shouldHave(text(subject1 + ", " + subject2));
        $(".table-responsive").$(byText("Hobbies")).sibling(0).shouldHave(text(hobby1 + ", " + hobby2));
        $(".table-responsive").$(byText("Picture")).sibling(0).shouldHave(text(picture1));
        $(".table-responsive").$(byText("Address")).sibling(0).shouldHave(text(address1));
        $(".table-responsive").$(byText("State and City")).sibling(0).shouldHave(text(state + " " + city));
        $("#closeLargeModal").click();
    }
}