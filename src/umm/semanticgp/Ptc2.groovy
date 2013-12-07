package umm.semanticgp

import java.util.Random

class Ptc2 {
	
	static Ops = []
	static Vars = []
	static PercentageVariable
	static ConstantRange
	def Ptc2(Ops, Vars, PercentageVariable, ConstantRange) {
		this.Ops = Ops
		this.Vars = Vars
		this.PercentageVariable = PercentageVariable
		this.ConstantRange = ConstantRange
	}

	def generateTree(size) {
		def randomTree = new GpTree([])
		Random rand = new Random()
		
		if (size == 1) {
			randomTree[0] = nonOperatorSelection()
			return randomTree
		} else {
			def indexArr = []
			def randomOperator = randomOperators()
			randomTree.nodes[0] = randomOperator
			def i = 0;
			for (i; i < Operator.numArgs(randomOperator); i++) {
				randomTree.nodes[i + 1] = null
				indexArr[i] = i + 1
			}

			while (randomTree.nodes.size() < size) {
				def anotherOperator = randomOperators()
				def randomNodeIndex = rand.nextInt(indexArr.size())
				def indexInGpTree = indexArr[randomNodeIndex]
				randomTree.nodes[indexInGpTree] = anotherOperator
				def nullArray = []
				for(int j = 0; j < Operator.numArgs(anotherOperator); j++) {
					nullArray[j] = null
				}			
				randomTree.nodes.addAll(indexInGpTree + 1, nullArray)
				indexArr.clear()
				for (def k = 0; k < randomTree.nodes.size(); k++) {
					if (randomTree.nodes[k] == null) {
						indexArr[indexArr.size()] = k
					}
				}
			}		
			for (int k = 0; k < indexArr.size(); k++) {
				randomTree.nodes[indexArr[k]] = nonOperatorSelection()
			}
			return randomTree
		}
	}
	
	static randomOperators() {
		Random rand = new Random()
		return Ops[rand.nextInt(Ops.size())]	
	}
	
	static nonOperatorSelection() {
		Random rand = new Random()
		if (rand.nextInt(100) < PercentageVariable) {
			Vars[rand.nextInt(Vars.size())]
		}
		else {
			rand.nextInt(ConstantRange)
		}
	}
}