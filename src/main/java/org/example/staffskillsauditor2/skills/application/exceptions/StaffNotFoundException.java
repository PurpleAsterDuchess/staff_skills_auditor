package org.example.staffskillsauditor2.skills.application.exceptions;

public class StaffNotFoundException extends RuntimeException{
    public StaffNotFoundException(String staff_id) {
        super(staff_id);
    }
}
