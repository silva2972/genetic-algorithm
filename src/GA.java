package geneticalgorithm;
import java.util.Random;

public class GA {
double Pco;
int solution = 10; /* Solution for the Algorithm */
    /* Constructor to set Pco value */
    public GA(double PcoValue){
        Pco = PcoValue;
    }
    /* Simulator for Genetic Algorithm with intital random population */
    public int GAEvaluate(Population initialPopulation){
        int numbOfGenerations = 1; /* tracker of generations to reach solution */
        boolean found = false;
        Population pop = new Population(initialPopulation);
        System.out.println("Initial Population: ");
        pop.printPopulation(); /*Printing the initial population */
        found = searchSolution(pop);
        while(!found){
            Population newGen = new Population();
            int[] selectionList = new int[20]; /*List of parent pairs */

            selectionList = rouletteSelection(pop); /*use roulette selection to get pairs*/

            replicate(pop,newGen,selectionList); /* 1-Pco chromosomes are replicate */
            if(Pco > 0) /*CrossOver is done if Pco is > 0 */
                crossOver(pop,newGen,selectionList);
            System.out.println("Population After CrossOver: ");
            newGen.printPopulation(); /*Printing population after crossOver */
            
            mutate(newGen); /*Mutate one value in the chromsomes */
            System.out.println("Population After Mutation: ");
            newGen.printPopulation(); /*Printing population after mutation */
            pop = newGen;
            pop.setPopulationFitness();
            pop.printPopulationFitness(); /* Printing fitness values of population */
            found = searchSolution(pop); /* Searching for solution */
            pop.calculateTotalFitness();
            numbOfGenerations++;
        }
        return numbOfGenerations;
    }
    /*Return true if solution is in the population */
    public boolean searchSolution(Population pop){
        for(int i = 0; i < Population.POPULATION_SIZE; i++)
            if(pop.chromosomes[i].fitness == solution)
                return true;
        return false;
    }
    /* Returns true if the pair of parents are valid cadidates */
    public boolean checkList(int[] list, int size, int[] p){

         for(int j = 0; j < size; j++){
             /* Case to avoid duplicate chromosomes to be replicated */
             if((list[j] == p[0] || list[j] == p[1]) && (j <= (20 - Pco*20)))
                     return false;
             /* Cases to avoid the same pair of parents for crossOver. */
             else if((j%2 == 0) && j >= ((20 - Pco*20))){
                 if((list[j] == p[0]) && (list[j-1]) == p[1])
                     return false;
                 if((list[j] == p[1]) && (list[j-1]) == p[0])
                     return false;
             }
            else{
                 if((list[j] == p[0]) && (list[j+1]) == p[1])
                     return false;
                 if((list[j] == p[1]) && (list[j+1]) == p[0])
                     return false;
            }
          }
         return true;
    }
    /*Finding pair of parents with roulette selection */
    public static int[] selectParents(Population pop){
        Random r = new Random();
        int[] parents = new int[2]; /*pair of parents */
        int numb = 0;
        while(numb < 2){
            /*Random value of sum of fitness values within 0 and total Fitness */
            int randomSum = r.nextInt(pop.totalFitness);
            /*Value to hold the sum of fitness value from the start to the individual chromosome*/
            int partialSum = 0;
            for(int i = 0; i < Population.POPULATION_SIZE; i++){
                partialSum += pop.chromosomes[i].fitness;
                /*If partialSum is greater then select chromosome as a parent */
                if(partialSum > randomSum){
                    parents[numb] = i;
                    numb++;
                    i = Population.POPULATION_SIZE;
                }
            }
          }
        return parents;
    }
    /*Roulette seletion of all the pairs to be replicated and crossovered */
      public int[] rouletteSelection(Population pop){
        int[] selectList = new int[20];
        int[] parents = new int[2];
        /* Pco > 0 allows for crossover to occur */
        if(Pco > 0){
            int x = 0;
            while(x < Population.POPULATION_SIZE){
                parents = selectParents(pop);
                /*If valid parents then add to selection list */
                if(checkList(selectList,x,parents)&& (parents[0] != parents [1])){
                    selectList[x] = parents[0];
                    selectList[x+1] = parents[1];
                    x+=2;
                }
            }
          }
        /*Population will just be the same for when Pco = 0 */
        else
            for(int i = 0; i < 20; i++)
                selectList[i] = i;
        return selectList;

    }
      /* Replicating the first chromsomes selected */
      public void replicate(Population pop,Population newPop,int[] list){
          for(int i = 0; i < 20 - Pco*20; i++)
              newPop.chromosomes[i] = pop.chromosomes[list[i]];
    }
      /* CrossOver with a mask off 1111100000 */
      public void crossOver(Population pop,Population newPop,int[] list){
          for(int i = (int) (20 - Pco*20); i < 20; i+=2){
              for(int j = 0; j < 10; j++){
                  /* keeping first five bits of the each parent */
                  if(j<5){
                    newPop.chromosomes[i].gene[j] = pop.chromosomes[list[i]].gene[j];
                    newPop.chromosomes[i+1].gene[j] = pop.chromosomes[list[i+1]].gene[j];
                  }
                  /* Using the other five bits of the other parent */
                  else {
                    newPop.chromosomes[i].gene[j] = pop.chromosomes[list[i+1]].gene[j];
                    newPop.chromosomes[i+1].gene[j] = pop.chromosomes[list[i]].gene[j];
                   }
              }
          }
      }
      /* Randomly select a chromosoem to have a random bit to be changed */
      public static void mutate(Population newGen){
          Random r  = new Random();
          int selected = r.nextInt(Population.POPULATION_SIZE);
          int bitLocation = r.nextInt(10);
          if(newGen.chromosomes[selected].gene[bitLocation] == 1)
              newGen.chromosomes[selected].gene[bitLocation] = 0;
          else
              newGen.chromosomes[selected].gene[bitLocation] = 1;
      }
      
}
