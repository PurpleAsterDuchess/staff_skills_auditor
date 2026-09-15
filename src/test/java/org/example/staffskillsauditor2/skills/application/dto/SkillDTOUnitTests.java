package org.example.staffskillsauditor2.skills.application.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SkillDTO Unit Tests")
class SkillDTOUnitTests {

    @Test
    @DisplayName("Should create SkillDTO with all supplied values")
    void shouldCreateSkillDTOWithAllValues() {
        SkillDTO dto = new SkillDTO(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming",
                true
        );

        assertEquals("SKILL001", dto.id());
        assertEquals("Java", dto.name());
        assertEquals(
                "Java programming language",
                dto.description()
        );
        assertEquals("Programming", dto.category());
        assertTrue(dto.active());
    }

    @Test
    @DisplayName("Should return false when skill is inactive")
    void shouldReturnFalseWhenSkillIsInactive() {
        SkillDTO dto = new SkillDTO(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming",
                false
        );

        assertFalse(dto.active());
    }

    @Test
    @DisplayName("Should return correct skill ID")
    void shouldReturnCorrectSkillId() {
        SkillDTO dto = createSkillDTO();

        assertEquals("SKILL001", dto.id());
    }

    @Test
    @DisplayName("Should return correct skill name")
    void shouldReturnCorrectSkillName() {
        SkillDTO dto = createSkillDTO();

        assertEquals("Java", dto.name());
    }

    @Test
    @DisplayName("Should return correct skill description")
    void shouldReturnCorrectSkillDescription() {
        SkillDTO dto = createSkillDTO();

        assertEquals(
                "Java programming language",
                dto.description()
        );
    }

    @Test
    @DisplayName("Should return correct skill category")
    void shouldReturnCorrectSkillCategory() {
        SkillDTO dto = createSkillDTO();

        assertEquals("Programming", dto.category());
    }

    @Test
    @DisplayName("Should support null values")
    void shouldAllowNullValues() {
        SkillDTO dto = new SkillDTO(
                null,
                null,
                null,
                null,
                false
        );

        assertNull(dto.id());
        assertNull(dto.name());
        assertNull(dto.description());
        assertNull(dto.category());
        assertFalse(dto.active());
    }

    @Test
    @DisplayName("Should consider DTOs with the same values equal")
    void shouldConsiderDTOsWithSameValuesEqual() {
        SkillDTO first = createSkillDTO();
        SkillDTO second = createSkillDTO();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    @DisplayName("Should not consider DTOs with different values equal")
    void shouldNotConsiderDifferentDTOsEqual() {
        SkillDTO first = createSkillDTO();

        SkillDTO second = new SkillDTO(
                "SKILL002",
                "Python",
                "Python programming language",
                "Programming",
                true
        );

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("Should distinguish active and inactive skills")
    void shouldDistinguishActiveAndInactiveSkills() {
        SkillDTO activeSkill = new SkillDTO(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming",
                true
        );

        SkillDTO inactiveSkill = new SkillDTO(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming",
                false
        );

        assertNotEquals(activeSkill, inactiveSkill);
    }

    private SkillDTO createSkillDTO() {
        return new SkillDTO(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming",
                true
        );
    }
}