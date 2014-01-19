package umm.semanticgp

class Fitness {
	def inputList
	def Fitness(inputList) {
		this.inputList = inputList
	}

	def computeFitness(Individual individual) {
		def meanSqError = 0
		for(def i = 0; i < inputList.size(); i++) {
			def expectedResult = inputList[i][1]
			def evaluatedTree = individual.getTree().evaluate(inputList[i][0])
			meanSqError += Math.pow((evaluatedTree - expectedResult), 2)
		}
		return meanSqError
	}
}