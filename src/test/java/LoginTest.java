import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.pageobjects.*;

import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest {

    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;
    RegistrationPage registrationPage;
    RestorePasswordPage restorePasswordPage;
    UserApi userApi;
    String authToken;
    UserData userData;


    @Before
    public void regUser() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        registrationPage = new RegistrationPage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);
        userApi = new UserApi();
        userData = UserGenarate.getRandomUser();
        Response response = userApi.createUser(userData);
        authToken = response.then().extract().path("accessToken");
    }

    @Test
    @DisplayName("Log in User with Login-button on main page")
    @Description("Positive test of logging in User using Login-button on main page")
    public void checkLoginWithMainButtonTest() {
        mainPage.clickLoginButton();
        loginPage.fillLoginForm(userData.getEmail(), userData.getPassword());
        accountPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Log in User via link Personal account")
    @Description("Positive test of logging in User using Link Personal account")
    public void checkLoginWithPersonalAccountButtonTest() {
        mainPage.clickPersonalAccountButton();
        loginPage.fillLoginForm(userData.getEmail(), userData.getPassword());
        accountPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Log in User via the Link in registration form")
    @Description("Positive test of logging in User using Link in registration form")
    public void checkLoginWithLoginLinkInRegFormTest() {
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegistrationLink();
        registrationPage.clickLoginLink();
        loginPage.fillLoginForm(userData.getEmail(), userData.getPassword());
        accountPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Log in User via the Link in restore password form")
    @Description("Positive test of logging in User using Link in restore password form")
    public void checkLoginWithLiginLinkInRestorePasswordTest() {
        mainPage.clickLoginButton();
        loginPage.pressRecoverPasswordLink();
        restorePasswordPage.clickLoginButton();
        loginPage.fillLoginForm(userData.getEmail(), userData.getPassword());
        accountPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @After
    public void cleanUp() {
        if(authToken != null) {
            userApi.deleteUser(authToken);
        }
    }
}
