package com.jsrdev.ForoHub.common;

import java.text.Normalizer;
import java.util.Arrays;

public enum TopicStatus {
    OPEN("Open", "Abierto"),
    CLOSED("Closed", "Cerrado"),
    RESOLVED("Resolved", "Resuelto"),
    PENDING("Pending", "En espera");

    private final String statusEng;
    private final String statusEsp;

    TopicStatus(String statusEng, String statusEsp) {
        this.statusEng = statusEng;
        this.statusEsp = statusEsp;
    }

    public String getStatusEng() {
        return statusEng;
    }

    public String getStatusEsp() {
        return statusEsp;
    }

    public static TopicStatus fromString(String value) {
        if (value == null) return null;
        String normalizedValue = normalize(value);
        return Arrays.stream(values())
                .filter(status -> normalize(status.statusEng).equalsIgnoreCase(normalizedValue)
                        || normalize(status.statusEsp).equalsIgnoreCase(normalizedValue))
                .findFirst()
                .orElse(null);
    }

    private static String normalize(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
}

