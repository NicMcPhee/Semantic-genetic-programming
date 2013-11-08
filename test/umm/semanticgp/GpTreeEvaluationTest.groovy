package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.sematicgp.GpTree

class GpTreeEvaluationTest extends Specification {

	@Test
	public void constantTest() {
		given:
		def tree = new GpTree([5])
		
		when:
		def result = tree.evaluate()
		
		then:
		result == 5
		
	}

}
