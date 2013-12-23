package umm.semanticgp

import java.util.Random

public class Mutation {
	
	static mutation(GpTree P1, Evolver P1Evolver) {
		Random random = new Random()

		def copyP1 = new GpTree(P1.nodes.clone())
		def int noiseProbability = 1/copyP1.nodes.size() * 100
		int i = 0
		while (copyP1.nodes[i] != null) {
			if (random.nextInt(100) <= noiseProbability && !(copyP1.nodes[i] instanceof Closure)) {
				copyP1.nodes.remove(i)
				def randomGeneratedTree = new Ptc2(
					P1Evolver.operatorList,
					P1Evolver.variableList,
					P1Evolver.percentVariables, 
					P1Evolver.initialConstantRange
					)
				int mutationTreeSize = (random.nextInt(P1.nodes.size() + 1) / 2) + 1
				def mutationTree = randomGeneratedTree.generateTree(mutationTreeSize)
				
				//ask Nic about size of random mutated tree size
				if (i == copyP1.nodes.size()) {
				copyP1.nodes.addAll(mutationTree.findSubTree(0))
				} else {
				copyP1.nodes.addAll(i, mutationTree.findSubTree(0))
				}
				noiseProbability = 1/copyP1.nodes.size() * 100
				i = copyP1.findCrossoverParameters(i) + 1
			} else {
			i++
			}
		}
		return copyP1
	}
}
				
/** Other form of mutation, might want to come back to? **/				
				
//		def normalDistributionVariance
//		def min
//		def max
				
//				def fromNormalDistribution
//				if (!(min <= P1.nodes[i] && P1.nodes[i] <= max)) {
//					fromNormalDistribution //random number from N(0, normalDistributionVariance)
//				}
//				copyP1.nodes[i] = P1.nodes[i] + fromNormalDistribution
//			}
//		}
//		return copyP1;
//	}
//}