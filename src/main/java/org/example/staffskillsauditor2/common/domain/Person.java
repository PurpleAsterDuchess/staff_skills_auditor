package org.example.staffskillsauditor2.common.domain;
import lombok.ToString;

@ToString(callSuper = true)
public class Person extends Entity<Person> {
    public static final String FULL_NAME_CANNOT_BE_NULL = "Full name cannot be null";
    public static final String ADDRESS_CANNOT_BE_NULL = "Address cannot be null";

    private FullName fullName;

    public Person(Identity<Person> id, FullName fullName) {
        super(id);
        updateFullName(fullName);
    }

    public final void updateFullName(FullName fullName) {
        if (fullName == null) throw new IllegalArgumentException(FULL_NAME_CANNOT_BE_NULL);
        this.fullName = new FullName(fullName);
    }


    public Identity<Person> id() { return id; }
    public FullName fullName() { return fullName; }
}