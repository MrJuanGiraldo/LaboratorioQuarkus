package co.juan;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LegumeService {

    private Set<Legume> legumes = Collections.synchronizedSet(new LinkedHashSet<>());

    {legumes.add(new Legume("Carrot", "Root vegetable, usually orange"));
        legumes.add(new Legume("Zucchini", "Summer squash"));}

    public Set<Legume> getLegumes() {
        return legumes;
    }

    public void add (Legume legume){
        getLegumes().add(legume);
    }

    public void delete (Legume legume){
        getLegumes().removeIf(existingLegume -> existingLegume.name.contentEquals(legume.name));
    }
}