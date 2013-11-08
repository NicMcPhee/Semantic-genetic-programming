package umm.sematicgp

class GpTree {
	def nodes 
	
	def GpTree(nodes) {
		this.nodes = nodes
	}
	def evaluate(context) {
		if( nodes[0].equals("x")) {
			return context.x
		}
		else {
		return nodes[0]
		}
	}
}
