package umm.semanticgp

class Tourney {
    /* takes the whole population and computes n things to tourney */
    static Tournament(array, n) {
        def fitness = new Fitness(Evolver.TestPointsList)
        Random rand = new Random()
        def toTourney = []
        def bestIndividual
        def bestFitness = Double.POSITIVE_INFINITY
        int i = 0
        while (toTourney.size() != n) {
            def randomIndex = rand.nextInt(array.size())
            def individual = array[randomIndex]
            toTourney[i] = individual
            // to select fitness from existing individual or adds to individuals fitness
            if(individual.treeFitness == null) {
                def error = fitness.computeFitness(individual)
                individual.setFitness(error)
            }
            i++
        }
        for(def j = 0; j < n; j++) {
            def computedFitness = toTourney[j].getFitness()

            if (computedFitness < bestFitness) {
                bestFitness = computedFitness
                bestIndividual = toTourney[j]
            }
        }
        return bestIndividual
    }
}