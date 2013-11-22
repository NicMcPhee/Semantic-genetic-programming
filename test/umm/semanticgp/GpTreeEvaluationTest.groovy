package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.sematicgp.GpTree
import umm.sematicgp.Operator;
import umm.sematicgp.Ptc2

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
		def constantAddTree = new GpTree([Operator.plus, 0, 1])
		def constantSubTree = new GpTree([Operator.sub, 4, 1])
		def constantMultTree = new GpTree([Operator.mult, 2, 3])
		def constantDiviTree = new GpTree([Operator.divi, 6, 3])
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
		def firstVarAddTree = new GpTree ([Operator.plus, "x", 1])
		def lastVarAddTree = new GpTree ([Operator.plus, 1, "x"])
		def twoVarAddTree = new GpTree ([Operator.plus, "x" , "y"])
		def firstVarSubTree = new GpTree ([Operator.sub, "x", 1])
		def lastVarSubTree = new GpTree ([Operator.sub, 1, "x"])
		def twoVarSubTree = new GpTree ([Operator.sub, "x" , "y"])
		def firstVarMultTree = new GpTree ([Operator.mult, "x", 1])
		def lastVarMultTree = new GpTree ([Operator.mult, 1, "x"])
		def twoVarMultTree = new GpTree ([Operator.mult, "x" , "y"])
		def firstVarDiviTree = new GpTree ([Operator.divi, "x", 1])
		def lastVarDiviTree = new GpTree ([Operator.divi, 3, "x"])
		def twoVarDiviTree = new GpTree ([Operator.divi, "x" , "y"])

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
		def sinTree = new GpTree([Operator.sin, 0])
		def sinVTree = new GpTree([Operator.sin, "x"])
		def cosTree = new GpTree([Operator.cos, 0])
		def cosVTree = new GpTree([Operator.cos, "x"])
		def logTree = new GpTree([Operator.log, Math.E])
		def logVTree = new GpTree([Operator.log, 1])


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
		def gpifTree = new GpTree([Operator.gpif, 1, 0, 2])
		def gpifZeroTree = new GpTree([Operator.gpif, 0, 1, 2])
		def gpifNegativeTree = new GpTree([Operator.gpif, -1, 1, 2])


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
		def threeOperatorTree = new GpTree([Operator.mult, Operator.plus, 2, 3, Operator.sub, 6, 5])
		def threeAdditionTree = new GpTree([Operator.plus, Operator.plus, 1, 2, Operator.plus, 3, 4])

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
		def threeOperatorTree = new GpTree([Operator.mult, Operator.plus, 2, 'x', Operator.sub, 6, 'y'])
		def threeAdditionTree = new GpTree([Operator.plus, Operator.plus, 1, 2, Operator.plus, 'x', 4])

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
		def threeOperatorTree = new GpTree([Operator.mult, Operator.plus, Operator.plus, 1, 1, 3, Operator.sub, 6, Operator.divi, 10, 2])
		def threeAdditionTree = new GpTree([Operator.plus, Operator.plus, 1, Operator.plus, 1, 1, Operator.plus, Operator.plus, 1, Operator.plus, 1, 1, 4])

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
		def threeOperatorTree = new GpTree([Operator.mult, Operator.plus, Operator.plus, 'z', 'z', 3, Operator.sub, 'y', Operator.divi, 10, 'x'])
		def threeAdditionTree =
		new GpTree([Operator.plus, Operator.plus, 'z', Operator.plus, 'z', 'z', Operator.plus, Operator.plus, 'z', Operator.plus, 'z', 'z', 4])

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
		def multOperatorTree = new GpTree([Operator.sin, Operator.mult, Operator.sub, Operator.plus, Operator.sin, Math.PI/2, Operator.cos, 0, Operator.sub, 'y', Operator.divi, 10, 'x', Math.PI/2])
		def complexMultOperatorTree =
		new GpTree([Operator.gpif, Operator.plus, Operator.gpif, Operator.sub, 'x', 'y', -1, 0, 1, Operator.mult, Operator.sin, Math.PI/2, 'z', Operator.log, Math.E])

		when:
		def resultMultOperatorTree = multOperatorTree.evaluate(context)
		def resultComplexMultOperatorTree = complexMultOperatorTree.evaluate(context)

		then:
		resultMultOperatorTree == 1
		resultComplexMultOperatorTree == 1
	}
}