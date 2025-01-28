package com.demoqa.entities.iZDE;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class ChangePasswordEntity {

    private String currentPassword;
    private String newPassword;
    private String repeatNewPassword;
}
