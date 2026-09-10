package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.application.exceptions.CannotBeBlankException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Skill Domain Aggregate Root Tests")
class SkillAggregateUnitTests {

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
        @DisplayName("Given valid attributes, when createNewSkill is called, then Skill is instantiated in active state")
        void test01_validSkillCreation() {
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
        @DisplayName("Given blank skill name, when createNewSkill is called, then throw CannotBeBlankException")
        void test02_blankNameThrowsException() {
            // Act & Assert
            Throwable exception = assertThrows(CannotBeBlankException.class, () ->
                    Skill.createNewSkill(Identity.of(validId), "   ", validDescription, validCategory)
            );
            assertEquals("Skill name cannot be blank", exception.getMessage());
        }

        @Test
        @DisplayName("Given blank category, when createNewSkill is called, then throw CannotBeBlankException")
        void test03_blankCategoryThrowsException() {
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
        @DisplayName("Given valid new details, when editSkill is called, then skill details are updated")
        void test04_editSkillUpdatesDetails() {
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
        @DisplayName("Given active skill, when deactivateSkill is called, then isActive becomes false")
        void test05_deactivateSkillChangesStatus() {
            // Arrange
            Skill skill = createValidSkill();
            assertTrue(skill.isActive());

            // Act
            skill.deactivateSkill();

            // Assert
            assertFalse(skill.isActive());
        }
    }
}

