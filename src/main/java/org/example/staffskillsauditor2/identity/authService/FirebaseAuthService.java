package org.example.staffskillsauditor2.identity.authService;

import org.example.staffskillsauditor2.identity.dto.LoginResponse;
import org.example.staffskillsauditor2.identity.security.Role;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;

@Service
@Slf4j
public class FirebaseAuthService {
    private final FirebaseAuth firebaseAuth;
    private final RestClient restClient;

    @Value("${firebase.web-api-key}")
    private String firebaseApiKey;

    public FirebaseAuthService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
        this.restClient = RestClient.create();
    }

    public UserRecord registerUser(String username,
                                   String email,
                                   String password,
                                   String role) throws Exception {
        CreateRequest createRequest = new CreateRequest().setEmail(email)
                                                        .setPassword(password)
                                                        .setDisplayName(username)
                                                        .setEmailVerified(false);

        UserRecord userRecord = firebaseAuth.createUser(createRequest);

        // Confirm role passed in request exists
        String confirmedRole = role != null
                                    ? Role.fromString(role).getAuthority()
                                    : Role.USER.name();

        // Custom claims
        Map<String, Object> customClaims = Map.of(
                "role", confirmedRole,
                "admin", false
        );

        firebaseAuth.setCustomUserClaims(userRecord.getUid(), customClaims);
        return userRecord;
    }

    public LoginResponse loginUser(String email,
                                   String password) {
        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Email and password must not be empty");
        }

        Map<String, Object> requestBody = Map.of(
                "email", email,
                "password", password,
                "returnSecureToken", true
        );

        String firebaseLoginUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + firebaseApiKey;

        try {
            return restClient.post()
                            .uri(firebaseLoginUrl)
                            .body(requestBody)
                            .retrieve()
                            .body(LoginResponse.class);
        } catch (RestClientResponseException e) {
            log.error("Firebase Auth error [{}] {}",
                        e.getStatusCode(),
                        e.getResponseBodyAsString());
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}