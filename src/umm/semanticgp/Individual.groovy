package umm.semanticgp

class Individual {
	
	def gpTree
	def treeFitness = null
	
	def Individual(gpTree) {
		this.gpTree = gpTree
	}
	
	def getFitness() {
		return treeFitness
	}
	def getTree() {
		return gpTree
	}
	def setFitness(fitness) {
		this.treeFitness = fitness
	}
    
	def compareTo(individual) {
		if (this.treeFitness < individual.treeFitness) {
			return this
		} else {
			return individual
		}
	}
	
    String toString() {
        return "[" + this.getFitness() + ": " + this.getTree().toString() + "]"
    }
}
