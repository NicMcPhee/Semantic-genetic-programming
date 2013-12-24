package umm.semanticgp

import umm.semanticgp.Ptc2
import umm.semanticgp.Operator
import java.util.Random

class GPMain {

	static main(args) {
		def Evolve = new Evolver([Operator.plus, Operator.mult], ["x","y"], 80, 2, 20, 5, 100)
		Evolve.FitnessList = Evolve.readFitness(args[0] /*Does Not Work in Eclipse, but Should Be Correct for Console*/) /*[
			[['x': 0, 'y': 0], 0],
			[['x': 0, 'y': 1], 1],
			[['x': 1, 'y': 0], 1],
			[['x': 1, 'y': 1], 2],
			[['x': 1, 'y': 2], 3],
			[['x': 2, 'y': 1], 3],
			[['x': 2, 'y': 2], 4],
			[['x': 2, 'y': 3], 5],
			[['x': 3, 'y': 2], 5],
			[['x': 3, 'y': 3], 6],
			[['x': 3, 'y': 4], 7],
			[['x': 4, 'y': 3], 7],
			[['x': 4, 'y': 4], 8],
			[['x': 4, 'y': 5], 9],
			[['x': 5, 'y': 4], 9],
			[['x': 5, 'y': 5], 10],
			[['x': 5, 'y': 6], 11],
			[['x': 6, 'y': 5], 11],
			[['x': 6, 'y': 6], 12],
			[['x': 6, 'y': 7], 13],
			[['x': 7, 'y': 6], 13]
		]*/
		Evolve.evolve(90)
		for(def i = 0; i < Evolve.popSize; i++){
		println (Evolve.Population[i].printGpTree())
		}
	}
}