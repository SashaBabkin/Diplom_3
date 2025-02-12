package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends BasePage {

    //Ссылка Булки
    private String bunsLink = ".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]/span";
    //Ссылка Соусы
    private String saucesLink = ".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]/span";
    //Ссылка Начинки
    private String fillingsLink = ".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]/span";

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
    public void checkBunsActive() {
        bunsLinkClick();
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]")).isDisplayed();
    }

    //Проверку, что сработал клик на ссылку Соусы
    @Step("Section Sauces is available")
    public void checkCaucesActive() {
        saucesLinkClick();
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]")).isDisplayed();

    }

    //Проверка, что сработал клик на ссылку Начинки
    @Step("Section Fillings is available")
    public void checkFillingsActive() {
        fillingsLinkClick();
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]")).isDisplayed();
    }
}
