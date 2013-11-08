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
		def context = ['x' : 3]
		def variableAddTree = new GpTree ([plus, "x", 1])
		
		when:
		def resultAddTree = variableAddTree.evaluate(context)
		
		then:
		resultAddTree == 4
		
	}

}
