package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    //кнопка "Личный Кабинет" вверху страницы
    private String personalAccountButton = ".//*[@id=\"root\"]/div/header/nav/a/p";
    //кнопка "Войти в аккаунт"
    private String loginButton = ".//*[@id=\"root\"]/div/main/section[2]/div/button";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //URL сервиса
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    //Клик на ссылку Личный кабинет
    @Step("Click Personal account link")
    public void clickPersonalAccountButton() {
        driver.findElement(By.xpath(personalAccountButton)).click();
    }

    //Клик на кнопку Войти
    @Step("Click Login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath(loginButton)).click();
    }

}
