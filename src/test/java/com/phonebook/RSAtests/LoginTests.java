package com.phonebook.RSAtests;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class LoginTests extends TestBase{

    AuthRequestDto auth = AuthRequestDto.builder()
            .username("artest1@gm.com")
            .password("arTest1234$")
            .build();

    @Test
    public void loginSuccessTest() {

        System.out.println();

        AuthResponseDto dto = given()
                .contentType("application/json")
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(dto.getToken());
    }


    @Test
    public void loginSuccessTest2() {

        System.out.println();

        String dtoToken = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("token"))
                .extract().path("token");
        System.out.println(dtoToken);
    }

    @Test
    public void loginWithWrongEmailTest () {
        ErrorDto error = given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDto.builder()
                        .username("artest0001@gm.com")
                        .password("arTest1234$").build())
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDto.class);

        System.out.println(error.getMessage()+ " *** " + error.getError() );

        Assert.assertEquals(error.getMessage(), "Login or Password incorrect");

    }
}
