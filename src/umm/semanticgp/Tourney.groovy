package umm.semanticgp

class Tourney {
	
	static Tournament(GpTree potentialParent1, GpTree potentialParent2) {
		
	/* should take the whole population and computes n things to tourney */
		def fitness1 = Fitness.computeFitness(potentialParent1)
		def fitness2 = Fitness.computeFitness(potentialParent2)
		println fitness1
		println fitness2
		if (fitness1 <= fitness2) {
			return potentialParent1
		} else {
			return potentialParent2
		}
	}
	
}
