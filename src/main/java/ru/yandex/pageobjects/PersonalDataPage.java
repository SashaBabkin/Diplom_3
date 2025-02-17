package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDataPage extends BasePage {

    //Кнопка "Профиль"
    private String profileLink = ".//a[text()='Профиль']";
    //Кнопка "Выход"
    private String exitLink = ".//button[text()='Выход']";
    //Ссылка "Конструктор" в шапке
    private String constructorLink = ".//a[@class='AppHeader_header__link__3D_hX' and @href='/']";
    //Логотип StellarBurgers
    private String stellarBurgersLogo = ".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']";
    //Раздел "Соберите бургер Конструктора"
    private String makeBurgerZone = ".//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']";

    public PersonalDataPage(WebDriver driver) {
        super(driver);
    }


    //Ожидание загрузки личного кабинета
    @Step("Wait till personal account page is loaded")
    public void waitOfVisibilityExitButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(exitLink)));
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
    public boolean checkSwitchToConstructor() {
        return driver.findElement(By.xpath(makeBurgerZone)).isDisplayed();
    }

    //Выход из личного кабинета (клик по кнопке "Выход")
    @Step("Click Exit button - logging out")
    public void clickExitAccountButton() {
        driver.findElement(By.xpath(exitLink)).click();
    }

    //
    @Step("Check visibility of Exit button")
    public boolean exitButtonIsDisplayed() {
        return driver.findElement(By.xpath(exitLink)).isDisplayed();
    }




}
