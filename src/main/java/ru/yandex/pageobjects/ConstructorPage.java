package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends BasePage {

    //Ссылка Булки
    private String bunsLink = ".//span[text()='Булки']";
    //Ссылка Соусы
    private String saucesLink = ".//span[text()='Соусы']";
    //Ссылка Начинки
    private String fillingsLink = ".//span[text()='Начинки']";
    //Элемент для проверки перехода в раздел Булки
    private String bunsSectorSwitched = ".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]";
    //Элемент для проверки перехода в раздел Соусы
    private String soucesSectorSwitched = ".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]";
    //Элемент для проверки перехода в раздел Начинки
    private String fillingsSectorSwitched = ".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]";

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    //Клик на ссылку Булки
    @Step("Click the Buns link")
    public void bunsLinkClick() {
        driver.findElement(By.xpath(bunsLink)).click();
    }

    //Клик на ссылку Соусы
    @Step("Click the Sauces link")
    public void saucesLinkClick() {
        driver.findElement(By.xpath(saucesLink)).click();
    }

    //Клик на ссылку Начинки
    @Step("Click the Fillings link")
    public void fillingsLinkClick() {
        driver.findElement(By.xpath(fillingsLink)).click();
    }

    //Проверка, что сработал клик на ссылку Булки
    @Step("Section Buns is available")
    public boolean checkBunsActive() {
        bunsLinkClick();
        return driver.findElement(By.xpath(bunsSectorSwitched)).isDisplayed();
    }

    //Проверку, что сработал клик на ссылку Соусы
    @Step("Section Sauces is available")
    public boolean checkCaucesActive() {
        saucesLinkClick();
        return driver.findElement(By.xpath(soucesSectorSwitched)).isDisplayed();

    }

    //Проверка, что сработал клик на ссылку Начинки
    @Step("Section Fillings is available")
    public boolean checkFillingsActive() {
        fillingsLinkClick();
        return driver.findElement(By.xpath(fillingsSectorSwitched)).isDisplayed();
    }
}
