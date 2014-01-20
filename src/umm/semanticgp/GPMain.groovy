package umm.semanticgp

import umm.semanticgp.Ptc2
import umm.semanticgp.Operator
import java.util.Random

class GPMain {

	static main(args) {
		def OperatorList = [Operator.plus, Operator.mult, Operator.sub, Operator.divi, Operator.sin, Operator.cos, Operator.log]
		def VarsList = ["x","y"]
		def PercentVariables = 80
		def LowestConstant = -1
		def HighestConstant = 1
		def InitialTreeSize = 20
		def PopSize = 40
		def Generations = 50
		def Evolve = new Evolver(OperatorList, VarsList, PercentVariables, LowestConstant, HighestConstant, InitialTreeSize, PopSize, Generations)
		Evolve.TestPointsList = //Evolve.readTestPoints(args[0])
			[[['x': 0, 'y': 0], 0],
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
			[['x': -400, 'y': -300], -700],
			[['x': 5, 'y': 4], 9],
			[['x': -500, 'y': 249], -251],
			[['x': 5, 'y': 6], 11],
			[['x': 6, 'y': 5], 11],
			[['x': 6, 'y': 6], 12],
			[['x': 6, 'y': 7], 13],
			[['x': 7, 'y': 6], 13],
			[['x': -15, 'y': 15], 0],
			[['x': -14, 'y': 8], -6],
			[['x': 14, 'y': -10], 4],
			[['x': 5, 'y': -12], -7]]
		Evolve.evolve(90)
	}
}