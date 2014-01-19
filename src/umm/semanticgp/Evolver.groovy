package umm.semanticgp

import java.util.Random

class Evolver {

	def operatorList
	def variableList
	def lowestConstant
	def highestConstant
	def percentVariables
	def initialTreeSize
	def popSize
	def Population = []
	static TestPointsList = [][]
	def generations

	def Evolver(operatorList, variableList, percentVariables, lowestConstant, highestConstant, initialTreeSize, popSize, generations) {
		this.operatorList = operatorList
		this.variableList = variableList
		this.percentVariables = percentVariables
		this.lowestConstant = lowestConstant
		this.highestConstant = highestConstant
		this.initialTreeSize = initialTreeSize
		this.popSize = popSize
		this.generations = generations
	}

	def evolve(crossoverPercent) {
		initialPop()
		//printFitness()
		println("Resulting Fitness")
		for (def j = 1; j < generations; j++) {
			mutationType(crossoverPercent)
		}

		//printFitness()
	}

	def readFitness(input) {
		FileReader inputFile = new FileReader(input)
		Scanner scan = new Scanner(inputFile)
		while (scan.hasNextLine()) {
			def context = [:]
			def j = 0
			while (scan.hasNext('[a-zA-Z]*:[0-9]*')) {
				def contextString = scan.next()
				def contextArray = contextString.split(':')
				String key = contextArray[0]
				int value = contextArray[1].toInteger()
				context.put(key, value)
				j++
			}
			def subList = [context, scan.nextInt()]
			TestPointsList.add(subList)
		}
		inputFile.close()
		scan.close()
		return TestPointsList
	}

	def initialPop() {
		for (int i = 0; i < popSize; i++) {
			def GpTree = new Ptc2(operatorList, variableList, percentVariables, lowestConstant, highestConstant)
			def Individual = new Individual(GpTree.generateTree(initialTreeSize))
			Population[i] = Individual
		}
	}

	def mutationType(crossoverPercentage) {
		def childGeneration = []
		Random random = new Random()
		def parent1 = Tourney.Tournament(Population, 2)
		def parent2 = Tourney.Tournament(Population, 2)
		for(def i = 0; i < popSize; i++) {
			if (random.nextInt(100) < crossoverPercentage) {
				childGeneration[i] = Crossover.crossover(parent1.getTree(), parent2.getTree())
			} else if (random.nextInt(100) < (crossoverPercentage  + 1)) {
				childGeneration[i] = Mutation.mutation(parent1.getTree(), this)
			} else {
				childGeneration[i] = parent1
			}
		}
		for (def k = 0; k < childGeneration.size(); k++) {
			if(!(childGeneration[k] instanceof Individual)) {
				def Individual = new Individual(childGeneration[k])
				Population[k] = Individual
			} else {
				Population[k] = childGeneration[k]
			}
		}
	}
	def printFitness() {
		def Fitness = new Fitness(TestPointsList)
		for(def i = 0; i < Population.size(); i++) {
			println(Fitness.computeFitness(Population[i]))
		}
	}
}