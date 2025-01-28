package com.demoqa.entities.iZDE;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class ChangeEmailEntity {

    private String email;
    private String currentPassword;
}
