package ru.javainside.genetic.system;

import java.util.List;
import java.util.Random;

/**
 * Author Grinch
 * Date: 02.03.2015
 * Time: 17:14
 */
public class SimpleCrossover implements Crossover {

    @Override
    public Person crossover(Person parent1, Person parent2, PersonFactory factory) {
        Random r = new Random();
        Chromosome<Double> chromosome = new Chromosome<Double>(parent1.chromosome.chromosome);
        int separator = parent1.getSeparator();
        if (r.nextBoolean()){
            List<Double> leftPart = parent1.getChromosome().getLeftPart(separator);
            List<Double> rightPart = parent2.getChromosome().getRightPart(separator);
            for (int i = 0; i < separator; i++){
                chromosome.setGene(i, leftPart.get(i));
            }
            for (int i = 0; i < rightPart.size(); i++){
                chromosome.setGene(i + separator, rightPart.get(i));
            }
        }else{
            List<Double> leftPart = parent2.getChromosome().getLeftPart(separator);
            List<Double> rightPart = parent1.getChromosome().getRightPart(separator);
            for (int i = 0; i < separator; i++){
                chromosome.setGene(i, leftPart.get(i));
            }
            for (int i = 0; i < rightPart.size(); i++){
                chromosome.setGene(i + separator, rightPart.get(i));
            }
        }

        Person newPerson = factory.revivePerson(parent2.getMutation(), parent1.getFitnessFunction(), parent1.getCrossover(),chromosome);

        return newPerson;
    }
}
