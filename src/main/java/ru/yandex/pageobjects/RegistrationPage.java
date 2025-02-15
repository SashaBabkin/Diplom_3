package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    //Поле Имя
    private String inputName = ".//label[text()='Имя']//following-sibling::input";
    //Поле Email
    private String inputEmail = ".//label[text()='Email']//following-sibling::input";
    //Поле Пароль
    private String inputPassword = ".//input[@name='Пароль']";
    //Кнопка Зарегистрироваться
    private String regButton = ".//button[text()='Зарегистрироваться']";
    //Ссылка "Войти"
    private String loginLink = ".//a[text()='Войти']";
    //Уведомление о некорректном пароле
    private String incorrectPasswordNotification = ".//p[text()='Некорректный пароль']";

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
    public boolean getNotValidPasswordNotification() {
        return driver.findElement(By.xpath(incorrectPasswordNotification)).isDisplayed();
    }

}
