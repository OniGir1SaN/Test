package com.demoqa.entities.iZDE;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class ResetNumberEntity {

    private int number;
    private String password;
}
