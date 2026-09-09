package org.example.staffskillsauditor2.skills.application.exceptions;

public class CannotBeBlankException extends RuntimeException {
    public CannotBeBlankException(String Skill) {
        super(Skill);
    }
}
