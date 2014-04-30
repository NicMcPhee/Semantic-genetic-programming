package umm.semanticgp

class Fitness {
	def inputList
	def Fitness(inputList) {
		this.inputList = inputList
	}

	def computeFitness(Individual individual) {
		def absError = 0
		for(def i = 0; i < inputList.size(); i++) {
			def expectedResult = inputList[i][1]
			def evaluatedTree = individual.getTree().evaluate(inputList[i][0])
			absError += Math.abs(evaluatedTree - expectedResult)
		}
		absError += individual.getTree().size()/100
		return absError
	}
}