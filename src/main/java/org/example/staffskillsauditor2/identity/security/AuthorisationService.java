package org.example.staffskillsauditor2.identity.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.example.staffskillsauditor2.staff.persistance.entities.StaffJpa;
import org.example.staffskillsauditor2.staff.persistance.repositories.repositories.StaffRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("authorisationService")
@RequiredArgsConstructor
public class AuthorisationService {

    private final FirebaseAuth firebaseAuth;
    private final StaffRepository staffRepository;

    public boolean canEditPortfolio(
            Authentication authentication,
            String staffId
    ) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (staffId == null || staffId.isBlank()) {
            return false;
        }

        try {
            String firebaseUid = authentication.getName();

            if (firebaseUid == null || firebaseUid.isBlank()) {
                return false;
            }

            String firebaseEmail = firebaseAuth.getUser(firebaseUid).getEmail();

            if (firebaseEmail == null || firebaseEmail.isBlank()) {
                return false;
            }

            StaffJpa staff = staffRepository.findById(staffId).orElse(null);

            if (staff == null || staff.getEmail() == null) {
                return false;
            }

            return firebaseEmail.trim().equalsIgnoreCase(staff.getEmail().trim());

        } catch (FirebaseAuthException | RuntimeException e) {
            return false;
        }
    }
}