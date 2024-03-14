package com.phonebook.RSAtests;

import com.phonebook.dto.ContactDto;
import com.phonebook.dto.MessageDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class AddContactTests extends TestBase{

    @Test
    public void addContactSuccessTest () {
        ContactDto dto = ContactDto.builder()
                .name("Jogan")
                .lastName("Schtein")
                .email("some@gm.com")
                .phone("1234565789")
                .address("Central Platz")
                .description("Some description")
                .build();
       given()
                .contentType(ContentType.JSON)
                .header(AUTH, token)
                .body(dto)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message",containsString("Contact was added!"));
//                .extract().response().as(MessageDto.class);
//        System.out.println(message.getMessage());
    }
}
