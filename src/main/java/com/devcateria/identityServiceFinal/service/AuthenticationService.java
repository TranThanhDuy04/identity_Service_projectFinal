package com.devcateria.identityServiceFinal.service;

import com.devcateria.identityServiceFinal.dto.request.AuthenticationRequest;
import com.devcateria.identityServiceFinal.dto.request.IntroSpectRequest;
import com.devcateria.identityServiceFinal.dto.response.AuthenticationResponse;
import com.devcateria.identityServiceFinal.dto.response.InstroSpecResponse;
import com.devcateria.identityServiceFinal.exception.AppExeption;
import com.devcateria.identityServiceFinal.exception.ErrorCode;
import com.devcateria.identityServiceFinal.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;



    public InstroSpecResponse instrospect(IntroSpectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT jwt = SignedJWT.parse(token);

        Date expityTime = jwt.getJWTClaimsSet().getExpirationTime();

        var verified = jwt.verify(verifier);

        return InstroSpecResponse.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws KeyLengthException {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppExeption(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(request.getPassword(),
                user.getPassword());

        if(!authenticated)
            throw new AppExeption(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .Authenticated(true)
                .build();
    }

    private String generateToken(String username) throws KeyLengthException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("devcateria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim","custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (Exception e){
            log.error("Cannot create Token");
            throw new RuntimeException(e);
        }

    }
}
