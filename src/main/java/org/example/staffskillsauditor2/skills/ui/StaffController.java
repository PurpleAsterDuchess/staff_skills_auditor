package org.example.staffskillsauditor2.skills.ui;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.StaffDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/staff")
@RestController
@AllArgsConstructor
public class StaffController {
    private final ContextFacade facade;

    @GetMapping("/{staff_id}")
    @ResponseStatus(HttpStatus.OK)
    public StaffDTO getStaffById(
            @PathVariable String staff_id){
        return facade.findStaffById(staff_id);
    }
}
