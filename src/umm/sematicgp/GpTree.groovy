package umm.sematicgp

class GpTree {
	def nodes

	def GpTree(nodes) {
		this.nodes = nodes
	}
	def evaluate(context) {
		if (nodes[0] instanceof Closure) {
			def f = nodes[0]
			return f(nodes[1], nodes[2])
		}
		else {
			if(context.containsKey(nodes[0])) {
				return context.(nodes[0])
			}
			else {
				return nodes[0]
			}
		}
	}
}
