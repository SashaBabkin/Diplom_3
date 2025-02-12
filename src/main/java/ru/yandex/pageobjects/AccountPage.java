package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage {

    //Кнопка оформить заказ
    private String makeOrderButton = ".//div[@class = 'BurgerConstructor_basket__container__2fUl3 mt-10']/button[text() = 'Оформить заказ']";
    //Ссылка "Личный кабинет"
    private String personalAccountLink = ".//*[@id=\"root\"]/div/header/nav/a/p";

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    //Проверка наличия кнопки "Оформить заказ"
    @Step("Check the existence of Make order button")
    public void checkMakeOrderButtonIsDisplayed() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath(makeOrderButton)));
    }

    //Вход в личный кабинет по ссылке "Личный кабинет"
    @Step("Click the Personal account link")
    public void switchToPersonalAccount() {
        driver.findElement(By.xpath(personalAccountLink)).click();
    }

}
