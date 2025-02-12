package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDataPage extends BasePage {

    //Кнопка "Профиль"
    private String profileLink = ".//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a";
    //Кнопка "История заказов"
    private String orderHistoryLink = ".//*[@id=\"root\"]/div/main/div/nav/ul/li[2]/a";
    //Кнопка "Выход"
    private String exitLink = ".//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button";
    //Ссылка "Конструктор" в шапке
    private String constructorLink = ".//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p";
    //Логотип StellarBurgers
    private String stellarBurgersLogo = ".//*[@id=\"root\"]/div/header/nav/div/a";
    //Раздел "Соберите бургер Конструктора"
    private String makeBurgerZone = ".//*[@id=\"root\"]/div/main/section[1]/h1";

    public PersonalDataPage(WebDriver driver) {
        super(driver);
    }

    //Проверка наличия ссылки-переключателя "Профиль"
    @Step("Check the existence of Profile link")
    public void checkProfileLink() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath(profileLink)));
    }

    //Клик по ссылке "Конструктор"
    @Step("Click Constructor link")
    public void clickConstructorLink() {
        driver.findElement(By.xpath(constructorLink)).click();
    }

    //Клик по логотипу StellarBurgers
    @Step("Click Stellar Burgers logo")
    public void clickStellarBurgersLogo() {
        driver.findElement(By.xpath(stellarBurgersLogo)).click();
    }

    //Проверка перехода в Конструктор
    @Step("Checking the switch to Constructor")
    public void checkSwitchToConstructor() {
        driver.findElement(By.xpath(makeBurgerZone)).isDisplayed();
    }

    //Выход из личного кабинета (клик по кнопке "Выход")
    @Step("Click Exit button - logging out")
    public void clickExitAccountButton() {
        driver.findElement(By.xpath(exitLink)).click();
    }




}
