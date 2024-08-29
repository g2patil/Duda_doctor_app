package service;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RoleSetDeserializer extends JsonDeserializer<Set<String>> {
    @Override
    public Set<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String[] roles = jsonParser.readValueAs(String[].class);
        Set<String> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(role);
        }
        return roleSet;
    }
}
