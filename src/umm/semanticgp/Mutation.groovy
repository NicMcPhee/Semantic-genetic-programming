package umm.semanticgp

import umm.util.SharedPRNG;

public class Mutation {
	private static random = SharedPRNG.instance()

	static mutation(Individual P1, Evolver P1Evolver) {
		def copyP1 = new GpTree(P1.getTree().nodes.clone())
		int mutationPoint = random.nextInt(copyP1.nodes.size)
		def randomGeneratedTree = new Ptc2(
				P1Evolver.operatorList,
				P1Evolver.variableList,
				P1Evolver.percentVariables,
				P1Evolver.lowestConstant,
				P1Evolver.highestConstant
				)
		int mutationTreeSize = (random.nextInt(P1.getTree().nodes.size() + 1) / 2) + 1
		def mutationTree = randomGeneratedTree.generateTree(mutationTreeSize)
		
		if (!(OperatorJava.isFunction(copyP1.nodes[mutationPoint]))) {
			copyP1.nodes.remove(mutationPoint)
			if (mutationPoint == copyP1.nodes.size()) {
				copyP1.nodes.addAll(mutationTree.findSubTree(0))
			} else {
				copyP1.nodes.addAll(mutationPoint, mutationTree.findSubTree(0))
			}
		} else {
			copyP1.nodes.removeRange(mutationPoint, copyP1.findCrossoverParameters((int) mutationPoint) + 1)
			copyP1.nodes.addAll(mutationPoint, mutationTree.findSubTree(0))
		}

		def individual = new Individual(copyP1)
		return [individual, mutationPoint]
	}
}