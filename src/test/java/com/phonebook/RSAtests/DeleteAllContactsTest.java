package com.phonebook.RSAtests;

import com.phonebook.dto.MessageDto;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAllContactsTest extends TestBase{


    @Test
    public void deleteAllContactsSuccessTest() {
        MessageDto message = given()
                .header(AUTH, token)
                .when()
                .delete("contacts/clear")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(MessageDto.class);
        System.out.println(message.getMessage());
    }

}
