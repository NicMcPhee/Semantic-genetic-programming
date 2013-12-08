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
		
		when:
		def childTree = Mutation.mutation(P1)
		
		then:
		assert (5..11).contains(childTree.nodes.size())
	}
	
	@Test
	public void complexMutation() {
		given:
		def P1 = new GpTree([Operator.gpif, 3, Operator.sub, 2, 1, Operator.mult, 1 , Operator.sin, Operator.divi, Operator.cos, "x", 7])
		
		when:
		def childTree = Mutation.mutation(P1)
		then:
		assert (12..42).contains(childTree.nodes.size())
	}
}