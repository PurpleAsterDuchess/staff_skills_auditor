package org.example.staffskillsauditor2.skills.domain.exceptions;

public class SkillNotFoundException extends RuntimeException{
    public SkillNotFoundException(String skill_id) {
        super(skill_id);
    }
}
