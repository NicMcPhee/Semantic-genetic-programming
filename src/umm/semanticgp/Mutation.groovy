package umm.semanticgp

import java.util.Random

public class Mutation {
	
	static mutation(GpTree P1) {
		Random random = new Random()
		
		def copyP1 = new GpTree(P1.nodes.clone())
		
		def noiseProbability
		def normalDistributionVariance
		def min
		def max
		
		for (def i = 0; i < P1.size(); i++) {
			if (noiseProbability >= random.nextInt(10)) {
				def fromNormalDistribution
				if (!(min <= P1.nodes[i] && P1.nodes[i] <= max)) {
					fromNormalDistribution //random number from N(0, normalDistributionVariance)
				}
				copyP1.nodes[i] = P1.nodes[i] + fromNormalDistribution
			}
		}
		return copyP1;
	}
}