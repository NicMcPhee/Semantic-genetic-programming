package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import umm.semanticgp.GpTree
import spock.lang.Specification

class MutationTest {
	
	@Test
	public void simpleMutation() {
		given:
		def P1Tree = new GpTree(["plus", 3, "sub", 2, 1])
		def P1 = new Individual(P1Tree)
		def P1Evolver = new Evolver(["plus", "sub"], [], 0, 0, 4, 5, 4, 20)
		
		when:
		println("mutationTest")
		def (childTree,_) = Mutation.mutation(P1, P1Evolver)
		println(P1.getTree().printGpTree())
		println(childTree.getTree().printGpTree())
		
		then:
		assert (3..11).contains(childTree.getTree().nodes.size())
	}
	
	@Test
	public void complexMutation() {
		given:
		def P1Tree = new GpTree(["gpif", 3, "sub", 2, 1, "mult", 1 , "sin", "divi", "cos", "x", 7])
		def P1 = new Individual(P1Tree)
		def P1Evolver = new Evolver(["gpif", "sub", "mult", "sin", "divi", "cos"], ['x'], 10, 0, 5, 8, 20, 15)
		
		when:
		def (childTree,_) = Mutation.mutation(P1, P1Evolver)
		println(P1.getTree().printGpTree())
		println(childTree.getTree().printGpTree())
		
		then:
		assert (4..42).contains(childTree.getTree().nodes.size())
	}
	
	@Test
	public void manyOPMutation() {
		given:
		def P1Tree = new GpTree(["sin","sin","sin","sin","sin","sin","sin","sin","sin","sin","sin","x"])
		def P1 = new Individual(P1Tree)
		def P1Evolver = new Evolver(["gpif", "sub", "mult", "sin", "divi", "cos"], ['x'], 10, 0, 5, 8, 20, 15)
		
		when:
		def (childTree,_) = Mutation.mutation(P1, P1Evolver)
		println(P1.getTree().printGpTree())
		println(childTree.getTree().printGpTree())
		
		then:
		assert (2..42).contains(childTree.getTree().nodes.size())
	}
}