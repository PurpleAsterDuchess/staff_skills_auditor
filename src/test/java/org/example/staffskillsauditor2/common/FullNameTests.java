package org.example.staffskillsauditor2.common;

import org.example.staffskillsauditor2.common.domain.FullName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FullName value object unit tests")
public class FullNameTests {
    private static final String VALID_FIRST_NAME = "firstname1";
    private static final String VALID_SURNAME = "surname1";
    private FullName createValidFullName(){
        return new FullName(VALID_FIRST_NAME, VALID_SURNAME);
    }

    private String createTextOfLength(int length){
        char[] chars = new char[length];
        Arrays.fill(chars, 'a');
        return new String(chars);
    }

    @Test
    @DisplayName("Full names are considered the same when all fields are the same")
    void fullNamesAreEqualWhenAllOfTheirFieldsAreTheSame(){
        FullName fullName1 = createValidFullName();
        FullName fullName2 = createValidFullName();
        // Act and Assert
        assertEquals(fullName1,fullName2);
    }

    @Test
    @DisplayName("A full name requires a non-blank surname to be valid")
    void blankSurnamesAreRejected(){
        assertThatThrownBy(() -> new FullName(VALID_FIRST_NAME, ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(FullName.SURNAME_NOT_EMPTY);
    }

    @Test
    @DisplayName("A full name requires a non-null surname to be valid")
    void blankFirstNamesAreRejected(){
        assertThatThrownBy(() -> new FullName(VALID_FIRST_NAME, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(FullName.SURNAME_NOT_EMPTY);
    }

    @Test
    @DisplayName("A surname can be up to a specified maximum number of characters in length")
    void surnameMustNotExceedTheSpecifiedMaxLength(){
        assertThatNoException().isThrownBy(() ->
                new FullName(VALID_FIRST_NAME, createTextOfLength(FullName.MAX_SURNAME_LENGTH))
        );
    }

    @Test
    @DisplayName("A surname exceeding the specified number of characters is rejected")
    void surnamesExceedingTheSpecifiedMaxLengthIsRejected(){
        assertThatThrownBy(() -> new FullName(VALID_FIRST_NAME, createTextOfLength(FullName.MAX_SURNAME_LENGTH + 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(FullName.SURNAME_LENGTH);
    }

    //same tests for firstname1
}

