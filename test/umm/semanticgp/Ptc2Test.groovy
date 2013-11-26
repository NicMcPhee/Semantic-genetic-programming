package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.sematicgp.GpTree
import umm.sematicgp.Operator;
import umm.sematicgp.Ptc2

class Ptc2Test extends Specification {
	
//	@Test 
//	public void randomOpTest ()

	@Test
	public void doubleOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.plus, Operator.sub, Operator.mult, Operator.divi ])
		
		when:
		def randomTree = treegen.generateTree(6,2)
		System.out.println(randomTree.printGpTree())
		then:
		assert (6..7).contains(randomTree.nodes.size())
		
	}
	@Test
	public void singleOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.sin, Operator.cos, Operator.log])
		
		when:
		def randomTree = treegen.generateTree(7,2)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..7).contains(randomTree.nodes.size())
		
	}
	@Test
	public void tripleOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.gpif])
		
		when:
		def randomTree = treegen.generateTree(7,2)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..9).contains(randomTree.nodes.size())
		
	}
	@Test
	public void mixOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.gpif, Operator.plus, Operator.sub])
		
		when:
		def randomTree = treegen.generateTree(7,2)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..9).contains(randomTree.nodes.size())
		
	}
	public void largeAllOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.plus, Operator.sub, Operator.mult, Operator.divi, Operator.sin, Operator.cos, Operator.log, Operator.gpif])
		
		when:
		def randomTree = treegen.generateTree(30,2)
		System.out.println(randomTree.printGpTree())
		then:
		assert (30..32).contains(randomTree.nodes.size())
		
	}
}
