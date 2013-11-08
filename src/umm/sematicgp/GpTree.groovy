package umm.sematicgp

class GpTree {
	def nodes

	def GpTree(nodes) {
		this.nodes = nodes
	}
	def evaluate(context, index = 0) {
		if (nodes[index] instanceof Closure) {
			def f = nodes[index]
			def x = evaluate(context, index + 1)
			def y = evaluate(context, index + 2)
			return f(x, y)
		}
		else {
			if(context.containsKey(nodes[index])) {
				return context.(nodes[index])
			}
			else {
				return nodes[index]
			}
		}
	}
}
