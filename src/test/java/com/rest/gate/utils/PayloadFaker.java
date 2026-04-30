package com.rest.gate.utils;
import com.github.javafaker.Faker;
import com.rest.gate.payloads.Address;
import com.rest.gate.payloads.Company;
import com.rest.gate.payloads.UserPayload;

import java.util.HashMap;
import java.util.Map;

public class PayloadFaker {

    private static final Faker faker = new Faker();

    public static UserPayload createUserPayload() {

        Address address = new Address(
                faker.address().country(),
                faker.address().city(),
                faker.address().streetAddress()
        );

        Company company = new Company(
                faker.company().name(),
                faker.company().profession()
        );

        return new UserPayload(
                faker.name().firstName(),
                faker.name().lastName(),
                address,
                company
        );
    }

    public static Map<String, String> createFormData() {

        Map<String, String> formData = new HashMap<>();

        formData.put("userName", faker.name().firstName());
        formData.put("lastName", faker.name().lastName());
        return formData;
    }
}