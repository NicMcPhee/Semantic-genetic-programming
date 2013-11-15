package umm.sematicgp

class GpTree {
	def nodes

	def GpTree(nodes) {
		this.nodes = nodes
	}

	def evaluate(context, index = 0) {
		if (isFunction(nodes[index])) {
			def f = nodes[index]
			def x = evaluate(context, index + 1)
			def y = evaluate(context, findCrossoverParameters(index + 1) + 1)
			return f(x, y)
		} else {
			if(context.containsKey(nodes[index])) {
				return context.(nodes[index])
			} else {
				return nodes[index]
			}
		}
	}

	def findSubTree(int i) {
		return nodes[i..findCrossoverParameters(i)]
	}

	def isFunction(node) {
		(node instanceof Closure)
	}

	def numArgs(node) {
		if (isFunction(node)) {
			node.parameterTypes.length
		}
		else {
			0
		}
	}
	def findCrossoverParameters(int i) {
		def j = 0
		def end = i
		while(j < numArgs(nodes[i])) {
			end = findCrossoverParameters(end + 1)
			j++;
		}
		return end 
	}
}
