package umm.semanticgp

class Tourney {
	/* takes the whole population and computes n things to tourney */
	static Tournament(array, n) {
		def Fitness = new Fitness(Evolver.TestPointsList)
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
				// to select fitness from existing individual or adds to individuals fitness
				if(array[randomIndex].treeFitness == null) {
					tourneyFitnesses[i] = Fitness.computeFitness(array[randomIndex])
					array[randomIndex].setFitness(tourneyFitnesses[i])
				} else {
					tourneyFitnesses[i] = array[randomIndex].treeFitness
				}
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