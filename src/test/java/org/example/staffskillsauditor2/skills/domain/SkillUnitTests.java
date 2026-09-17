package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.domain.exceptions.CannotBeBlankException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Skill Domain Aggregate Root Tests")
class SkillUnitTests {

    private String validId;
    private String validName;
    private String validDescription;
    private String validCategory;

    @BeforeEach
    void setUp() {
        // Arrange
        validId = "SKL100";
        validName = "Spring Boot Microservices";
        validDescription = "Building cloud-native microservices with Spring Boot and Spring Cloud.";
        validCategory = "Software Engineering";
    }

    private Skill createValidSkill() {
        return Skill.createNewSkill(Identity.of(validId), validName, validDescription, validCategory);
    }

    @Nested
    @DisplayName("Creation and Invariants")
    class CreationTests {

        @Test
        @DisplayName("Should instantiate skill to an active state when createNewSkill is called with valid attributes")
        void validSkillCreation() {
            // Act
            Skill skill = createValidSkill();

            // Assert
            assertNotNull(skill);
            assertEquals(validId, skill.id().id());
            assertEquals(validName, skill.name());
            assertEquals(validDescription, skill.description());
            assertEquals(validCategory, skill.category());
            assertTrue(skill.isActive());
        }

        @Test
        @DisplayName("Should throw CannotBeBlankException when createNewSkill is called with a blank skill name")
        void blankNameThrowsException() {
            // Act & Assert
            Throwable exception = assertThrows(CannotBeBlankException.class, () ->
                    Skill.createNewSkill(Identity.of(validId), "   ", validDescription, validCategory)
            );
            assertEquals("Skill name cannot be blank", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw CannotBeBlankException when createNewSkill is called with a blank category")
        void blankCategoryThrowsException() {
            // Act & Assert
            Throwable exception = assertThrows(CannotBeBlankException.class, () ->
                    Skill.createNewSkill(Identity.of(validId), validName, validDescription, "")
            );
            assertEquals("Skill category cannot be blank", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Domain Behaviors (Edit & Deactivate)")
    class BehaviorTests {

        @Test
        @DisplayName("Should update skill details when editSkill is called with valid new details")
        void editSkillUpdatesDetails() {
            // Arrange
            Skill skill = createValidSkill();
            String newName = "Spring Boot 3 Advanced";
            String newDesc = "Updated description with Spring 6 features.";
            String newCat = "Backend Development";

            // Act
            skill.editSkill(newName, newDesc, newCat);

            // Assert
            assertEquals(newName, skill.name());
            assertEquals(newDesc, skill.description());
            assertEquals(newCat, skill.category());
        }
        @Test
        @DisplayName("Should make isActive false when deactivateSkill is given an active skill")
        void deactivateSkillChangesStatus() {
            // Arrange
            Skill skill = createValidSkill();
            assertTrue(skill.isActive());

            // Act
            skill.deactivateSkill();

            // Assert
            assertFalse(skill.isActive());
        }
    }

    @Test
    @DisplayName("Should make isActive true when activateSkill is given an active skill")
    void activateSkillChangesStatus() {
        // Arrange
        Skill skill = createValidSkill();
        assertTrue(skill.isActive());

        // Act
        skill.activateSkill();

        // Assert
        assertTrue(skill.isActive());
    }
}

