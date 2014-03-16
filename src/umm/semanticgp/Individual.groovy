package umm.semanticgp
import groovy.transform.AutoClone;

import java.util.UUID;

@AutoClone
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
		uid = UUID.randomUUID();
	}
    
	def getUid() {
		return uid
	}
    
	def setFitness(fitness) {
		this.treeFitness = fitness
	}
	
    String toString() {
        return "[" + this.getFitness() + ": " + this.getTree().toString() + "]"
    }
}
