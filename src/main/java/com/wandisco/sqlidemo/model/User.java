package com.wandisco.sqlidemo.model;

import lombok.*;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String firstName;

    private String favouriteAnimal;

    private String favouriteColour;
}
