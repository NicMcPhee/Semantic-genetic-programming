package umm.semanticgp
import java.util.UUID;

class Individual {
	private static final long serialVersionUID = 8709627944120749083L;
	def gpTree
	def uid = UUID.randomUUID();
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
	
	def setUid() {
		return UUID.randomUUID();
	}
	def getUid() {
		return uid
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
