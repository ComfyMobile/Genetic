package ru.javainside.genetic.system;

/**
 * Author Grinch
 * Date: 07.03.2015
 * Time: 14:19
 */
public class SimpleFactory extends PersonFactory {
    @Override
    protected Person createPerson(Object... args) {
        switch (args.length){
            case 0: return new SimplePerson();
            case 1:{
                if (args[0] instanceof Chromosome) {
                    return new SimplePerson((Chromosome)args[0]);
                }else if (args[0] instanceof Integer){
                    return new SimplePerson((Integer)args[0]);
                }
            }
            case 3: return new SimplePerson((Double)args[0], (Double)args[1], (Integer)args[2]);
            case 4: return new SimplePerson((Double)args[0], (Double)args[1], (Integer)args[2], (Boolean)args[3]);
            default: return new SimplePerson();
        }
    }
}