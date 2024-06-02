package rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Score;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/score")
public class ScoreResource {

    @Inject
    ObjectMapper objectMapper;

    @GET
    public List<Score> all() {
        return Score.listAll();
    }

    @GET
    @Path("/aggregate")
    public JsonNode aggregate() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("dateTime", LocalDateTime.now().toString());


        ArrayNode positions = objectNode.putArray("positions");

        Score.<Score>streamAll().collect(Collectors.groupingBy(score -> score.name, Collectors.averagingDouble(value -> value.position)))
            .entrySet().stream().sorted(Comparator.comparingDouble(Map.Entry::getValue))
            .forEach(entry -> positions.addObject().put(entry.getKey(), entry.getValue()));

        return objectNode;
    }




}
