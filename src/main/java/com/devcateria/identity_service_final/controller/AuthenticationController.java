package com.devcateria.identity_service_final.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcateria.identity_service_final.dto.request.AuthenticationRequest;
import com.devcateria.identity_service_final.dto.request.IntroSpectRequest;
import com.devcateria.identity_service_final.dto.request.LogoutRequest;
import com.devcateria.identity_service_final.dto.request.RefreshRequest;
import com.devcateria.identity_service_final.dto.response.ApiResponse;
import com.devcateria.identity_service_final.dto.response.AuthenticationResponse;
import com.devcateria.identity_service_final.dto.response.InstroSpecResponse;
import com.devcateria.identity_service_final.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
            throws KeyLengthException {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<InstroSpecResponse> authenticate(@RequestBody IntroSpectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.instrospect(request);
        return ApiResponse.<InstroSpecResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws JOSEException, ParseException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }
}
