package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    //Поле Имя
    private String inputName = ".//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input";
    //Поле Email
    private String inputEmail = ".//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input";
    //Поле Пароль
    private String inputPassword = ".//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input";
    //Кнопка Зарегистрироваться
    private String regButton = ".//*[@id=\"root\"]/div/main/div/form/button";
    //Ссылка "Войти"
    private String loginLink = ".//*[@id=\"root\"]/div/main/div/div/p/a";
    //Уведомление о некорректном пароле
    private String incorrectPasswordNotification = ".//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p";

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    //Заполнение формы регистрации
    @Step("Fill the registration form")
    public void fillRegistrationForm(String name, String email, String password) {
        driver.findElement(By.xpath(inputName)).sendKeys(name);
        driver.findElement(By.xpath(inputEmail)).sendKeys(email);
        driver.findElement(By.xpath(inputPassword)).sendKeys(password);
        driver.findElement(By.xpath(regButton)).click();
    }

    //Клик на ссылку "Войти"
    @Step("Click Login link")
    public void clickLoginLink() {
        driver.findElement(By.xpath(loginLink)).click();
    }

    //Появление уведомления о невалидном пароле
    @Step("Checking that there is a notification of invalid password")
    public void getNotValidPasswordNotification() {
        driver.findElement(By.xpath(incorrectPasswordNotification)).isDisplayed();
    }

}
