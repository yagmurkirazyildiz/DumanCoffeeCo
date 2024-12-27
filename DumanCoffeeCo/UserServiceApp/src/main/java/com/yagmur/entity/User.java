package com.yagmur.entity;
import com.yagmur.utility.EStatus;
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
public class User extends BaseEntity{
    @Id
    private String id;
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String address;
    @Builder.Default
    private String image = "https://media.istockphoto.com/id/1209654046/vector/user-avatar-profile-icon-black-vector-illustration.jpg?s=612x612&w=0&k=20&c=EOYXACjtZmZQ5IsZ0UUp1iNmZ9q2xl1BD1VvN6tZ2UI=";
    private String birthDate;
    private Boolean isStudent;
    @Builder.Default
    private EStatus status = EStatus.PENDING;

}
