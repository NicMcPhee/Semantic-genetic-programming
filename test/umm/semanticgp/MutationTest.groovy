package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import umm.semanticgp.GpTree
import spock.lang.Specification

class MutationTest {
	
	@Test
	public void simpleMutation() {
		given:
		def P1 = new GpTree([Operator.plus, 3, Operator.sub, 2, 1])
		def P1Evolver = new Evolver([Operator.plus, Operator.sub], [], 0, 0, 4, 5, 4, 20)
		
		when:
		def childTree = Mutation.mutation(P1, P1Evolver)
		println(P1.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (5..11).contains(childTree.nodes.size())
	}
	
	@Test
	public void complexMutation() {
		given:
		def P1 = new GpTree([Operator.gpif, 3, Operator.sub, 2, 1, Operator.mult, 1 , Operator.sin, Operator.divi, Operator.cos, "x", 7])
		def P1Evolver = new Evolver([Operator.gpif, Operator.sub, Operator.mult, Operator.sin, Operator.divi, Operator.cos], ['x'], 10, 0, 5, 8, 20, 15)
		
		when:
		def childTree = Mutation.mutation(P1, P1Evolver)
		println(P1.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (12..42).contains(childTree.nodes.size())
	}
}