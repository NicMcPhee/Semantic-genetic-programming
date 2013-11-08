package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.sematicgp.GpTree
import umm.sematicgp.Operator;

class GpTreeEvaluationTest extends Specification {
	def plus = { x, y -> x + y }
	def sub =  { x, y -> x - y }
	def mult =  { x, y -> x * y }
	def divi =  { x, y -> x / y }
	

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
		def constantAddTree = new GpTree([plus, 0, 1])
		def constantSubTree = new GpTree([sub, 4, 1])
		def constantMultTree = new GpTree([mult, 2, 3])
		def constantDiviTree = new GpTree([divi, 6, 3])
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
		def firstVarAddTree = new GpTree ([plus, "x", 1])
		def lastVarAddTree = new GpTree ([plus, 1, "x"])
		def twoVarAddTree = new GpTree ([plus, "x" , "y"])
		def firstVarSubTree = new GpTree ([sub, "x", 1])
		def lastVarSubTree = new GpTree ([sub, 1, "x"])
		def twoVarSubTree = new GpTree ([sub, "x" , "y"])
		def firstVarMultTree = new GpTree ([mult, "x", 1])
		def lastVarMultTree = new GpTree ([mult, 1, "x"])
		def twoVarMultTree = new GpTree ([mult, "x" , "y"])
		def firstVarDiviTree = new GpTree ([divi, "x", 1])
		def lastVarDiviTree = new GpTree ([divi, 3, "x"])
		def twoVarDiviTree = new GpTree ([divi, "x" , "y"])
		
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

}
