package com.demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalDialog {

    SelenideElement modalDialog = $(".modal-dialog");
    SelenideElement modalDialogTitle = $(".modal-title");
    SelenideElement modalDialogTable = $(".table-responsive");
    SelenideElement closeButton = $("#closeLargeModal");

    public ModalDialog checkVisibility() {
        modalDialog.shouldBe(visible);
        return this;
    }

    public void checkTitle(String titleText) {
        modalDialogTitle.shouldHave(text(titleText));
    }

    public void checkTableValue(String label, String value) {
        modalDialogTable.$(byText(label))
                .sibling(0).shouldHave(text(value));
    }

    public void close() {
        closeButton.click();
    }
}
