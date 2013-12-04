package umm.semanticgp

import java.util.Random;

// 90% or more crossover 1% mutation rest copy into next generation.
class Crossover {

	Random rand = new Random()
	def percentage = rand.nextInt(100)

	def crossover(GpTree P1, GpTree P2) {
		def l2opP1 = rand.nextInt(10)
		def l2opP2 = rand.nextInt(10)
		def parent1Leaves = []
		def parent2Leaves = []
		def parent1Operators = []
		def parent2Operators = []
		// adds indices of P1
		for (int i = 0; i < P1.nodes.size(); i++) {
			if (P1.nodes[i] instanceof Closure) {
				parent1Operators.add(i)
			}
			else {
				parent1Leaves.add(i)
			}
		}
		// adds indices of P2
		for (int i = 0; i < P2.nodes.size(); i++) {
			if (P2.nodes[i] instanceof Closure) {
				parent2Operators.add(i)
			}
			else {
				parent2Leaves.add(i)
			}
		}

		if (l2opP1 < 1) {
			def leafIndexToAdd = parent1Leaves[rand.nextInt(parent1Leaves.size())]
			P1.nodes.remove(leafIndexToAdd)
			if (l2opP2 < 1) {
				P1.nodes.addAll(leafIndexToAdd + 1, P2.nodes[parent2Leaves[rand.nextInt(parent2Leaves.size())]])
			}
			else {
				P1.nodes.addAll(leafIndexToAdd + 1,P2.findSubtree(parent2Operators[rand.nextInt(parent2Operators.size())]))
			}
		}
		else {
			def opIndexToAdd = parent1Operators[rand.nextInt(parent1Operators.size())]
			P1.nodes.remove(findSubtree(opIndexToAdd))
			if (l2opP2 < 1) {
				P1.nodes.addAll(opIndexToAdd + 1, P2.nodes[parent2Leaves[rand.nextInt(parent2Leaves.size())]])
			}
			else {
				P1.nodes.addAll(opIndexToAdd + 1,P2.findSubtree(parent2Operators[rand.nextInt(parent2Operators.size())]))
			}
		}
	}
}

//	static main(args) {
//		if (percentage < 1) {
//			mutation(P1)
//		}
//		else if (percentage < 91) { //90% crossover rate.
//			crossover(P1,P2)
//		}
//		else {
//			reproduction(P1)
//		}
//}
