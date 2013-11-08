package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.sematicgp.GpTree

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


}
