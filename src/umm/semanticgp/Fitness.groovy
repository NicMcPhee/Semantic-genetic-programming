package umm.semanticgp

class Fitness {
	/*for testing I'm using a x+y function.*/
	def inputList
	def Fitness(inputList) {
		this.inputList = inputList
	}

	def computeFitness(Individual individual) {
		def count = 0
		/* Count- What do we need this for? gp field guide said it was useful*/
		def meanSqError = 0
		for(def i = 0; i < inputList.size(); i++) {
			def expectedResult = inputList[i][1]
			def evaluatedTree = individual.getTree().evaluate(inputList[i][0])
			if (evaluatedTree == expectedResult) {
				count++
			}
			meanSqError += Math.pow((evaluatedTree - expectedResult), 2)
		}
		return meanSqError
	}
}