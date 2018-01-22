import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSerializer {
    public static void main(String[] args) {

        JsonSerializer jsonSerializer = new JsonSerializer();
        Dog dogToSerialize = new Dog(15, new BasicInformation("perrete", 2));

        final String filePath = "dog.json";
        jsonSerializer.SerializeDog(dogToSerialize, filePath);

        Dog dogDeserialized = jsonSerializer.DeserializeDog(filePath);

        System.out.println(dogToSerialize.equals(dogDeserialized)?"Los perros son iguales":"Ha habido un error, son distintos");
    }

    private Dog DeserializeDog(String json) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(json), Dog.class);
        } catch (IOException e) {
            System.out.println("Error leyendo del json");
            System.out.println(e.getMessage());
        }
        return Dog.PerroInvalido;
    }

    private void SerializeDog(Dog dog, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), dog);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            System.out.println("Error serializando");
        } catch (JsonMappingException e) {
            e.printStackTrace();
            System.out.println("Error serializando");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creando archivo");
        }
    }
}
