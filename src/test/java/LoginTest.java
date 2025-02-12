import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.pageobjects.*;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {

    String name = "Sasha";
    String email = "sashatest@test.ru";
    String password = "1234567";

    @Before
    public void regUser() {
        UserData userData = new UserData(email, password, name);
        given()
                .header("Content-type", "application/json")
                .body(userData)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
    }

    @Test
    @DisplayName("Log in User with Login-button on main page")
    @Description("Positive test of logging in User using Login-button on main page")
    public void checkLoginWithMainButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        AccountPage accountPage = new AccountPage(driver);
        accountPage.checkMakeOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Log in User via link Personal account")
    @Description("Positive test of logging in User using Link Personal account")
    public void checkLoginWithPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        AccountPage accountPage = new AccountPage(driver);
        accountPage.checkMakeOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Log in User via the Link in registration form")
    @Description("Positive test of logging in User using Link in registration form")
    public void checkLoginWithLoginLinkInRegFormTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();
        loginPage.fillLoginForm(email, password);
        AccountPage accountPage = new AccountPage(driver);
        accountPage.checkMakeOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Log in User via the Link in restore password form")
    @Description("Positive test of logging in User using Link in restore password form")
    public void checkLoginWithLiginLinkInRestorePasswordTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.pressRecoverPasswordLink();
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.clickLoginButton();
        loginPage.fillLoginForm(email, password);
        AccountPage accountPage = new AccountPage(driver);
        accountPage.checkMakeOrderButtonIsDisplayed();
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
