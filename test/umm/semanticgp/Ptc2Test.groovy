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
		def treegen = new Ptc2([Operator.plus, Operator.sub, Operator.mult, Operator.divi], [] , 0, 2)
		
		when:
		def randomTree = treegen.generateTree(6)
		System.out.println(randomTree.printGpTree())
		then:
		assert (6..7).contains(randomTree.nodes.size())
		
	}
	@Test
	public void singleOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.sin, Operator.cos, Operator.log], [] , 0, 2)
		
		when:
		def randomTree = treegen.generateTree(7)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..7).contains(randomTree.nodes.size())
		
	}
	@Test
	public void tripleOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.gpif], [] , 0, 2)
		
		when:
		def randomTree = treegen.generateTree(7)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..9).contains(randomTree.nodes.size())
		
	}
	@Test
	public void mixOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.gpif, Operator.plus, Operator.sub], [] , 0, 2)
		
		when:
		def randomTree = treegen.generateTree(7)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..9).contains(randomTree.nodes.size())
		
	}
	@Test
	public void largeAllOpRandomTree() {
		given:
		def treegen = new Ptc2([Operator.plus, Operator.sub, Operator.mult, Operator.divi, Operator.sin, Operator.cos, Operator.log, Operator.gpif], [] , 0, 2)
		
		when:
		def randomTree = treegen.generateTree(30)
		System.out.println(randomTree.printGpTree())
		then:
		assert (30..32).contains(randomTree.nodes.size())
		
	}
	
	@Test
	public void simpleVariableRandomTree() {
		given:
		def treegen = new Ptc2([Operator.plus, Operator.sub], ["x"] , 50, 2)
		
		when:
		def randomTree = treegen.generateTree(7)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..8).contains(randomTree.nodes.size())
		
	}
	@Test
	public void simpleTwoVariableRandomTree() {
		given:
		def treegen = new Ptc2([Operator.plus, Operator.sub], ["x","y"] , 50, 2)
		
		when:
		def randomTree = treegen.generateTree(7)
		System.out.println(randomTree.printGpTree())
		then:
		assert (7..8).contains(randomTree.nodes.size())
		
	}
	@Test
	public void simpleFiveVariableRandomTree() {
		given:
		def treegen = new Ptc2([Operator.plus, Operator.sub, Operator.mult, Operator.sin, Operator.gpif], ["x","y","z","w","v"] , 50, 2)
		
		when:
		def randomTree = treegen.generateTree(15)
		System.out.println(randomTree.printGpTree())
		then:
		assert (15..17).contains(randomTree.nodes.size())
		
	}
}
