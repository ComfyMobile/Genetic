package ru.javainside.genetic.testapp;

import ru.javainside.genetic.system.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Grinch
 * Date: 01.03.2015
 * Time: 20:25
 */
public class Simulator {
    public static void main(String... args){
        PersonFactory personFactory = new SimpleFactory();
        List<Person> initPersons = new ArrayList<Person>();
        for (int i = 0; i < 200; i++){
            initPersons.add(personFactory.revivePerson(new SimpleMutation(1.), new QuadFitness(), new SimpleCrossover(),1.,1.,4));
        }
        Person person = initPersons.get(0);
        try {
            Populations quadPopulations = new SimplePopulations(new Population(initPersons), personFactory);
            for (int i = 0; i < 500000; i++) {
                Person bestPerson = GeneticUtils.getBestPerson(quadPopulations.getLastPopulation());
                if (bestPerson.getFitness() < person.getFitness()) {
                    person = bestPerson;
                }
                System.out.println("Population " + (i + 1) + ". Best person: " + bestPerson);
                System.out.println("Fitness mean: " + GeneticUtils.getFitnessMean(quadPopulations.getLastPopulation()));
                quadPopulations.nextGen();
            }
        }finally {
            System.out.println("Best of the best: "+person);
        }
    }

}
