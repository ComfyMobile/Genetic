package ru.javainside.genetic.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author Grinch
 * Date: 06.03.2015
 * Time: 17:46
 */
public class SimplePerson extends Person{
    private int count;
    Random r = new Random();

    public SimplePerson(){}

    public SimplePerson(Chromosome chromosome){
        setChromosome(chromosome);
        count = chromosome.getSize();
    }

    public SimplePerson(int count){
        init(0,0,count,false);
    }

    public SimplePerson(double min, double max, int count){
        init(min,max,count,false);
    }

    public SimplePerson(double min, double max, int count, boolean sign){
        init(min,max,count,sign);
    }

    private void init(double min, double max, int count, boolean sign){
        List<Double> init = new ArrayList<Double>();
        this.count = count;
        boolean z = true;
        for (int i = 0; i < count; i++){
            if (sign){
                if (z){
                    init.add(new Double(r.nextDouble()*(max-min)+min));
                    z = false;
                }else{
                    init.add(new Double(-(r.nextDouble()*(max-min)+min)));
                    z = true;
                }
            }else{
                init.add(new Double(r.nextDouble()*(max-min)+min));
            }
        }
        setChromosome(new Chromosome(init));
    }

    @Override
    public void setChromosome(Chromosome chromosome) {
        super.setChromosome(chromosome);
        count = chromosome.getSize();
    }

    @Override
    public int getSeparator() {
        return r.nextInt(count-1)+1;
    }
}
