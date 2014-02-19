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
					OperatorJava.plus(doEvaluate(context), doEvaluate(context))
					break
				case 'sub':
					OperatorJava.sub(doEvaluate(context), doEvaluate(context))
					break
				case 'mult':
					OperatorJava.mult(doEvaluate(context), doEvaluate(context))
					break
				case 'divi':
					OperatorJava.divi(doEvaluate(context), doEvaluate(context))
					break
				case 'sin':
					OperatorJava.sin(doEvaluate(context))
					break
				case 'cos':
					OperatorJava.cos(doEvaluate(context))
					break
				case 'log':
					OperatorJava.log(doEvaluate(context))
					break
				case 'gpif':
					OperatorJava.gpif(doEvaluate(context),doEvaluate(context),doEvaluate(context))
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