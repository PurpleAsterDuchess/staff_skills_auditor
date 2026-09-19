package org.example.staffskillsauditor2.identity.security;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.staff.persistance.entities.StaffJpa;
import org.example.staffskillsauditor2.staff.persistance.repositories.repositories.StaffRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorisationService {

    private final StaffRepository staffRepository;

    public boolean canEditOwnPortfolio(
            Authentication authentication,
            String staffId
    ) {
        String currentStaffId = getCurrentStaffId(authentication);

        return currentStaffId.equals(staffId);
    }

    public boolean canManagerEditPortfolio(
            Authentication authentication,
            String staffId
    ) {
        String currentStaffId = getCurrentStaffId(authentication);

        StaffJpa staff = staffRepository.findById(staffId)
                .orElse(null);

        if (staff == null) {
            return false;
        }

        return currentStaffId.equals(staff.getLineManagerId());
    }

    public boolean canEditPortfolio(
            Authentication authentication,
            String staffId
    ) {
        String role = authentication.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .findFirst()
                .orElse("");

        if ("ROLE_STAFF".equals(role)) {
            return canEditOwnPortfolio(authentication, staffId);
        }

        if ("ROLE_MANAGER".equals(role)) {
            return canManagerEditPortfolio(authentication, staffId);
        }

        return false;
    }

    private String getCurrentStaffId(Authentication authentication) {
        return authentication.getName();
    }
}