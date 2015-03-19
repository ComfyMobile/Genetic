package ru.javainside.genetic.testapp;

import ru.javainside.genetic.system.Chromosome;
import ru.javainside.genetic.system.FitnessFunction;
import ru.javainside.genetic.system.Person;

/**
 * Author Grinch
 * Date: 02.03.2015
 * Time: 7:27
 */
public class QuadFitness implements FitnessFunction {
    @Override
    public double getFitness(Person p) {
        Chromosome<Double> c = p.getChromosome();
        double a = c.getGene(0);
        double b = c.getGene(1);
        double d = c.getGene(2);
        double e = c.getGene(3);
        //a+bx+dx^2+ex^3=sin(x)
        double delta = 0;
        for (double x = 0; x < 1; x += 1E-2) {
            double f = a + b*x + d*x*x + e*x*x*x;
            double s = Math.sin(x);
            delta += Math.abs(f-s);
        }
        return delta;
    }
}
