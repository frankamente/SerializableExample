
import java.util.Objects;

public class Dog {

    public static Dog PerroInvalido = new Dog();

    private int weight;

    private BasicInformation basicInformation;

    public Dog() {
    }

    public Dog(int weight, BasicInformation basicInformation) {
        this.weight = weight;
        this.basicInformation = basicInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return weight == dog.weight &&
                Objects.equals(basicInformation, dog.basicInformation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weight, basicInformation);
    }

    public int getWeight() {
        return weight;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }
}
