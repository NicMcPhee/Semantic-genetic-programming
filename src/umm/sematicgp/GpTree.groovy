package umm.sematicgp

import java.util.Random

class GpTree {
	def nodes

	def GpTree(nodes) {
		this.nodes = nodes
	}

	def evaluate(context, index = 0) {
		if (isFunction(nodes[index])) {
			def f = nodes[index]
			def numberOfArgs = numArgs(f)
			def position = index + 1
			for(int j = 0; j < numberOfArgs; j++) {	
				def x = evaluate(context, position)
				f = f.curry(x)
				position = findCrossoverParameters(position) + 1
			}	
			return f()
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