package com.yagmur.controller;


import com.yagmur.dto.request.AuthSaveRequestDto;
import com.yagmur.dto.request.LoginRequestDto;
import com.yagmur.dto.response.BasicResponse;
import com.yagmur.entity.Auth;
import com.yagmur.service.AuthService;
import com.yagmur.utility.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yagmur.constans.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)

public class AuthController {

    private final AuthService authService;

    
    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Auth>> register(@RequestBody AuthSaveRequestDto authSaveRequestDto){
        return ResponseEntity.ok(BasicResponse.<Auth>builder().
                status(200)
                .message("Kayıt Başarılı.")
                .data(authService.register(authSaveRequestDto))
                .build());
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<String >> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(BasicResponse.<String>builder()
                .status(200)
                .message("Giriş Başarılı Tokenınız Oluşturuldu.")
                .data(authService.login(loginRequestDto))
                .build());
    }

    @PostMapping(ROLE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<ERole>> getRoleFromToken(@RequestHeader("Authorization") String authorizationHeader) {
        // Authorization başlığını konsolda kontrol edin
        System.out.println("Authorization Header: " + authorizationHeader);

        // "Bearer" kısmını çıkararak sadece token'i alıyoruz
        String token = authorizationHeader.replace("Bearer ", "");
        ERole role = authService.getRoleFromToken(token);

        System.out.println("Extracted Role: " + role);

        return ResponseEntity.ok(BasicResponse.<ERole>builder()
                .status(200)
                .message("Giriş Başarılı Rol Alındı.")
                .data(role)
                .build());
    }



}
