package umm.semanticgp

import umm.util.SharedPRNG;
// 90% or more crossover 1% mutation rest copy into next generation.
class Crossover {
	
	private static rand = SharedPRNG.instance()
    
    static crossover(Individual P1, Individual P2) {
        def copyP1 = new GpTree(P1.getTree().nodes.clone())
		
		def parent1Leaves = []
		def parent2Leaves = []
		def parent1Operators = []
		def parent2Operators = []
        
        def l2opP1 = rand.nextInt(10)
        def l2opP2 = rand.nextInt(10)
        // adds indices of P1
		leavesAndOperators(P1.getTree(), parent1Operators, parent1Leaves)
        // adds indices of P2
		leavesAndOperators(P2.getTree(), parent2Operators, parent2Leaves)

		def newNodes 
		if (l2opP2 < 1 || parent2Operators.empty) { // make into function
			newNodes = P2.getTree().nodes[selectIndex(parent2Leaves)]
		} else {
			newNodes = P2.getTree().findSubTree(selectIndex(parent2Operators))
		}
		def indexToAdd
		def endIndex
        if (l2opP1 < 1 || parent1Operators.empty) {
            indexToAdd = selectIndex(parent1Leaves)
			endIndex = indexToAdd + 1

        } else {
            indexToAdd = selectIndex(parent1Operators)
            endIndex = copyP1.findCrossoverParameters(indexToAdd) + 1

        }
		copyP1.nodes.removeRange(indexToAdd, endIndex)
		copyP1.nodes.addAll(indexToAdd, newNodes)
        def individual = new Individual(copyP1)
        return [individual, indexToAdd]
    }
	
	private static selectIndex(selectorArray) {
		selectorArray[rand.nextInt(selectorArray.size())]
	}
	
	private static leavesAndOperators(parentTree, parentOperators, parentLeaves) {
		for (def i = 0; i < parentTree.nodes.size(); i++) {
			if (OperatorJava.isFunction(parentTree.nodes[i])) {
				parentOperators.add(i)
			} else {
				parentLeaves.add(i)
			}
		}
	}
}