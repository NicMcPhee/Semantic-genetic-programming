package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import umm.semanticgp.GpTree
import spock.lang.Specification

class CrossoverTest extends Specification {

    @Test
    public void simpleCrossOver() {
        given:
        def P1Tree = new GpTree(["mult", 0, "divi", 1, 2])
		def P1 = new Individual(P1Tree)
        def P2Tree = new GpTree(["sub", 3, "plus", 4, 5])
		def P2 = new Individual(P1Tree)
        
        when:
        def (childTree,_) = Crossover.crossover(P1,P2)
        println(P1.getTree().printGpTree())
        println(P2.getTree().printGpTree())
        println(childTree.getTree().printGpTree())
		
        then:
        assert (1..9).contains(childTree.getTree().nodes.size())
    }
    
    @Test
    public void longerCrossOver() {
        given:
        def P1Tree = new GpTree(["mult", 0, "divi", 1, "log", "gpif", 1, 3, 4])
		def P1 = new Individual(P1Tree)
        def P2Tree = new GpTree(["sub", "y", "plus", "z", "gpif", "x", "w", "cos", "v"])
		def P2 = new Individual(P2Tree)
        
        when:
        def (childTree,_) = Crossover.crossover(P1,P2)
        println(P1.getTree().printGpTree())
        println(P2.getTree().printGpTree())
        println(childTree.getTree().printGpTree())
		
        then:
        assert (1..17).contains(childTree.getTree().nodes.size())
    }
	
	@Test
	public void singleNodeP1Crossover() {
		given:
		def P1Tree = new GpTree([1])
		def P1 = new Individual(P1Tree)
		def P2Tree = new GpTree(["sub", "y", "plus", "z", "gpif", "x", "w", "cos", "v"])
		def P2 = new Individual(P2Tree)
		when:
		def (childTree,_) = Crossover.crossover(P1,P2)
		println("parent 1 is single node")
		println(P1.getTree().printGpTree())
		println(P2.getTree().printGpTree())
		println(childTree.getTree().printGpTree())
		
		then:
		assert (1..17).contains(childTree.getTree().nodes.size())
	}
	
	@Test
	public void singleNodeP2Crossover() {
		given:
		def P1Tree =  new GpTree(["sub", "y", "plus", "z", "gpif", "x", "w", "cos", "v"])
		def P1 = new Individual(P1Tree)
		def P2Tree = new GpTree([1])
		def P2 = new Individual(P2Tree)
		when:
		def (childTree,_) = Crossover.crossover(P1,P2)
		println("parent 2 is single node")
		println(P1.getTree().printGpTree())
		println(P2.getTree().printGpTree())
		println(childTree.getTree().printGpTree())
		
		then:
		assert (1..17).contains(childTree.getTree().nodes.size())
	}
}