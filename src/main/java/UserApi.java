import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseHttpClient {

    public static final String CREATE_USER = "api/auth/register";
    public static final String DELETE_USER = "api/auth/user";
    public static final String LOGIN_USER = "api/auth/login";

    @Step("Регистрация пользователя")
    public Response createUser(UserData userData) {
        return given()
                .spec(requestSpec())
                .body(userData)
                .post(CREATE_USER);
    }

    @Step("Авторизация пользователя")
    public Response loginUser(UserData userData) {
        return given()
                .spec(requestSpec())
                .body(userData)
                .post(LOGIN_USER);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String token) {
        given()
                .spec(requestSpec())
                .header("Authorization", token)
                .delete(DELETE_USER);
    }



}
