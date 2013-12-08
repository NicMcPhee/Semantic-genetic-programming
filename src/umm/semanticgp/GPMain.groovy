package umm.semanticgp

import umm.semanticgp.Ptc2
import umm.semanticgp.Operator
import java.util.Random

class GPMain {

	static main(args) {
		def initialPopulationSize = 20
		def initialPopulation = []
		for (int i = 0; i < initialPopulationSize; i++) {
			initialPopulation[i] = new Ptc2([Operator.plus], ["x","y"], 90, 10)
		}
		
		Random random = new Random()
		GpTree parent1 = initialPopulation[random.nextInt(initialPopulationSize)]
		GpTree parent2 = initialPopulation[random.nextInt(initialPopulationSize)]

	}
}