package com.rest.gate.payloads;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {
    private String firstName;
    private String lastName;
    private int age;

}
