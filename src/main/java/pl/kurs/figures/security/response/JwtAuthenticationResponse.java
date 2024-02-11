package pl.kurs.figures.security.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtAuthenticationResponse {

    private String token;

}
