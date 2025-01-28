package org.example.ui.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.example.ui.datas.BankAccount;

import static com.codeborne.selenide.Selenide.element;

@Getter
public class RegisterAccountPage {

    private SelenideElement firstNameInput = element(Selectors.byId("customer.firstName"));

    private SelenideElement lastNameInput = element(Selectors.byId("customer.lastName"));

    private SelenideElement addressInput = element(Selectors.byId("customer.address.street"));

    private SelenideElement addressCityInput = element(Selectors.byId("customer.address.city"));

    private SelenideElement addressStateInput = element(Selectors.byId("customer.address.state"));

    private SelenideElement zipCodeInput = element(Selectors.byId("customer.address.zipCode"));

    private SelenideElement socialSecurityNumberInput = element(Selectors.byId("customer.ssn"));

    private SelenideElement userNameInput = element(Selectors.byId("customer.username"));

    private SelenideElement passWordInput = element(Selectors.byId("customer.password"));

    private SelenideElement confirmPasswordInput = element(Selectors.byId("repeatedPassword"));

    private SelenideElement registerButton = element(Selectors.byValue("Register"));

   private SelenideElement accountCreatedSuccessfully = element(Selectors.byXpath("//*[@id=\"rightPanel\"]/p"));

    public void open() {
        Selenide.open("/parabank/register.htm");
    }

    public void register(BankAccount bankAccount) {
        firstNameInput.sendKeys(bankAccount.getFirstName());
        lastNameInput.sendKeys(bankAccount.getLastName());
        addressInput.sendKeys(bankAccount.getAddress());
        addressCityInput.sendKeys(bankAccount.getAddressCity());
        addressStateInput.sendKeys(bankAccount.getAddressState());
        zipCodeInput.sendKeys(bankAccount.getZipCode());
        socialSecurityNumberInput.sendKeys(bankAccount.getSocialSecurityNumber());
        userNameInput.sendKeys(bankAccount.getUserName());
        passWordInput.sendKeys(bankAccount.getPassWord());
        confirmPasswordInput.sendKeys(bankAccount.getConfirmPassword());

        registerButton.click();

    }
}
