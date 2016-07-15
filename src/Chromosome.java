package geneticalgorithm;
import java.util.Random;

public class Chromosome {

    static final int GENENUMB = 10; /* Current maximum length of Chromosomes */
    byte[] gene; /* Chromosome */
    int fitness; /* Fitness Value */
    /*Constructor*/
    public Chromosome(){
        gene =  new byte[GENENUMB];
        fitness = 0;
    }
    /*Initialize Random Chromosome */
    public void randomChromosome(){
        Random r = new Random();
        double randomBit;
         for(int j = 0; j < 10; j++){
                randomBit = r.nextDouble();
                if(randomBit < 0.5){
                    gene[j] = 1;
                    fitness++;
             }
                else
                    gene[j] = 0;
         }
    }
    /* Copy Constructor */
    public Chromosome(Chromosome originalChromosome){
        gene = new byte[GENENUMB];
        for(int i = 0; i < GENENUMB;i++)
            this.gene[i] = originalChromosome.gene[i];
        this.fitness = originalChromosome.fitness;
    }
    /* Set new Fitness Value to Chromsome */
    public void setFitness(){
        fitness = 0;
        for(int x = 0; x < gene.length; x++)
            if(gene[x] == 1) /*Each Value 1 increases Fitness */
                fitness++;
    }
    /* Compare Chromomoes */
    public boolean matchGene(Chromosome c2){
        for(int i = 0; i < 10; i++)
            if(gene[i] != c2.gene[i])
                return false;
        return true;
    }
    /*Print Chromosomes gene field */
    public void printDetails(){
        for(int i = 0; i < GENENUMB; i++)
            System.out.print(gene[i]);
    }
}
