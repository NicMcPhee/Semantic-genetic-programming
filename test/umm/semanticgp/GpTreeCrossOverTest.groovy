package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.semanticgp.GpTree
import umm.semanticgp.Operator;

class GpTreeCrossOverTest extends Specification {

	@Test 
	public void findCrossOverParameters() {
		given: 
		def threeOperatorTree = new GpTree([Operator.mult, Operator.plus, Operator.plus, 'z', 'z', 3, Operator.sub, 'y', Operator.divi, 10, 'x'])
		
		when:
		def resultSingleCParameter = threeOperatorTree.findCrossoverParameters(5)
		def resultSingleVParameter = threeOperatorTree.findCrossoverParameters(4)
		def resultSingleOParameter = threeOperatorTree.findCrossoverParameters(8)
		def resultMultipleOParameter = threeOperatorTree.findCrossoverParameters(1)
		
		then:
		resultSingleCParameter == 5
		resultSingleVParameter == 4
		resultSingleOParameter == 10
		resultMultipleOParameter == 5
	}
	
	@Test
	public void findSubTrees() {
		given:

	def context = ['x': 2]
	def threeOperatorTree = new GpTree([Operator.mult, Operator.plus, Operator.plus, 'z', 'z', 3, Operator.sub, 'y', Operator.divi, 10, 'x'])

	when:
	def resultConstantSubTree = threeOperatorTree.findSubTree(5)
	def resultVariableSubTree = threeOperatorTree.findSubTree(4)
	def resultOneOpSubTree = threeOperatorTree.findSubTree(8)
	def resultMultOpSubTree = threeOperatorTree.findSubTree(1)
	
	then:
	resultConstantSubTree == threeOperatorTree.nodes[5..5]
	resultVariableSubTree == threeOperatorTree.nodes[4..4]
	resultOneOpSubTree == threeOperatorTree.nodes[8..10]
	resultMultOpSubTree == threeOperatorTree.nodes[1..5]
	}
	

}
