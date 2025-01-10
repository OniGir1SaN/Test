package com.demoqa.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class RegisterEntity {

    private String firstName;
    private String email;
    private String password;
    private String password2;
}
