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
		def treegen = new Ptc2()
		def randomTree = treegen.generateTree(10,10)
		
	}
}
