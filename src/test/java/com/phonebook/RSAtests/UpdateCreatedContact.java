package com.phonebook.RSAtests;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateCreatedContact extends TestBase{

    String id;
    ContactDto contact;

    @BeforeMethod
    public void precondition () {

        contact = ContactDto.builder()
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
                .body(contact)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");
        String[] array = message.split(": ");
        contact.setId(array[1]);
    }

    @Test
    public void updateContactPhoneNumberSuccessTests() {
        contact.setPhone("9876543210");

        String message =  given()
                .contentType(ContentType.JSON)
                .header(AUTH, token)
                .body(contact)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");
        System.out.println(message);

    }

    @Test
    public void updateContactsWrongPhoneNumberTest() {
        contact.setPhone("987654");

        String message =  given()
                .contentType(ContentType.JSON)
                .header(AUTH, token)
                .body(contact)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(400)
                .extract().path("error");
        System.out.println(message);
    }
    @Test
    public void updateContactsUnauthorizedTest() {

        System.out.println(contact);
        String message =  given()
                .contentType(ContentType.JSON)
                .header(AUTH, "")
                .body(contact)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(401)
                .extract().path("error");
        System.out.println(message);
    }

    @Test
    public void updateContactsWithWrongContactIdTest() {

        contact.setId("dfc2c68b-9578-454c-bc64-caba0969d560");
        String message =  given()
                .contentType(ContentType.JSON)
                .header(AUTH, token)
                .body(contact)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(404)
                .extract().path("error");
        System.out.println(message);
    }


}
