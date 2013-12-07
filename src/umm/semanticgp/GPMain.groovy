package umm.semanticgp

import umm.semanticgp.Ptc2
import umm.semanticgp.Operator

class GPMain {

	static main(args) {
		def initialPopulationSize = 20
		def initialPopulation = []
		for (int i = 0; i < initialPopulationSize; i++) {
			initialPopulation[i] = new Ptc2([Operator.plus], ["x","y"], 90, 10)
		}
	}
}