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
            toTourney[i] = array[randomIndex]
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