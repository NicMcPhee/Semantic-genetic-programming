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
            def numberOfArgs = Operator.numArgs(f)
            index = index + 1
            for(int j = 0; j < numberOfArgs; j++) {
                def x = doEvaluate(context)
                f = f.curry(x)
            }
            return f()
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