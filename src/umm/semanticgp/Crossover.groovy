package umm.semanticgp

import umm.util.SharedPRNG;
// 90% or more crossover 1% mutation rest copy into next generation.
class Crossover {
    
    static crossover(GpTree P1, GpTree P2) {
		def rand = SharedPRNG.instance()
        def copyP1 = new GpTree(P1.nodes.clone())
		
		def parent1Leaves = []
		def parent2Leaves = []
		def parent1Operators = []
		def parent2Operators = []
        
        def l2opP1 = rand.nextInt(10)
        def l2opP2 = rand.nextInt(10)
        // adds indices of P1
		leavesAndOperators(P1, parent1Operators, parent1Leaves)
        // adds indices of P2
		leavesAndOperators(P2, parent2Operators, parent2Leaves)

		def newNodes 
		if (l2opP2 < 1 || parent2Operators.empty) { // make into function
			newNodes = P2.nodes[parent2Leaves[rand.nextInt(parent2Leaves.size())]]
		} else {
			newNodes = P2.findSubTree(parent2Operators[rand.nextInt(parent2Operators.size())])
		}
		def indexToAdd
		def endIndex
        if (l2opP1 < 1 || parent1Operators.empty) {
            indexToAdd = parent1Leaves[rand.nextInt(parent1Leaves.size())]
			endIndex = indexToAdd + 1

        } else {
            indexToAdd = parent1Operators[rand.nextInt(parent1Operators.size())]
            endIndex = copyP1.findCrossoverParameters(indexToAdd) + 1

        }
		copyP1.nodes.removeRange(indexToAdd, endIndex)
		copyP1.nodes.addAll(indexToAdd, newNodes)
        def individual = new Individual(copyP1)
        return [individual, indexToAdd]
    }
	
	
	
	static leavesAndOperators(parentTree, parentOperators, parentLeaves) {
		for (def i = 0; i < parentTree.nodes.size(); i++) {
			if (OperatorJava.isFunction(parentTree.nodes[i])) {
				parentOperators.add(i)
			} else {
				parentLeaves.add(i)
			}
		}
	}
}