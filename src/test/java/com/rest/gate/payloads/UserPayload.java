package com.rest.gate.payloads;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserPayload {
    private String firstName;
    private String lastName;
    private Address address;
    private Company company;
}