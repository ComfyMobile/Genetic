package ru.javainside.genetic.system;

import java.util.Random;

/**
 * Author Grinch
 * Date: 08.03.2015
 * Time: 13:38
 */
public class SimpleMutation implements Mutation{
    Random r = new Random();
    double per;

    public SimpleMutation(double percents){
        per = percents;
    }

    @Override
    public void mutation(Person person) {
        Chromosome<Double> c = person.getChromosome();
        for (int i = 0; i < c.getSize(); i++){
            if (r.nextBoolean()){
                double z = r.nextBoolean()?-1.:1.;
                c.setGene(i, c.getGene(i) + r.nextDouble()*(c.getGene(i)*per/100.)*z);
            }
        }
    }
}
