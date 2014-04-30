package umm.semanticgp

import org.junit.Test;

class IndividualCloneTest {
	@Test
	public void simpleCloneTest() {
		given:
		def parentTree = new GpTree(["plus", 1, 2])
		def parent = new Individual(parentTree)
		
		when:
		parent.setFitness(13)
		
		def child = parent.clone()
		
		
	
		then:
		println(parent)
		println(parent.getUid())
		println(child)
		println(child.getUid())
		assert (parent.getUid() != child.getUid())
	}
	
	@Test
	public void AnotherCloneTest() {
		given:
		def parentTree = new GpTree(["mult", "sin","x", 2])
		def parent = new Individual(parentTree)
		
		when:
		parent.setFitness(10)
		
		def child = parent.clone()
		
		
	
		then:
		println(parent)
		println(parent.getUid())
		println(child)
		println(child.getUid())
		assert (parent.getUid() != child.getUid())
	}

}
