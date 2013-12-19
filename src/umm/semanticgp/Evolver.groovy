package umm.semanticgp

class Evolver {
	static operatorList
	static variableList
	static initialConstantRange
	static percentVariables
	static popSize
	static Population

	def Evolver(operatorList, variableList, percentVariables, initialConstantRange, popSize) {
		this.operatorList = operatorList
		this.variableList = variableList
		this.percentVariables = percentVariables
		this.initialConstantRange = initialConstantRange
		this.popSize = popSize
	}

	def initialPop(popSize) {
		for (int i = 0; i < popSize; i++) {
			Population[i] = new Ptc2(operatorList, variableList, percentVariables, initialConstantRange)
		}
	}

	def mutationType(crossoverPercentage) {
		def childGeneration = []
		for(def i = 0; i < popSize; i++) {
			if (random.nextInt(100) < crossoverPercentage /*90 /*this may be a variable*/) {
				childGeneration[i] = Crossover.crossover(parent1, parent2)
			} else if (random.nextInt(100) < (crossoverPercentage + 1)/*91*/) {
				childGeneration[i] = Mutation.mutation(parent1)
			} else {
				GpTree copyParent1 = new GpTree(parent1.nodes.clone())
				childGeneration[i] = copyParent1
			}
		}
	}
}
