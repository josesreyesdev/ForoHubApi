package com.jsrdev.ForoHub.common;

import java.text.Normalizer;
import java.util.Arrays;

public enum CourseCategory {
    SCIENCE("Science", "Ciencia"),
    MATHEMATICS("Mathematics", "Matemáticas"),
    PHYSICS("Physics", "Física"),
    CHEMISTRY("Chemistry", "Química"),
    BIOLOGY("Biology", "Biología"),
    TECHNOLOGY("Technology", "Tecnología"),
    PROGRAMMING("Programming", "Programación"),
    WEB_DEVELOPMENT("Web Development", "Desarrollo Web"),
    DATA_SCIENCE("Data Science", "Ciencia de Datos"),
    CYBER_SECURITY("Cyber Security", "Ciberseguridad"),
    ART("Art", "Arte"),
    MUSIC("Music", "Música"),
    LITERATURE("Literature", "Literatura"),
    HISTORY("History", "Historia"),
    PHILOSOPHY("Philosophy", "Filosofía"),
    BUSINESS("Business", "Negocios"),
    MARKETING("Marketing", "Mercadotecnia"),
    FINANCE("Finance", "Finanzas"),
    ACCOUNTING("Accounting", "Contabilidad"),
    ENTREPRENEURSHIP("Entrepreneurship", "Emprendimiento"),
    HEALTH("Health", "Salud"),
    FITNESS("Fitness", "Ejercicio"),
    NUTRITION("Nutrition", "Nutrición"),
    YOGA("Yoga", "Yoga"),
    PERSONAL_DEVELOPMENT("Personal Development", "Desarrollo Personal"),
    LANGUAGES("Languages", "Idiomas"),
    PHOTOGRAPHY("Photography", "Fotografía"),
    GRAPHIC_DESIGN("Graphic Design", "Diseño Gráfico"),
    COOKING("Cooking", "Cocina"),
    DIY("DIY", "Hazlo tú mismo");

    private final String categoryEng;
    private final String categoryEsp;

    CourseCategory(String categoryEng, String categoryEsp) {
        this.categoryEng = categoryEng;
        this.categoryEsp = categoryEsp;
    }

    public String getCategoryEng() {
        return categoryEng;
    }

    public String getCategoryEsp() {
        return categoryEsp;
    }

    public static CourseCategory fromString(String value) {
        if (value == null) return null;
        String normalizedValue = normalize(value);
        return Arrays.stream(values())
                .filter(category -> normalize(category.categoryEng).equalsIgnoreCase(normalizedValue)
                        || normalize(category.categoryEsp).equalsIgnoreCase(normalizedValue))
                .findFirst()
                .orElse(null);
    }

    private static String normalize(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
}
