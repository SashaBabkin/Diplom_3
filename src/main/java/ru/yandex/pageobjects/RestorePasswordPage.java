package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage extends BasePage {

    //ссылка "Войти" (Вспомнили пароль?)
    private String loginLink = ".//*[@id=\"root\"]/div/main/div/div/p/a";

    public RestorePasswordPage(WebDriver driver) {
        super(driver);
    }

    //Клик на ссылку "Войти"
    @Step("Click Login link")
    public void clickLoginButton() {
        driver.findElement(By.xpath(loginLink)).click();
    }
}
