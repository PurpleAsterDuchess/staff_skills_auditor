package org.example.staffskillsauditor2.skills.ui.controllers;

import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.staff.application.dto.StaffDTO;
import org.example.staffskillsauditor2.staff.ui.commands.RegisterStaffMemberCommand;
import org.example.staffskillsauditor2.staff.ui.commands.UpdateStaffDetailsCommand;
import org.example.staffskillsauditor2.staff.ui.controllers.StaffController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("StaffController Unit Tests")
class StaffControllerUnitTests {

    @Mock
    private ContextFacade facade;

    @InjectMocks
    private StaffController controller;

    private StaffDTO staff;

    @BeforeEach
    void setUp() {
        staff = new StaffDTO(
                "STAFF002",
                "Jane",
                "Doe",
                "jane.doe@example.com",
                LocalDate.of(2022, 3, 15),
                "IT",
                "MANAGER002",
                "STAFF",
                LocalDate.of(2022, 3, 15),
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );
    }

    @Test
    @DisplayName("Should get staff member by ID")
    void shouldGetStaffById() {
        when(facade.findStaffById("STAFF002"))
                .thenReturn(staff);

        StaffDTO result = controller.getStaffById("STAFF002");

        assertNotNull(result);
        assertEquals(staff, result);

        verify(facade).findStaffById("STAFF002");
    }

    @Test
    @DisplayName("Should return the exact staff member returned by the facade")
    void shouldReturnExactStaffFromFacade() {
        when(facade.findStaffById("STAFF002"))
                .thenReturn(staff);

        StaffDTO result = controller.getStaffById("STAFF002");

        assertSame(staff, result);

        verify(facade).findStaffById("STAFF002");
    }

//    @Test
//    @DisplayName("Should register a staff member")
//    void shouldRegisterStaffMember() {
//        RegisterStaffMemberCommand command = mock(
//                RegisterStaffMemberCommand.class
//        );
//
//        when(facade.registerStaffMember(command))
//                .thenReturn("STAFF002");
//
//        StaffDTO result = controller.registerStaffMember(command);
//
//        assertEquals("STAFF002", result);
//
//        verify(facade).registerStaffMember(command);
//    }
//
//    @Test
//    @DisplayName("Should return registered staff member ID")
//    void shouldReturnRegisteredStaffMemberId() {
//        RegisterStaffMemberCommand command = mock(
//                RegisterStaffMemberCommand.class
//        );
//
//        when(facade.registerStaffMember(command))
//                .thenReturn("STAFF002");
//
//        StaffDTO result = controller.registerStaffMember(command);
//
//        assertNotNull(result);
//        assertEquals("STAFF002", result);
//    }

    @Test
    @DisplayName("Should update staff details")
    void shouldUpdateStaffDetails() {
        UpdateStaffDetailsCommand command = mock(
                UpdateStaffDetailsCommand.class
        );

        controller.patchStaffMember(
                "STAFF002",
                command
        );

        verify(facade).updateStaffDetails(
                "STAFF002",
                command
        );
    }

    @Test
    @DisplayName("Should pass the correct staff ID when updating staff details")
    void shouldPassCorrectStaffIdWhenUpdating() {
        UpdateStaffDetailsCommand command = mock(
                UpdateStaffDetailsCommand.class
        );

        controller.patchStaffMember(
                "STAFF002",
                command
        );

        verify(facade).updateStaffDetails(
                eq("STAFF002"),
                same(command)
        );
    }

    @Test
    @DisplayName("Should only call findStaffById when getting a staff member")
    void shouldOnlyCallFindStaffById() {
        when(facade.findStaffById("STAFF002"))
                .thenReturn(staff);

        controller.getStaffById("STAFF002");

        verify(facade).findStaffById("STAFF002");
        verifyNoMoreInteractions(facade);
    }

    @Test
    @DisplayName("Should handle null staff ID")
    void shouldHandleNullStaffId() {
        when(facade.findStaffById(null))
                .thenReturn(null);

        StaffDTO result = controller.getStaffById(null);

        assertNull(result);

        verify(facade).findStaffById(null);
    }
}