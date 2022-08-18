package co.juan;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class FruitService{

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    {fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));}

    public Set<Fruit> getFruits() {
        return fruits;
    }

    public void add (Fruit fruit){
        getFruits().add(fruit);
    }

    public void delete (Fruit fruit){
        getFruits().removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
    }
}
