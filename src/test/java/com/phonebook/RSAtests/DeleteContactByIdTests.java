package com.phonebook.RSAtests;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.SortedMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTests extends TestBase {
    String id;

    @BeforeMethod
    public void precondition () {

        ContactDto dto = ContactDto.builder()
                .name("Jogan")
                .lastName("Schtein")
                .email("some@gm.com")
                .phone("1234565789")
                .address("Central Platz")
                .description("Some description")
                .build();
        String message = given()
                .contentType(ContentType.JSON)
                .header(AUTH, token)
                .body(dto)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");
        String[] array = message.split(": ");
        id = array[1];
    }

    @Test
    public void deleteContactByIdSuccessTest() {
        String message  = given()
                .header(AUTH, token)
                .when()
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"))
                .extract().path("message");

        System.out.println(message);
    }

}
