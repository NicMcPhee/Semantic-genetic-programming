package umm.semanticgp

class Evolver {
	static operatorList
	static variableList
	static initialConstantRange
	static percentVariables
	static popSize
	
	def Evolver(operatorList, variableList, percentVariables, initialConstantRange, popSize) {
		this.operatorList = operatorList
		this.variableList = variableList
		this.percentVariables = percentVariables
		this.initialConstantRange = initialConstantRange
		this.popSize = popSize
	}
	def initialPop(popSize) {
		def initialPopulation = []
		for (int i = 0; i < initialPopulationSize; i++) {
			initialPopulation[i] = new Ptc2(operatorList, variableList, percentVariables, initialConstantRange)
		}
	}
	def mutationType() {
		if (random.nextInt(100) < 90 /*this may be a variable*/) {
			Crossover.crossover(parent1, parent2)
		} else if (random.nextInt(100) < 91) {
			Mutation.mutation(parent1)
		} else {
			GpTree copyParent1 = new GpTree(parent1.nodes.clone())
		}
	}
}
