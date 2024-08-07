package org.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    String validTestCase="{\"title\":\"Hello json parser\"}";
    String validAuthor="{\n" +
            "  \"authorName\": \"jhon\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"name\": \"the elegant object\",\n" +
            "      \"pages\": 100,\n" +
            "      \"creationDate\": \"2020-07-15\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    @Test
    void parse() throws JsonProcessingException {
        JsonNode node= JsonParser.parse(validTestCase);
        assertEquals(node.get("title").asText(),"Hello json parser");
    }

    @Test
    void fromJson() throws JsonProcessingException {
        Boo boo=JsonParser.fromJson(JsonParser.parse(validTestCase),Boo.class);
        assertEquals(boo.getTitle(),"Hello json parser");
    }

    @Test
    void fromJsonToAuthorPojo() throws JsonProcessingException {
        AuthorPojo authorPojo=JsonParser.fromJson(JsonParser.parse(validAuthor),AuthorPojo.class);
        System.out.println(authorPojo.getAuthorName());
    }

    @Test
    void toJsonNode() {
        Boo boo =new Boo();
        boo.setTitle("boo boo");
        JsonNode node=     JsonParser.toJsonNode(boo);
        assertEquals(node.get("title").asText(),"boo boo");

    }

    @Test
    void stringify() throws JsonProcessingException {
        Boo boo =new Boo();
        boo.setTitle("boo boo");
        JsonNode node=     JsonParser.toJsonNode(boo);

        System.out.println(JsonParser.stringify(node));
        System.out.println(JsonParser.PrettyStringify(node));
        assertEquals(JsonParser.stringify(node),"{\"title\":\"boo boo\"}");
    }
    @Test
    void prettyStringify()throws JsonProcessingException{
        Boo boo =new Boo();
        boo.setTitle("boo boo");
        JsonNode node=     JsonParser.toJsonNode(boo);
        assertEquals(JsonParser.stringify(node),"{\"title\":\"boo boo\"}");
    }
}