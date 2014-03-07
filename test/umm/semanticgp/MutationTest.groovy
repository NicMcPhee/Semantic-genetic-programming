package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import umm.semanticgp.GpTree
import spock.lang.Specification

class MutationTest {
	
	@Test
	public void simpleMutation() {
		given:
		def P1 = new GpTree(["plus", 3, "sub", 2, 1])
		def P1Evolver = new Evolver(["plus", "sub"], [], 0, 0, 4, 5, 4, 20)
		
		when:
		def childTree = Mutation.mutation(P1, P1Evolver).getTree()
		println(P1.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (5..11).contains(childTree.nodes.size())
	}
	
	@Test
	public void complexMutation() {
		given:
		def P1 = new GpTree(["gpif", 3, "sub", 2, 1, "mult", 1 , "sin", "divi", "cos", "x", 7])
		def P1Evolver = new Evolver(["gpif", "sub", "mult", "sin", "divi", "cos"], ['x'], 10, 0, 5, 8, 20, 15)
		
		when:
		def childTree = Mutation.mutation(P1, P1Evolver).getTree()
		println(P1.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (12..42).contains(childTree.nodes.size())
	}
	
	@Test
	public void manyOPMutation() {
		given:
		def P1 = new GpTree(["sin","sin","sin","sin","sin","sin","sin","sin","sin","sin","sin","x"])
		def P1Evolver = new Evolver(["gpif", "sub", "mult", "sin", "divi", "cos"], ['x'], 10, 0, 5, 8, 20, 15)
		
		when:
		def childTree = Mutation.mutation(P1, P1Evolver).getTree()
		println(P1.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (12..42).contains(childTree.nodes.size())
	}
}