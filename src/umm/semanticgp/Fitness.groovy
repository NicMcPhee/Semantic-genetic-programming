package umm.semanticgp

//[NaN: [/, +, sin, y, *, y, x, *, -, *, 0, sin, -1, log, cos, -, x, x, *, 0, x]]
//[NaN: [/, cos, /, y, cos, /, cos, y, x, cos, /, *, x, x, *, -1, +, sin, y, y]]
//[NaN: [/, +, +, x, -, -, x, x, x, *, y, 0, *, cos, +, +, x, y, y, x]]
//[NaN: [cos, +, *, x, cos, y, log, /, +, 0, -, x, 0, *, -, y, sin, cos, 0, x]]

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