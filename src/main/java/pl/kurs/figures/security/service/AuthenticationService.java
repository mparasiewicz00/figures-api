package pl.kurs.figures.security.service;

import pl.kurs.figures.security.request.SignInRequest;
import pl.kurs.figures.security.request.SignUpRequest;
import pl.kurs.figures.security.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);

}
