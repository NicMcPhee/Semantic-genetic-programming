package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import spock.lang.Specification
import umm.semanticgp.GpTree
import umm.semanticgp.Operator
import umm.semanticgp.Ptc2

class GpTreeEvaluationTest extends Specification {

	@Test
	public void constantTest() {
		given:
		def context = [:]
		def tree5 = new GpTree([5])
		def tree7 = new GpTree([7])

		when:
		def result5 = tree5.evaluate(context)
		def result7 = tree7.evaluate(context)

		then:
		result5 == 5
		result7 == 7
	}

	@Test
	public void variableTest() {
		given:
		def context = ['x': 4, 'y' : 6]
		def treex = new GpTree(["x"])
		def treey = new GpTree(["y"])

		when:
		def resultx = treex.evaluate(context)
		def resulty = treey.evaluate(context)

		then:
		resultx == 4
		resulty == 6
	}

	@Test
	public void simpleConstantTreeTest() {
		given:
		def context = [:]
		def constantAddTree = new GpTree(["plus", 0, 1])
		def constantSubTree = new GpTree(["sub", 4, 1])
		def constantMultTree = new GpTree(["mult", 2, 3])
		def constantDiviTree = new GpTree(["divi", 6, 3])
		
		when:
		def resultAddTree = constantAddTree.evaluate(context)
		def resultSubTree = constantSubTree.evaluate(context)
		def resultMultTree = constantMultTree.evaluate(context)
		def resultDiviTree = constantDiviTree.evaluate(context)

		then:
		resultAddTree == 1
		resultSubTree == 3
		resultMultTree == 6
		resultDiviTree == 2
	}

	@Test
	public void simpleVariableTreeTest () {
		given:
		def context = ['x' : 3, 'y' : 6]
		def firstVarAddTree = new GpTree (["plus", "x", 1])
		def lastVarAddTree = new GpTree (["plus", 1, "x"])
		def twoVarAddTree = new GpTree (["plus", "x" , "y"])
		def firstVarSubTree = new GpTree (["sub", "x", 1])
		def lastVarSubTree = new GpTree (["sub", 1, "x"])
		def twoVarSubTree = new GpTree (["sub", "x" , "y"])
		def firstVarMultTree = new GpTree (["mult", "x", 1])
		def lastVarMultTree = new GpTree (["mult", 1, "x"])
		def twoVarMultTree = new GpTree (["mult", "x" , "y"])
		def firstVarDiviTree = new GpTree (["divi", "x", 1])
		def lastVarDiviTree = new GpTree (["divi", 3, "x"])
		def twoVarDiviTree = new GpTree (["divi", "x" , "y"])

		when:
		def resultFirstAddTree = firstVarAddTree.evaluate(context)
		def resultLastAddTree = lastVarAddTree.evaluate(context)
		def resultTwoVarAddTree = twoVarAddTree.evaluate(context)
		def resultFirstSubTree = firstVarSubTree.evaluate(context)
		def resultLastSubTree = lastVarSubTree.evaluate(context)
		def resultTwoVarSubTree = twoVarSubTree.evaluate(context)
		def resultFirstMultTree = firstVarMultTree.evaluate(context)
		def resultLastMultTree = lastVarMultTree.evaluate(context)
		def resultTwoVarMultTree = twoVarMultTree.evaluate(context)
		def resultFirstDiviTree = firstVarDiviTree.evaluate(context)
		def resultLastDiviTree = lastVarDiviTree.evaluate(context)
		def resultTwoVarDiviTree = twoVarDiviTree.evaluate(context)

		then:
		resultFirstAddTree == 4
		resultLastAddTree == 4
		resultTwoVarAddTree == 9
		resultFirstSubTree == 2
		resultLastSubTree == -2
		resultTwoVarSubTree == -3
		resultFirstMultTree == 3
		resultLastMultTree == 3
		resultTwoVarMultTree == 18
		resultFirstDiviTree == 3
		resultLastDiviTree == 1
		resultTwoVarDiviTree == 1/2
	}
	
	@Test
	public void simpleSingleParameterOperatorTreeTest() {
		given:
		def context = ['x' : Math.PI / 2, 'y' : 10]
		def sinTree = new GpTree(["sin", 0])
		def sinVTree = new GpTree(["sin", "x"])
		def cosTree = new GpTree(["cos", 0])
		def cosVTree = new GpTree(["cos", "x"])
		def logTree = new GpTree(["log", Math.E])
		def logVTree = new GpTree(["log", 1])

		when:
		def resultSinTree = sinTree.evaluate(context)
		def resultSinVTree = sinVTree.evaluate(context)
		def resultCosTree = cosTree.evaluate(context)
		def resultCosVTree = cosVTree.evaluate(context)
		def resultLogTree = logTree.evaluate(context)
		def resultLogVTree = logVTree.evaluate(context)

		then:
		resultSinTree == 0
		resultSinVTree == 1
		resultCosTree == 1
//		resultCosVTree == 0
		resultLogTree == 1
		resultLogVTree == 0
	}
	
	@Test
	public void simpleThreeParameterOperatorTreeTest() {
		given:
		def context = [:]
		def gpifTree = new GpTree(["gpif", 1, 0, 2])
		def gpifZeroTree = new GpTree(["gpif", 0, 1, 2])
		def gpifNegativeTree = new GpTree(["gpif", -1, 1, 2])

		when:
		def resultGpifTree = gpifTree.evaluate(context)
		def resultGpifZeroTree = gpifZeroTree.evaluate(context)
		def resultGpifNegativeTree = gpifNegativeTree.evaluate(context)
		
		then:
		resultGpifTree == 0
		resultGpifZeroTree == 2
		resultGpifNegativeTree == 2
	}
	
