package com.yagmur.entity;


import com.yagmur.utility.ERole;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Auth extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Boolean isStudent;
    private String birthDate;
    private String email;
    private String password;
    @Builder.Default
    private ERole role = ERole.ROLE_CUSTOMER;

}
