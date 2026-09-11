package org.example.staffskillsauditor2.skills.domain.exceptions;

public class CannotBeBlankException extends RuntimeException {
    public CannotBeBlankException(String Skill) {
        super(Skill);
    }
}
