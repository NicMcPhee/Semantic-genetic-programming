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
		if (Operator.isFunction(nodes[index])) {
			def f = nodes[index]
			index++
			switch (f) {
				case 'plus':
					Operator.plus(doEvaluate(context), doEvaluate(context))
					break
				case 'sub':
					Operator.sub(doEvaluate(context), doEvaluate(context))
					break
				case 'mult':
					Operator.mult(doEvaluate(context), doEvaluate(context))
					break
				case 'divi':
					Operator.divi(doEvaluate(context), doEvaluate(context))
					break
				case 'sin':
					Operator.sin(doEvaluate(context))
					break
				case 'cos':
					Operator.cos(doEvaluate(context))
					break
				case 'log':
					Operator.log(doEvaluate(context))
					break
				case 'gpif':
					Operator.gpif(doEvaluate(context),doEvaluate(context),doEvaluate(context))
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
		while(j < Operator.numArgs(nodes[i])) {
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
			if (Operator.isFunction(nodes[i])) {
				gpString += Operator.toString(nodes[i])
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