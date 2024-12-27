package com.yagmur.controller;


import com.yagmur.dto.request.UserSaveRequestDto;
import com.yagmur.dto.response.UserSaveResponseDto;
import com.yagmur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yagmur.constans.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {

    private final UserService userService;

    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<UserSaveResponseDto> register(@RequestBody UserSaveRequestDto dto) {
        return ResponseEntity.ok(userService.saveUser(dto));
    }

}
