package org.JsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonParser {
    private static ObjectMapper objectMapper= getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper (){
                ObjectMapper defaultObjectMapper = new ObjectMapper();
                defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                defaultObjectMapper.registerModule(new JavaTimeModule());
                //todo object mapper configuration
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws JsonProcessingException {
     JsonNode node =   objectMapper.readTree(src);
     return node;
    }
    public static <A> A fromJson(JsonNode node,Class<A> clazz) throws JsonProcessingException {
    return   objectMapper.treeToValue(node,clazz);
    }
    public static <A> JsonNode toJsonNode(Object obj){
       return objectMapper.valueToTree(obj);
    }
    public static String stringify(JsonNode node) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        return objectWriter.writeValueAsString(node);
    }

    public static String PrettyStringify(JsonNode node) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter=objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(node);
    }
}
