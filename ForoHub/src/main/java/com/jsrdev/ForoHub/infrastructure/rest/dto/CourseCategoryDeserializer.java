package com.jsrdev.ForoHub.infrastructure.rest.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jsrdev.ForoHub.common.CourseCategory;

import java.io.IOException;

public class CourseCategoryDeserializer extends JsonDeserializer<CourseCategory> {

    @Override
    public CourseCategory deserialize(JsonParser p, DeserializationContext context) throws IOException {
        String value = p.getText();
        CourseCategory category = CourseCategory.fromString(value);
        if (category == null) {
            throw new IllegalArgumentException("Invalid category: " + value);
        }
        return category;
    }
}
