package com.yagmur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthSaveRequestDto {
    String email;
    String password;
    String name;
    String surname;
    String phoneNumber;
    String birthDate;
    Boolean isStudent;

}
