package geneticalgorithm;

public class Main {

    public static void main(String[] args) {
        Population initialPop = new Population();
        for(int i = 0; i < 20; i++)
            initialPop.chromosomes[i].randomChromosome();
        initialPop.calculateTotalFitness();
        int generations = 0;
        double average = 0.0;
        System.out.println();
        for(int i = 0; i < 20; i++){
            
            GA a = new GA(0.7);
            generations = a.GAEvaluate(initialPop);
            average += generations;
            System.out.println("Number of generations it took reach solution: " + generations);
        }
        average /= 20.0;
        System.out.println("Average number of Generations:" + average);
    }
      
 }
    
