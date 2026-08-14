package org.example.staffskillsauditor2.skills.ui;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.StaffDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/staff")
@RestController
@AllArgsConstructor
public class StaffController {
    private final ContextFacade facade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<StaffDTO> getAllStaffMembers() { return facade.findAllStaffMembers();}
}
