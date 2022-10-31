package com.example.zenturaserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Doctor")
public class DoctorDTO {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private String channellingTime;

}
