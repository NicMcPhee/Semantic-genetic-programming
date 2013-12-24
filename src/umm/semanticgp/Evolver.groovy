package umm.semanticgp
import java.util.Random
class Evolver {
	def operatorList
	def variableList
	def initialConstantRange
	def percentVariables
	def initialTreeSize
	def popSize
	def Population = []
	static FitnessList = [][]
	def generations

	def Evolver(operatorList, variableList, percentVariables, initialConstantRange, initialTreeSize, popSize, generations) {
		this.operatorList = operatorList
		this.variableList = variableList
		this.percentVariables = percentVariables
		this.initialConstantRange = initialConstantRange
		this.initialTreeSize = initialTreeSize
		this.popSize = popSize
		this.generations = generations
	}

	def evolve(crossoverPercent) {
		initialPop()
		for (def j = 1; j < generations; j++) {
			mutationType(crossoverPercent)
		}
	}

	def readFitness(input) {
		FileReader inputFile = new FileReader(input/*args[0]*/)
		Scanner scan = new Scanner(inputFile)
		def i = 0
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
			FitnessList.add(subList)
			i++
		}
		return FitnessList
	}

	def initialPop() {
		for (int i = 0; i < popSize; i++) {
			def GpTree = new Ptc2(operatorList, variableList, percentVariables, initialConstantRange)
			Population[i] =  GpTree.generateTree(initialTreeSize)
		}
	}

	def mutationType(crossoverPercentage) {

		def childGeneration = []
		Random random = new Random()
		def parent1 = Tourney.Tournament(Population, 2)
		def parent2 = Tourney.Tournament(Population, 2)
		for(def i = 0; i < popSize; i++) {
			if (random.nextInt(100) < crossoverPercentage /*this may be a variable*/) {
				childGeneration[i] = Crossover.crossover(parent1, parent2)
			} else if (random.nextInt(100) < (crossoverPercentage  + 1)) {
				childGeneration[i] = Mutation.mutation(parent1, this)
			} else {
				GpTree copyParent1 = new GpTree(parent1.nodes.clone())
				childGeneration[i] = copyParent1
			}
		}
		for (def k = 0; k < childGeneration.size(); k++) {
			Population[k] = childGeneration[k]
		}
	}
}
