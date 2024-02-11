package pl.kurs.figures.security.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    USER("user"),
    ADMIN("admin");

    private final String label;

    public static Role toRole(String label) {
        for (Role e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
