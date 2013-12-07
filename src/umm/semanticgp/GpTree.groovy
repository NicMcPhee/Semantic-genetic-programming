package umm.semanticgp

import java.util.Random

class GpTree {
	def nodes

	def GpTree(nodes) {
		this.nodes = nodes
	}

	def evaluate(context, index = 0) {
		if (Operator.isFunction(nodes[index])) {
			def f = nodes[index]
			def numberOfArgs = Operator.numArgs(f)
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

	def findCrossoverParameters(int i) {
		def j = 0
		def end = i
		while(j < Operator.numArgs(nodes[i])) {
			end = findCrossoverParameters(end + 1)
			j++;
		}
		return end 
	}
	
	def printGpTree() {
		def gpString = "["
//		print("[");
		for (int i = 0; i < nodes.size(); ++i) {
			if (Operator.isFunction(nodes[i])) {
				//print(Operator.toString(nodes[i]))
				gpString += Operator.toString(nodes[i])
				if (i != nodes.size() - 1) {
					gpString += ", "
//					print(", ")
				}
			} else {
				gpString += nodes[i]
//				print(nodes[i]);
				if (i != nodes.size() - 1)
					gpString += ", "
//					print(", ")
			}
		}
//		println("]");
		return gpString += "]"
	}
}