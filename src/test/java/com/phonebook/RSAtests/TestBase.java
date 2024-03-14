package com.phonebook.RSAtests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {


    public static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYXJ0ZXN0MUBnbS5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcxMTAxMjU5MCwiaWF0IjoxNzEwNDEyNTkwfQ.FeAbajXcccMR-d9eFiZzprq26zVyzSpr088mAIMV-Ko";
    public static final String AUTH = "Authorization";

@BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

}
