package umm.semanticgp

import umm.util.SharedPRNG;

public class Mutation {

    static mutation(GpTree P1, Evolver P1Evolver) {
        def random = SharedPRNG.instance()

        def copyP1 = new GpTree(P1.nodes.clone())
        def noiseProbability = 1/copyP1.nodes.size() * 100
        int i = 0
        while (copyP1.nodes[i] != null) {
            if (random.nextInt(100) <= noiseProbability && !(Operator.isFunction(copyP1.nodes[i]))) {
                copyP1.nodes.remove(i)
                def randomGeneratedTree = new Ptc2(
                        P1Evolver.operatorList,
                        P1Evolver.variableList,
                        P1Evolver.percentVariables,
                        P1Evolver.lowestConstant,
                        P1Evolver.highestConstant
                        )
                int mutationTreeSize = (random.nextInt(P1.nodes.size() + 1) / 2) + 1
                def mutationTree = randomGeneratedTree.generateTree(mutationTreeSize)

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

        def individual = new Individual(copyP1)
        return individual
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