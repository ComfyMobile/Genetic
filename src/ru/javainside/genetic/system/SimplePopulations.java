package ru.javainside.genetic.system;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Author Grinch
 * Date: 08.03.2015
 * Time: 13:47
 */
public class SimplePopulations extends Populations {

    public SimplePopulations(Population firstPopulation, PersonFactory factory) {
        super(firstPopulation, factory);
    }

    @Override
    public HashMap<Person,Double> getFitnessPercentage() {
        Population p = getLastPopulation();
        List<Person> persons = p.getPopulation();
        double s = 0;
        for (Person person : persons){
            s += 1./person.getFitness();
        }
        HashMap<Person,Double> percents = new HashMap<Person,Double>();
        for (Person person : p.getPopulation()){
            percents.put(person, (1./person.getFitness())/s);
        }
        return percents;
    }

    @Override
    public Pair<Person, Person> getParents() {
        Random r = new Random();
        HashMap<Person,Double> percents = getFitnessPercentage();
        Population population = getLastPopulation();
        List<Person> persons = population.getPopulation();
        //Collections.sort(persons, new PersonComparator());
        Person p1 = null;
        Person p2 = null;
        int index = r.nextInt(persons.size());
        while (p1 == null){
            Person person = persons.get(index);
            if (r.nextDouble() < percents.get(person)){
                p1 = person;
            }
            index = r.nextInt(persons.size());
        }
        index = r.nextInt(persons.size());
        while (p2 == null){
            Person person = persons.get(index);
            if (person != p1 && r.nextDouble() < percents.get(person)){
                p2 = person;
            }
            index = r.nextInt(persons.size());
        }
        return new Pair<Person, Person>(p1,p2);
    }

    @Override
    public void nextGen() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < getLastPopulation().getPopulation().size(); i++){
            Pair<Person,Person> pair = getParents();
            Person newPerson = pair.getKey().reproduction(pair.getValue(), getFactory());
            persons.add(newPerson);
        }
        addPopulation(new Population(persons));
    }
}
