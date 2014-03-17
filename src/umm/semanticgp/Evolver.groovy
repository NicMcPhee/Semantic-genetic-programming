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

	Neo4jObserverNotifier neo4j = new Neo4jObserverNotifier()
	LocalDBObserver observer = new LocalDBObserver(neo4j)
	Random random = SharedPRNG.instance() // new Random()

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
//		printFitnessAndTree()
		println("Best Individuals")
		for (def j = 1; j < generations; j++) {
			def start = System.currentTimeMillis()
			generateNewGeneration(crossoverPercent, j)
			println( "Generation " + j + " Avg Size = " + calcAvgSize())
			println(nBestFitnessIndiv(1)[0].toString())
			println(System.currentTimeMillis() - start)
		}
		println("Resulting Individuals")
	//	printFitnessAndTree()
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
			neo4j.setInitial(individual)
		}
	}

	def generateNewGeneration(crossoverPercentage, generation) {
		def fitness = new Fitness(TestPointsList)
		def childGeneration = []
        def eliteSetSize = (int) (popSize/100) 
        def elite = nBestFitnessIndiv(eliteSetSize)

		for(int i = 0; i < popSize; i++) {
			if(i < eliteSetSize) {
                def parent = elite[i]
				childGeneration[i] = parent.clone()
//				childGeneration[i].setUid()
				neo4j.setElitism(parent, childGeneration[i], generation)
			} else {
				def parent1 = Tourney.Tournament(Population, 2)
				def parent2 = Tourney.Tournament(Population, 2)
				childGeneration[i] = generateChild(parent1, parent2, crossoverPercentage, generation)
			}
		}
		Population = childGeneration.clone()
	}

	def generateChild(Individual parent1, Individual parent2, crossoverPercentage, generation) {
		def fitness = new Fitness(TestPointsList)
		def RANDOM_INT = random.nextInt(100)
		def child
		if (RANDOM_INT < crossoverPercentage) {
			def (XOChild, XoPoint) = Crossover.crossover(parent1, parent2)
			XOChild.setFitness(fitness.computeFitness(XOChild))
			neo4j.setCrossover(parent1, parent2, XOChild, generation, XoPoint)
			child = XOChild
		} else if (RANDOM_INT < (crossoverPercentage  + 1)) {
			def (mutationChild, mutationPoint) = Mutation.mutation(parent1, this)
			mutationChild.setFitness(fitness.computeFitness(mutationChild))
			neo4j.setMutation(parent1, mutationChild, generation, mutationPoint)
			child = mutationChild
		} else {
			def reproductionChild = parent1.clone()
//			reproductionChild.setUid()
			neo4j.setReproduction(parent1, reproductionChild, generation)
			child = reproductionChild
		}

		return child
	}

	def nBestFitnessIndiv(n) {
		def bestIndivArray = []
		Population.sort {it.getFitness()}
		for (def i = 0; i < n; i++) {
			bestIndivArray[i] = Population[i]
		}
		return bestIndivArray
	}

	def printFitnessAndTree() {
		for(def i = 0; i < Population.size(); i++) {
			println(Population[i].toString())
		}
	}
	
	def calcAvgSize() {
		def TOTAL_SIZE = 0
		for(def i = 0; i < Population.size(); i++) {
			TOTAL_SIZE += Population[i].getTree().nodes.size()
		}
		def AVG_SIZE = TOTAL_SIZE / Population.size()
		return AVG_SIZE
	}
}