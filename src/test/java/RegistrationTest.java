import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import ru.yandex.pageobjects.LoginPage;
import ru.yandex.pageobjects.MainPage;
import ru.yandex.pageobjects.RegistrationPage;

import static io.restassured.RestAssured.given;

public class RegistrationTest extends BaseTest {

    String name = "Sasha";
    String email = "sashatest@test.ru";
    String password = "1234567";
    String notValidPassword = "123";

    @Test
    @DisplayName("User register succeed with valid data")
    @Description("Positive test of succeed to reg User")
    public void checkRegistrationTest() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationForm(name, email, password);
        loginPage.checkRegistrationIsSucceed();
    }

    @Test
    @DisplayName("Not possible to reg User using a password less than 6 symbols")
    @Description("Negative test of no possibility to reg User if the length of password is less than 6 symbols")
    public void failedRegistrationNotValidPasswordTest() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationForm(name, email, notValidPassword);
        registrationPage.getNotValidPasswordNotification();
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
