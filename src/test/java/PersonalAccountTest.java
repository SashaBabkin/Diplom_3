import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.pageobjects.*;

import static org.junit.Assert.assertTrue;

public class PersonalAccountTest extends BaseTest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PersonalDataPage personalDataPage;
    AccountPage accountPage;
    UserApi userApi;
    UserData userData;
    String authToken;


    @Before
    public void regAndLoginUser() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        personalDataPage = new PersonalDataPage(driver);
        accountPage = new AccountPage(driver);
        userApi = new UserApi();
        userData = UserGenarate.getRandomUser();
        Response response = userApi.createUser(userData);
        authToken = response.then().extract().path("accessToken");
    }

    @Test
    @DisplayName("Switch to Personal account data page")
    @Description("Positive test of switching to Personal account data info")
    public void switchToPersonalAccountTest() {
        mainPage.clickLoginButton();
        loginPage.fillLoginForm(userData.getEmail(), userData.getPassword());
        accountPage.switchToPersonalAccount();
        personalDataPage.waitOfVisibilityExitButton();
        assertTrue(personalDataPage.exitButtonIsDisplayed());
    }

    @Test
    @DisplayName("Switch to Constructor via Link Constructor")
    @Description("Positive test of switching to Constructor using the Link Constructor")
    public void switchToConstructorWithLinkTest() {
        accountPage.switchToPersonalAccount();
        personalDataPage.clickConstructorLink();
        assertTrue(personalDataPage.checkSwitchToConstructor());
    }

    @Test
    @DisplayName("Switch to Constructor via logo Stellar Burgers")
    @Description("Positive test of switching to Constructor using the logo of Stellar Burgers")
    public void switchToConstructorWithLogoTest() {
        accountPage.switchToPersonalAccount();
        personalDataPage.clickStellarBurgersLogo();
        assertTrue(personalDataPage.checkSwitchToConstructor());
    }

    @Test
    @DisplayName("Exit the account via button Exit")
    @Description("Positive test of logging out the account using exit button")
    public void exitProfileTest() {
        mainPage.clickLoginButton();
        loginPage.fillLoginForm(userData.getEmail(), userData.getPassword());
        accountPage.switchToPersonalAccount();
        personalDataPage.waitOfVisibilityExitButton();
        personalDataPage.clickExitAccountButton();
        accountPage.switchToPersonalAccount();
        assertTrue(loginPage.checkLoginButtonIsDisplayed());
    }

    @After
    public void cleanUp() {
        if(authToken != null) {
            userApi.deleteUser(authToken);
        }
    }
}
