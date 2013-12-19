package umm.semanticgp
import java.util.Random
class Evolver {
	static operatorList
	static variableList
	static initialConstantRange
	static percentVariables
	static popSize
	static Population = []
	static FitnessList = []
	static Generations

	def Evolver(operatorList, variableList, percentVariables, initialConstantRange, popSize) {
		this.operatorList = operatorList
		this.variableList = variableList
		this.percentVariables = percentVariables
		this.initialConstantRange = initialConstantRange
		this.popSize = popSize
	}

	def readFitness() {
		Scanner scan = new Scanner(args[0])
		while(scan.hasNext("],")) {
			FitnessList.add(scan.nextLine())
		}
	}

	def initialPop() {
		for (int i = 0; i < popSize; i++) {
			def GpTree = new Ptc2(operatorList, variableList, percentVariables, initialConstantRange) 
			Population[i] =  GpTree.generateTree(20)
		}
	}

	def mutationType(crossoverPercentage) {
	//	for (def j = 0; j < Generations; j++) {
			def childGeneration = []
			Random random = new Random()
			def parent1 = Tourney.Tournament(Population, 2)
			def parent2 = Tourney.Tournament(Population, 2)
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
			
			Population = childGeneration.clone()
		//}
	}
}
