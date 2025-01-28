package org.example;

import com.codeborne.selenide.*;
import lombok.Getter;
import org.example.ui.datas.BankAccount;
import org.example.ui.pages.RegisterAccountPage;
import org.example.utils.RandomData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.element;


public class SimpleUiTest {

    @BeforeAll
    public static void setupUiTests() {
        Configuration.baseUrl = "https://parabank.parasoft.com";
    }

    @Test
    public void userCanCreateAccount() {
        // Подготовка страницы
        RegisterAccountPage registerAccountPage = new RegisterAccountPage();
        registerAccountPage.open();

        //Подготовка данных
        BankAccount bankAccount = BankAccount.builder()
                .firstName(RandomData.randomString()).lastName(RandomData.randomString()).address(RandomData.randomString())
                .addressCity(RandomData.randomString()).addressState(RandomData.randomString())
                .zipCode("600018").socialSecurityNumber("2323-34-5454").userName(RandomData.randomString()).passWord("qwerty123").confirmPassword("qwerty123")
                .build();


        registerAccountPage.register(bankAccount);

        registerAccountPage.getAccountCreatedSuccessfully().shouldHave(Condition.exactText("Your account was created successfully. You are now logged in."));
    }
}
