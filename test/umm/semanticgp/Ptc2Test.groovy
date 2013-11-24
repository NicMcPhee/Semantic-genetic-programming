package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.sematicgp.GpTree
import umm.sematicgp.Operator;
import umm.sematicgp.Ptc2

class Ptc2Test extends Specification {

	@Test
	public void randomTree() {
		given:
		def treegen = new Ptc2()
		
		when:
		def randomTree = treegen.generateTree(13,2)
		
		then:
		assert (6..8).contains(randomTree.nodes.size())
		randomTree.printGpTree()
	}
}
