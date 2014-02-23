package umm.semanticgp

class GpTree {
	def nodes
	def index

	def GpTree(nodes) {
		this.nodes = nodes
	}

	def evaluate(context) {
		index = 0
		return doEvaluate(context)
	}

	def doEvaluate(context) {
		if (OperatorJava.isFunction(nodes[index])) {
			def f = nodes[index]
			index++
			switch (f) {
				case 'plus':
					doEvaluate(context) + doEvaluate(context)
					break
				case 'sub':
					doEvaluate(context) - doEvaluate(context)
					break
				case 'mult':
					doEvaluate(context) * doEvaluate(context)
					break
				case 'divi':
					def diviFirstArg = doEvaluate(context)
					def diviSecondArg = doEvaluate(context)
					if (Math.abs(diviSecondArg)== 0) {
						1
					} else {
						diviFirstArg / diviSecondArg
					}
					break
				case 'sin':
					Math.sin(doEvaluate(context))
					break
				case 'cos':
					Math.cos(doEvaluate(context))
					break
				case 'log':
					def logArg = doEvaluate(context)
					if (logArg > 0) {
						Math.log(logArg)
					} else {
						-100000
					}
					break
				case 'gpif':
					def ifFirstArg = doEvaluate(context)
					def ifSecondArg = doEvaluate(context)
					def ifThirdArg = doEvaluate(context)
					if (ifFirstArg > 0) {
						ifSecondArg
					} else {
						ifThirdArg
					}
					break
			}
		} else {
			def result
			if(context.containsKey(nodes[index])) {
				result = context.(nodes[index])
			} else {
				result = nodes[index]
			}
			index = index + 1
			return result
		}
	}

	def findSubTree(int i) {
		return nodes[i..findCrossoverParameters(i)]
	}

	def findCrossoverParameters(int i) {
		def j = 0
		def end = i
		while(j < OperatorJava.numArgs(nodes[i])) {
			end = findCrossoverParameters(end + 1)
			j++
		}
		return end
	}
	String toString() {
		printGpTree()
	}
	def printGpTree() {
		def gpString = "["
		for (int i = 0; i < nodes.size(); ++i) {
			if (OperatorJava.isFunction(nodes[i])) {
				gpString += OperatorJava.toString(nodes[i])
				if (i != nodes.size() - 1) {
					gpString += ", "
				}
			} else {
				gpString += nodes[i]
				if (i != nodes.size() - 1)
					gpString += ", "
			}
		}
		return gpString += "]"
	}

	def size() {
		return nodes.size()
	}
}