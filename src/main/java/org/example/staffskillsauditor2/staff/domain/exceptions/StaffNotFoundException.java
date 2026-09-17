package org.example.staffskillsauditor2.staff.domain.exceptions;

public class StaffNotFoundException extends RuntimeException{
    public StaffNotFoundException(String staff_id) {
        super(staff_id);
    }
}
