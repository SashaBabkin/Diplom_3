import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.pageobjects.LoginPage;
import ru.yandex.pageobjects.MainPage;
import ru.yandex.pageobjects.RegistrationPage;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    UserApi userApi;
    UserData userData;
    String authToken;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        userApi = new UserApi();
        mainPage.clickLoginButton();
        loginPage.clickRegistrationLink();
    }

    @Test
    @DisplayName("User register succeed with valid data")
    @Description("Positive test of succeed to reg User")
    public void checkRegistrationTest() {
        userData = UserGenarate.getRandomUser();
        registrationPage.fillRegistrationForm(userData.getName(), userData.getEmail(), userData.getPassword());
        Response response = userApi.loginUser(userData);
        authToken = response.then().extract().path("accessToken");
        assertTrue(loginPage.checkLoginButtonIsDisplayed());

    }

    @Test
    @DisplayName("Not possible to reg User using a password less than 6 symbols")
    @Description("Negative test of no possibility to reg User if the length of password is less than 6 symbols")
    public void failedRegistrationNotValidPasswordTest() {
        registrationPage.fillRegistrationForm("Sasha", "sashatest@test.ru", "123");
        assertTrue(registrationPage.getNotValidPasswordNotification());
    }


    @After
    public void cleanUp() {
        if(authToken != null) {
            userApi.deleteUser(authToken);
        }
    }

}
