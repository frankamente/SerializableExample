import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonSerializer {

    private ObjectMapper objectMapper;

    public JsonSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    public static void main(String[] args) {

        JsonSerializer jsonSerializer = new JsonSerializer();
        Dog dogToSerialize = new Dog(15, new BasicInformation("perrete", 2));

        final String filePath = "dog.json";
        jsonSerializer.serializeDogUsingFile(dogToSerialize, filePath);

        Dog dogDeserialized = jsonSerializer.deserializeDogUsingFile(filePath);

        System.out.println(dogToSerialize.equals(dogDeserialized) ? "Los perros son iguales usando Archivo" : "Ha habido un error, son distintos usando archivos");

        String dogInString = jsonSerializer.serializeDog(dogToSerialize);

        dogDeserialized = jsonSerializer.deserializeDogUsingString(dogInString);

        System.out.println(dogToSerialize.equals(dogDeserialized) ? "Los perros son iguales usando String" : "Ha habido un error, son distintos usando string");
    }

    private Dog deserializeDogUsingFile(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), Dog.class);
        } catch (IOException e) {
            System.out.println("Error abriendo el fichero");
            System.out.println(e.getMessage());
        }
        return Dog.PerroInvalido;
    }

    private void serializeDogUsingFile(Dog dog, String filePath) {

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

    private String serializeDog(Dog dog) {

        try {
            return objectMapper.writeValueAsString(dog);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private Dog deserializeDogUsingString(String dogInString) {
        try {
            return objectMapper.readValue(dogInString, Dog.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Dog.PerroInvalido;
    }
}
