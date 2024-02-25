package pl.kurs.figures.security.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String label;

    public static Role toRole(String label) {
        for (Role e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}