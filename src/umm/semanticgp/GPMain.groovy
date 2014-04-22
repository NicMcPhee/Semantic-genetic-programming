package umm.semanticgp

import umm.semanticgp.Ptc2
import umm.semanticgp.Operator
import umm.semantic.parameters.GlobalParameters
import java.io.File


class GPMain {

	static main(args) {
		//Global Parameters to read from file
		File fileParam = new File('src/umm/semanticgp/fileParam.txt')
		GlobalParameters.setParameters(fileParam)
	
		def OperatorList = GlobalParameters.getStringArrValue("OperatorList")
		def VarsList = GlobalParameters.getStringArrValue("VarsList")
		def PercentVariables= GlobalParameters.getIntValue('PercentVariables')
		def LowestConstant= GlobalParameters.getIntValue('LowestConstant')
		def HighestConstant= GlobalParameters.getIntValue('HighestConstant')
		def InitialTreeSize= GlobalParameters.getIntValue('InitialTreeSize')
		def PopSize= GlobalParameters.getIntValue('PopSize')
		def Generations= GlobalParameters.getIntValue('Generations')
		
		def Evolve = new Evolver(OperatorList, VarsList, PercentVariables, LowestConstant, HighestConstant, InitialTreeSize, PopSize, Generations)
		Evolve.TestPointsList = //Evolve.readTestPoints(args[0])
			[[['x': 0.0], Math.sin(0.0)],
			[['x': 0.1], Math.sin(0.1)],
			[['x': 0.2], Math.sin(0.2)],
			[['x': 0.3], Math.sin(0.3)],
			[['x': 0.4], Math.sin(0.4)],
			[['x': 0.5], Math.sin(0.5)],
			[['x': 0.6], Math.sin(0.6)],
			[['x': 0.7], Math.sin(0.7)],
			[['x': 0.8], Math.sin(0.8)],
			[['x': 0.9], Math.sin(0.9)],
			[['x': 1.0], Math.sin(1.0)],
			[['x': 1.1], Math.sin(1.1)],
			[['x': 1.2], Math.sin(1.2)],
			[['x': 1.3], Math.sin(1.3)],
			[['x': 1.4], Math.sin(1.4)],
			[['x': 1.5], Math.sin(1.5)],
			[['x': 1.6], Math.sin(1.6)],
			[['x': 1.7], Math.sin(1.7)],
			[['x': 1.8], Math.sin(1.8)],
			[['x': 1.9], Math.sin(1.9)],
			[['x': 2.0], Math.sin(2.0)],
			[['x': 2.1], Math.sin(2.1)],
			[['x': 2.2], Math.sin(2.2)],
			[['x': 2.3], Math.sin(2.3)],
			[['x': 2.4], Math.sin(2.4)],
			[['x': 2.5], Math.sin(2.5)],
			[['x': 2.6], Math.sin(2.6)],
			[['x': 2.7], Math.sin(2.7)],
			[['x': 2.8], Math.sin(2.8)],
			[['x': 2.9], Math.sin(2.9)],
			[['x': 3.0], Math.sin(3.0)],
			[['x': 3.1], Math.sin(3.1)],
			[['x': 3.2], Math.sin(3.2)],
			[['x': 3.3], Math.sin(3.3)],
			[['x': 3.4], Math.sin(3.4)],
			[['x': 3.5], Math.sin(3.5)],
			[['x': 3.6], Math.sin(3.6)],
			[['x': 3.7], Math.sin(3.7)],
			[['x': 3.8], Math.sin(3.8)],
			[['x': 3.9], Math.sin(3.9)],
			[['x': 4.0], Math.sin(4.0)],
			[['x': 4.1], Math.sin(4.1)],
			[['x': 4.2], Math.sin(4.2)],
			[['x': 4.3], Math.sin(4.3)],
			[['x': 4.4], Math.sin(4.4)],
			[['x': 4.5], Math.sin(4.5)],
			[['x': 4.6], Math.sin(4.6)],
			[['x': 4.7], Math.sin(4.7)],
			[['x': 4.8], Math.sin(4.8)],
			[['x': 4.9], Math.sin(4.9)],
			[['x': 5.0], Math.sin(5.0)],
			[['x': 5.1], Math.sin(5.1)],
			[['x': 5.2], Math.sin(5.2)],
			[['x': 5.3], Math.sin(5.3)],
			[['x': 5.4], Math.sin(5.4)],
			[['x': 5.5], Math.sin(5.5)],
			[['x': 5.6], Math.sin(5.6)],
			[['x': 5.7], Math.sin(5.7)],
			[['x': 5.8], Math.sin(5.8)],
			[['x': 5.9], Math.sin(5.9)],
			[['x': 6.0], Math.sin(6.0)],
			[['x': 6.1], Math.sin(6.1)],
			[['x': 6.2], Math.sin(6.2)]]
		Evolve.evolve(90)
	}
}