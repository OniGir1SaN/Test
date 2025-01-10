package com.demoqa.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class LoginEntity {

    private String email;
    private String password;
}