	@Test
	public void threeOperatorConstantTreeTest() {
		given:
		def context = [:]
		def threeOperatorTree = new GpTree(["mult", "plus", 2, 3, "sub", 6, 5])
		def threeAdditionTree = new GpTree(["plus", "plus", 1, 2, "plus", 3, 4])

		when:
		def resultThreeOperatorTree = threeOperatorTree.evaluate(context)
		def resultThreeAdditionTree = threeAdditionTree.evaluate(context)

		then:
		resultThreeOperatorTree == 5
		resultThreeAdditionTree == 10
	}

	@Test
	public void threeOperatorVariableTreeTest() {
		given:
		def context = ['x':3, 'y':5]
		def threeOperatorTree = new GpTree(["mult", "plus", 2, 'x', "sub", 6, 'y'])
		def threeAdditionTree = new GpTree(["plus", "plus", 1, 2, "plus", 'x', 4])

		when:
		def resultThreeOperatorTree = threeOperatorTree.evaluate(context)
		def resultThreeAdditionTree = threeAdditionTree.evaluate(context)

		then:
		resultThreeOperatorTree == 5
		resultThreeAdditionTree == 10
	}

	@Test
	public void complexOperatorConstantTreeTest() {
		given:
		def context = [:]
		def threeOperatorTree = new GpTree(["mult", "plus", "plus", 1, 1, 3, "sub", 6, "divi", 10, 2])
		def threeAdditionTree = new GpTree(["plus", "plus", 1, "plus", 1, 1, "plus", "plus", 1, "plus", 1, 1, 4])

		when:
		def resultThreeOperatorTree = threeOperatorTree.evaluate(context)
		def resultThreeAdditionTree = threeAdditionTree.evaluate(context)

		then:
		resultThreeOperatorTree == 5
		resultThreeAdditionTree == 10
	}

	@Test
	public void complexOperatorVariableTreeTest() {
		given:
		def context = ['x': 2, 'y': 6, 'z': 1]
		def threeOperatorTree = new GpTree(["mult", "plus", "plus", 'z', 'z', 3, "sub", 'y', "divi", 10, 'x'])
		def threeAdditionTree =
		new GpTree(["plus", "plus", 'z', "plus", 'z', 'z', "plus", "plus", 'z', "plus", 'z', 'z', 4])

		when:
		def resultThreeOperatorTree = threeOperatorTree.evaluate(context)
		def resultThreeAdditionTree = threeAdditionTree.evaluate(context)

		then:
		resultThreeOperatorTree == 5
		resultThreeAdditionTree == 10
	}
	
	@Test
	public void diffArgLengthComplexOperatorVariableTreeTest() {
		given:
		def context = ['x': 2, 'y': 6, 'z': 1]
		def multOperatorTree = new GpTree(["sin", "mult", "sub", "plus", "sin", Math.PI/2, "cos", 0, "sub", 'y', "divi", 10, 'x', Math.PI/2])
		def complexMultOperatorTree =
		new GpTree(["gpif", "plus", "gpif", "sub", 'x', 'y', -1, 0, 1, "mult", "sin", Math.PI/2, 'z', "log", Math.E])

		when:
		def resultMultOperatorTree = multOperatorTree.evaluate(context)
		def resultComplexMultOperatorTree = complexMultOperatorTree.evaluate(context)

		then:
		resultMultOperatorTree == 1
		resultComplexMultOperatorTree == 1
	}
	
	// Need Protected Division
	@Test 
	public void divideByZero() {
		given:
		def context = ['x': 4, 'y': 0]
		def simpleCaseTree = new GpTree(["divi", 2, 0])
		def simpleVariableCaseTree = new GpTree(["divi", "x","y"])
		
		when:
		def resultsimpleCaseTree = simpleCaseTree.evaluate(context)
		def resultsimpleVariableCaseTree = simpleVariableCaseTree.evaluate(context)
		
		then:
		resultsimpleCaseTree == 1
		resultsimpleVariableCaseTree == 1
	}
	
	@Test
	public void logByNegative() {
		given:
		def context = ['x': 0,]
		def simpleCaseTree = new GpTree(["log", -10])
		def simpleVariableCaseTree = new GpTree(["log", "x"])
		
		when:
		def resultsimpleCaseTree = simpleCaseTree.evaluate(context)
		def resultsimpleVariableCaseTree = simpleVariableCaseTree.evaluate(context)
		
		then:
		resultsimpleCaseTree == -100000
		resultsimpleVariableCaseTree == -100000
	}
	
	@Test
	public void NaNTest() {
		given:
		def context = ['x': 4, 'y' : 6]
		def treex = new GpTree(["divi", "plus", "sin", "y", "mult", "y", "x", "mult", "sub", "mult", 0, "sin", -1, "log", "cos", "sub", "x", "x", "mult", 0, "x"])
		def leftSubTree = new GpTree(["plus", "sin", "y", "mult", "y", "x"])
		def rightSubTree = new GpTree(["mult", "sub", "mult", 0, "sin", -1, "log", "cos", "sub", "x", "x", "mult", 0, "x"])
		def treey = new GpTree(["divi", "cos", "divi", "y", "cos", "divi", "cos", "y", "x", "cos", "divi", "mult", "x", "x", "mult", -1, "plus", "sin", "y", "y"])
		
		when:
		def resultx = treex.evaluate(context)
		def resultLeftSubTree = leftSubTree.evaluate(context)
		def resultRightSubTree = rightSubTree.evaluate(context)
		def resulty = treey.evaluate(context)


		then:
		resultx == 1
		assert !Double.isNaN(resulty)
		resultLeftSubTree == 24 + Math.sin(6)
		Math.abs(resultRightSubTree) == 0
	}
}