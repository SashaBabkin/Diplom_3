import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.pageobjects.*;

import static io.restassured.RestAssured.given;

public class PersonalAccountTest extends BaseTest {

    String name = "Sasha";
    String email = "sashatest@test.ru";
    String password = "1234567";

    @Before
    public void regAndLoginUser() {

        UserData userData = new UserData(email, password, name);
        given()
                .header("Content-type", "application/json")
                .body(userData)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
        driver.get(MainPage.URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
    }

    @Test
    @DisplayName("Switch to Personal account data page")
    @Description("Positive test of switching to Personal account data info")
    public void switchToPersonalAccountTest() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.switchToPersonalAccount();
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        personalDataPage.checkProfileLink();
    }

    @Test
    @DisplayName("Switch to Constructor via Link Constructor")
    @Description("Positive test of switching to Constructor using the Link Constructor")
    public void switchToConstructorWithLinkTest() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.switchToPersonalAccount();
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        personalDataPage.clickConstructorLink();
        personalDataPage.checkSwitchToConstructor();
    }

    @Test
    @DisplayName("Switch to Constructor via logo Stellar Burgers")
    @Description("Positive test of switching to Constructor using the logo of Stellar Burgers")
    public void switchToConstructorWithLogoTest() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.switchToPersonalAccount();
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        personalDataPage.clickStellarBurgersLogo();
        personalDataPage.checkSwitchToConstructor();
    }

    @Test
    @DisplayName("Exit the account via button Exit")
    @Description("Positive test of logging out the account using exit button")
    public void exitProfileTest() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.switchToPersonalAccount();
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        personalDataPage.clickExitAccountButton();
        accountPage.switchToPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.checkLoginButtonIsDisplayed();
    }

    @After
    public void deleteUser() {
        UserData userData = new UserData(email, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(userData)
                        .when()
                        .post("https://stellarburgers.nomoreparties.site/api/auth/login");
        if (response.then().extract().statusCode() == 200) {
            String authToken = response.then().extract().body().path("accessToken");
            given().header("Content-type", "application/json").header("Authorization", authToken)
                    .when()
                    .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
        }
    }
}
