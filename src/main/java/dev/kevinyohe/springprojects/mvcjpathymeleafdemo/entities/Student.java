package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "s_id")
    @Getter
    private UUID id;

    @Getter
    @Setter
    @Column(name = "s_first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "s_last_name")
    private String lastName;


}
