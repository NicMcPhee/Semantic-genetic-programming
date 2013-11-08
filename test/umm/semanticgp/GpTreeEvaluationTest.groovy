package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.sematicgp.GpTree

class GpTreeEvaluationTest extends Specification {

	@Test
	public void constantTest() {
		given:
		def tree5 = new GpTree([5])
		def tree7 = new GpTree([7])
		
		when:
		def result5 = tree5.evaluate()
		def result7 = tree7.evaluate()
		
		then:
		result5 == 5
		result7 == 7
		
	}
	


}
