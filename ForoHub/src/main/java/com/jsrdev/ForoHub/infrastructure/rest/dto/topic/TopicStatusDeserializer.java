package com.jsrdev.ForoHub.infrastructure.rest.dto.topic;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jsrdev.ForoHub.common.TopicStatus;

import java.io.IOException;

public class TopicStatusDeserializer extends JsonDeserializer<TopicStatus> {

    @Override
    public TopicStatus deserialize(JsonParser p, DeserializationContext context) throws IOException {
        String value = p.getText();
        TopicStatus status = TopicStatus.fromString(value);
        if (status == null) {
            throw new IllegalArgumentException("Invalid status: " + value);
        }
        return status;
    }
}
