package umm.semanticgp

class Tourney {
	/* takes the whole population and computes n things to tourney */
	static Tournament(array, n) {
		def Fitness = new Fitness(Evolver.FitnessList)
		Random rand = new Random()
		def toTourneyIndex = []
		def tourneyFitnesses = []
		def bestFitnessIndex = 0
		def bestFitness = Double.POSITIVE_INFINITY
		int i = 0
		while (toTourneyIndex.size() != n) {
			def randomIndex = rand.nextInt(array.size())
			if(!(toTourneyIndex.contains(randomIndex))) {
				toTourneyIndex[i] = randomIndex
				tourneyFitnesses[i] = Fitness.computeFitness(array[randomIndex])
				i++
			}
		}
		for(def j = 0; j < n; j++) {
			def computedFitness = tourneyFitnesses[j]
			
			if (computedFitness < bestFitness) {
				bestFitness = computedFitness
				bestFitnessIndex = toTourneyIndex[j]
			}
		}
		return array[bestFitnessIndex]
	}
}