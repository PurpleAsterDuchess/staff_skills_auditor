package org.example.staffskillsauditor2.skills.persistance.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillJpaUnitTests {

    @Test
    void shouldSetAndGetId() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");

        assertEquals("SKILL001", skill.getId());
    }

    @Test
    void shouldSetAndGetName() {
        SkillJpa skill = new SkillJpa();

        skill.setName("Java");

        assertEquals("Java", skill.getName());
    }

    @Test
    void shouldSetAndGetDescription() {
        SkillJpa skill = new SkillJpa();

        skill.setDescription("Java programming language");

        assertEquals(
                "Java programming language",
                skill.getDescription()
        );
    }

    @Test
    void shouldSetAndGetCategory() {
        SkillJpa skill = new SkillJpa();

        skill.setCategory("Programming");

        assertEquals("Programming", skill.getCategory());
    }

    @Test
    void shouldSetAndGetActive() {
        SkillJpa skill = new SkillJpa();

        skill.setActive(true);

        assertTrue(skill.isActive());
    }

    @Test
    void shouldSetActiveToFalse() {
        SkillJpa skill = new SkillJpa();

        skill.setActive(false);

        assertFalse(skill.isActive());
    }

    @Test
    void shouldCreateSkillWithAllValues() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming language");
        skill.setCategory("Programming");
        skill.setActive(true);

        assertEquals("SKILL001", skill.getId());
        assertEquals("Java", skill.getName());
        assertEquals(
                "Java programming language",
                skill.getDescription()
        );
        assertEquals("Programming", skill.getCategory());
        assertTrue(skill.isActive());
    }

    @Test
    void shouldAllowNullValuesForObjectFields() {
        SkillJpa skill = new SkillJpa();

        skill.setId(null);
        skill.setName(null);
        skill.setDescription(null);
        skill.setCategory(null);

        assertNull(skill.getId());
        assertNull(skill.getName());
        assertNull(skill.getDescription());
        assertNull(skill.getCategory());
    }

    @Test
    void shouldDefaultActiveToFalse() {
        SkillJpa skill = new SkillJpa();

        assertFalse(skill.isActive());
    }

    @Test
    void shouldGenerateToStringContainingFieldValues() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming language");
        skill.setCategory("Programming");
        skill.setActive(true);

        String result = skill.toString();

        assertNotNull(result);
        assertTrue(result.contains("SKILL001"));
        assertTrue(result.contains("Java"));
        assertTrue(result.contains("Java programming language"));
        assertTrue(result.contains("Programming"));
        assertTrue(result.contains("true"));
    }
}