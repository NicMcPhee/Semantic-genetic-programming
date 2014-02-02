package umm.semanticgp

import umm.util.SharedPRNG;

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
		printFitnessAndTree()
		println("Best Individuals")
		for (def j = 1; j < generations; j++) {
			mutationType(crossoverPercent)
			println( "Generation " + j)
			printBestFitnessIndiv()

		}
		println("Resulting Individuals")
		printFitnessAndTree()
	}

	def readTestPoints(input) {
		FileReader inputFile = new FileReader(input)
		Scanner scan = new Scanner(inputFile)
		while (scan.hasNextLine()) {
			def context = [:]
			def j = 0
			while (scan.hasNext('[a-zA-Z]*:[0-9]*') || scan.hasNext('[a-zA-Z]*:-[0-9]*')) {
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
        def GpTree = new Ptc2(operatorList, variableList, percentVariables, lowestConstant, highestConstant)
        def fitness = new Fitness(TestPointsList)
		for (int i = 0; i < popSize; i++) {
			def individual = new Individual(GpTree.generateTree(initialTreeSize))
            individual.setFitness(fitness.computeFitness(individual))
			Population[i] = individual
		}
	}

	def mutationType(crossoverPercentage) {
        def fitness = new Fitness(TestPointsList)
		def childGeneration = []
		Random random = SharedPRNG.instance() // new Random()
		for(def i = 0; i < popSize; i++) {
			def parent1 = Tourney.Tournament(Population, 2)
			def parent2 = Tourney.Tournament(Population, 2)
			if (random.nextInt(100) < crossoverPercentage) {
				childGeneration[i] = Crossover.crossover(parent1.getTree(), parent2.getTree())
                childGeneration[i].setFitness(fitness.computeFitness(childGeneration[i]))
			} else if (random.nextInt(100) < (crossoverPercentage  + 1)) {
				childGeneration[i] = Mutation.mutation(parent1.getTree(), this)
                childGeneration[i].setFitness(fitness.computeFitness(childGeneration[i]))
			} else {
				childGeneration[i] = parent1
			}
		}
        Population = childGeneration.clone()
	}
	
	def printBestFitnessIndiv() {
		def bestFitnessIndiv = Population[0]
		for(def i = 1; i < Population.size(); i++) {
			if( Population[i].getFitness() < bestFitnessIndiv.getFitness()) {
				bestFitnessIndiv = Population[i]
			}
		}
		return println(bestFitnessIndiv.toString())
	}
	
	def printFitnessAndTree() {
		for(def i = 0; i < Population.size(); i++) {
			println(Population[i].toString())
		}
	}
}