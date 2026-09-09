package org.example.staffskillsauditor2.common;

import org.example.staffskillsauditor2.common.domain.FullName;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.common.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Person entity unit tests")
public class PersonTests {
    private Identity<Person> identity;
    private FullName fullName;

    @BeforeEach
    void setUp() {
        identity = Identity.of("12345678-1234-1234-1234-123456789012");
        fullName = new FullName("first", "surname");
    }

    private Person createValidPerson(){
        return new Person(identity, fullName);
    }

    @Test
    @DisplayName("You can create a Person when all arguments are valid")
    void objectCreatedWithValidDetails() {
        assertDoesNotThrow( () -> new Person(identity, fullName));
    }

    @Test
    @DisplayName("You cannot create a Person if the id is null")
    void nullIdIsRejected() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Person(null, fullName)
        );
        assertEquals(Person.IDENTITY_CANNOT_BE_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("You cannot create a Person if the full name is null")
    void nullFullNameIsRejected() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Person(identity, null)
        );
        assertEquals(Person.FULL_NAME_CANNOT_BE_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("You can update a full name if that new full name is valid")
    void validFullNameIsAcceptedForUpdate() {
        Person person = createValidPerson();
        FullName newFullName = new FullName("first2", "surname2");

        assertDoesNotThrow(() -> person.updateFullName(newFullName));
    }

    @Test
    @DisplayName("You cannot amend the full name if that new full name is null")
    void nullNewFullNameIsRejectedForUpdate() {
        Person person = createValidPerson();

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                person.updateFullName(null)
        );
        assertEquals(Person.FULL_NAME_CANNOT_BE_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("Two Persons with the same id are considered equal")
    void twoPersonsAreEqualWithTheSameId() {
        Person person1 = createValidPerson();
        FullName differentName = new FullName("first2", "surname2");

        Person person2 = new Person(identity, differentName);

        assertEquals(person1, person2); // id's are the same so entities should be equal
    }
}
