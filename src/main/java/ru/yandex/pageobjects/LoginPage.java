package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    //Поле Email
    private String inputEmail = ".//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input";
    //Поле Пароль
    private String inputPassword = ".//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input";
    //Кнопка Войти
    private String loginButton = ".//*[@id=\"root\"]/div/main/div/form/button";
    //Ссылка "Зарегистрироваться"
    private String registrationLink = ".//*[@id=\"root\"]/div/main/div/div/p[1]/a";
    //Ссылка "Восстановить пароль"
    private String restorePasswordLink = ".//*[@id=\"root\"]/div/main/div/div/p[2]/a";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Заполнение формы входа в Личный кабинет
    @Step("Filling the form of logging in")
    public void fillLoginForm(String email, String password) {
        driver.findElement(By.xpath(inputEmail)).sendKeys(email);
        driver.findElement(By.xpath(inputPassword)).sendKeys(password);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath(loginButton)));
        driver.findElement(By.xpath(loginButton)).click();
    }

    //Кликнуть на ссылку "Зарегистрироваться"
    @Step("Click Register link")
    public void clickRegistrationLink() {
        driver.findElement(By.xpath(registrationLink)).click();
    }

    //Кликнуть на ссылку "Восстановить пароль"
    @Step("Click Restore password link")
    public void pressRecoverPasswordLink() {
        driver.findElement(By.xpath(restorePasswordLink)).click();
    }

    //Проверка успешной регистрации (переход на страницу входа в личный кабинет)
    @Step("Checking the success of registration - redirection to Login form")
    public void checkRegistrationIsSucceed() {
        driver.findElement(By.xpath(loginButton)).isDisplayed();
    }

    //Проверка наличия кнопки "Войти"
    @Step("Checking the existence of Login button")
    public void checkLoginButtonIsDisplayed() {
        driver.findElement(By.xpath(loginButton)).isDisplayed();
    }


}
