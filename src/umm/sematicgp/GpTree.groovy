package umm.sematicgp

class GpTree {
	def nodes

	def GpTree(nodes) {
		this.nodes = nodes
	}
	
	def evaluate(context, index = 0) {
		while (nodes.size > 3 && index < nodes.size - 2) {
			if (nodes[index] instanceof Closure && !(nodes[index + 1] instanceof Closure) &&
				!(nodes[index + 2] instanceof Closure)) {
				def simpleNodes = new GpTree([nodes[index], nodes[index + 1], nodes[index + 2]])
				nodes[index] = simpleNodes.evaluate(context)
				nodes.remove(index + 1)
				nodes.remove(index + 1)
				index = 0
			} else {
				index++
			}
		}
		if (nodes[index] instanceof Closure) {
			def f = nodes[index]
            def x = evaluate(context, index + 1)
            def y = evaluate(context, index + 2)
            return f(x, y)
        } else {
            if(context.containsKey(nodes[index])) {
				return context.(nodes[index])
            } else {
                return nodes[index]
            }
        }
    }
}