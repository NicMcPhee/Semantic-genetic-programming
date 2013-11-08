package umm.sematicgp

class GpTree {
	def nodes 
	
	def GpTree(nodes) {
		this.nodes = nodes
	}
	def evaluate(context) {
	
		if(context.containsKey(nodes[0])) {
			return context.(nodes[0])
		}
		else {
		return nodes[0]
		}
	}
}
