package com.demoqa.entities.iZDE;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class ProfileEntity {

    private String firstName;
    private String lastName;
    private String avatar;

}
