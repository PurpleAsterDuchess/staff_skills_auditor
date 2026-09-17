package org.example.staffskillsauditor2.skills.persistance.repositories;

import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SkillRepositoryIntegrationTests {

    @Autowired
    private SkillRepository skillRepository;

    @Test
    void shouldSaveAndFindSkill() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming");
        skill.setCategory("Programming");
        skill.setActive(true);

        skillRepository.save(skill);

        Optional<SkillJpa> result =
                skillRepository.findById("SKILL001");

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo("SKILL001");
        assertThat(result.get().getName()).isEqualTo("Java");
        assertThat(result.get().getDescription())
                .isEqualTo("Java programming");
        assertThat(result.get().getCategory())
                .isEqualTo("Programming");
        assertThat(result.get().isActive()).isTrue();
    }

    @Test
    void shouldReturnEmptyWhenSkillDoesNotExist() {
        Optional<SkillJpa> result =
                skillRepository.findById("DOES_NOT_EXIST");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindAllSavedSkills() {
        SkillJpa java = new SkillJpa();
        java.setId("SKILL001");
        java.setName("Java");
        java.setDescription("Java programming");
        java.setCategory("Programming");
        java.setActive(true);

        SkillJpa spring = new SkillJpa();
        spring.setId("SKILL002");
        spring.setName("Spring");
        spring.setDescription("Spring Framework");
        spring.setCategory("Programming");
        spring.setActive(true);

        skillRepository.save(java);
        skillRepository.save(spring);

        Iterable<SkillJpa> result =
                skillRepository.findAll();

        assertThat(result)
                .extracting(SkillJpa::getId)
                .contains("SKILL001", "SKILL002");
    }

    @Test
    void shouldUpdateExistingSkill() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming");
        skill.setCategory("Programming");
        skill.setActive(true);

        skillRepository.save(skill);

        skill.setName("Advanced Java");
        skill.setDescription("Advanced Java programming");
        skill.setCategory("Software Development");

        skillRepository.save(skill);

        SkillJpa result =
                skillRepository.findById("SKILL001")
                        .orElseThrow();

        assertThat(result.getName())
                .isEqualTo("Advanced Java");
        assertThat(result.getDescription())
                .isEqualTo("Advanced Java programming");
        assertThat(result.getCategory())
                .isEqualTo("Software Development");
    }

    @Test
    void shouldDeleteSkill() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming");
        skill.setCategory("Programming");
        skill.setActive(true);

        skillRepository.save(skill);

        skillRepository.deleteById("SKILL001");

        assertThat(skillRepository.findById("SKILL001"))
                .isEmpty();
    }

    @Test
    void shouldPersistInactiveSkill() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming");
        skill.setCategory("Programming");
        skill.setActive(false);

        skillRepository.save(skill);

        SkillJpa result =
                skillRepository.findById("SKILL001")
                        .orElseThrow();

        assertThat(result.isActive()).isFalse();
    }
}