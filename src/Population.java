package geneticalgorithm;

public class Population{
    static final int POPULATION_SIZE = 20; /* Maximum population size */
    Chromosome[] chromosomes = new Chromosome[POPULATION_SIZE]; /* Population */
    int totalFitness; /* Population total fitness */

    /* Constructor */
    public Population(){
         for(int i = 0; i < chromosomes.length; i++)
            chromosomes[i] = new Chromosome();
    }
    /* Copy Constructor */
    public Population(Population originalPop){
        for(int i = 0; i < POPULATION_SIZE; i++)
            chromosomes[i] = new Chromosome(originalPop.chromosomes[i]);
        this.totalFitness = originalPop.totalFitness;
    }
    /* Set fitness values for all the chromosomes in the Population */
    public void setPopulationFitness(){
        for(int i = 0; i < Population.POPULATION_SIZE; i++)
            chromosomes[i].setFitness();
    }
    /* Obtain total fitness of  all the chromosomes in the Population */
    public void calculateTotalFitness(){
        totalFitness = 0;
        for(int i = 0; i < Population.POPULATION_SIZE; i++)
            totalFitness += chromosomes[i].fitness;
    }
    /* Print each chromosome in the population */
    public void printPopulation(){
        for(int i = 0; i < POPULATION_SIZE; i++){
            chromosomes[i].printDetails();
            System.out.print(" ");
        }
        System.out.println();
    }
    /* Print all the fitness values in the population */
    public void printPopulationFitness(){
        for(int i = 0; i < POPULATION_SIZE;i++)
            System.out.print(chromosomes[i].fitness + " ");
        System.out.println();
    }
    
}

