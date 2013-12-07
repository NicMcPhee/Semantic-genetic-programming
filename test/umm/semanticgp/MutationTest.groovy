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
		println(P1.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (5..6).contains(childTree.nodes.size())
	}
}