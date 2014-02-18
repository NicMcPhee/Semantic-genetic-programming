package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import umm.semanticgp.GpTree
import spock.lang.Specification

class CrossoverTest extends Specification {

    @Test
    public void simpleCrossOver() {
        given:
        def P1 = new GpTree(["mult", 0, "divi", 1, 2])
        def P2 = new GpTree(["sub", 3, "plus", 4, 5])
        
        when:
        def childTree = Crossover.crossover(P1,P2).getTree()
        println(P1.printGpTree())
        println(P2.printGpTree())
        println(childTree.printGpTree())
		
        then:
        assert (1..9).contains(childTree.nodes.size())
    }
    
    @Test
    public void longerCrossOver() {
        given:
        def P1 = new GpTree(["mult", 0, "divi", 1, "log", "gpif", 1, 3, 4])
        def P2 = new GpTree(["sub", "y", "plus", "z", "gpif", "x", "w", "cos", "v"])
        
        when:
        def childTree = Crossover.crossover(P1,P2).getTree()
        println(P1.printGpTree())
        println(P2.printGpTree())
        println(childTree.printGpTree())
		
        then:
        assert (1..17).contains(childTree.nodes.size())
    }
	
	@Test
	public void singleNodeP1Crossover() {
		given:
		def P1 = new GpTree([1])
		def P2 = new GpTree(["sub", "y", "plus", "z", "gpif", "x", "w", "cos", "v"])
		
		when:
		def childTree = Crossover.crossover(P1,P2).getTree()
		println("parent 1 is single node")
		println(P1.printGpTree())
		println(P2.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (1..17).contains(childTree.nodes.size())
	}
	
	@Test
	public void singleNodeP2Crossover() {
		given:
		def P1 =  new GpTree(["sub", "y", "plus", "z", "gpif", "x", "w", "cos", "v"])
		def P2 = new GpTree([1])
		
		when:
		def childTree = Crossover.crossover(P1,P2).getTree()
		println("parent 2 is single node")
		println(P1.printGpTree())
		println(P2.printGpTree())
		println(childTree.printGpTree())
		
		then:
		assert (1..17).contains(childTree.nodes.size())
	}
}