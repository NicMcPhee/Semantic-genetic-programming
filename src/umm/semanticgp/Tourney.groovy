package umm.semanticgp
import umm.util.SharedPRNG;
class Tourney {
    /* takes the whole population and computes n things to tourney */
    static Tournament(array, n) {
        Random rand = SharedPRNG.instance()
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