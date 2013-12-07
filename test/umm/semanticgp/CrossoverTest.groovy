package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import umm.semanticgp.GpTree
import spock.lang.Specification

class CrossoverTest extends Specification {

    @Test
    public void simpleCrossOver() {
        given:
        def P1 = new GpTree([Operator.mult, 0, Operator.divi, 1, 2])
        def P2 = new GpTree([Operator.sub, 3, Operator.plus, 4, 5])
        
        when:
        def childTree = Crossover.crossover(P1,P2)
        println(P1.printGpTree())
        println(P2.printGpTree())
        println(childTree.printGpTree())
		
        then:
        assert (1..9).contains(childTree.nodes.size())
    }
    
    @Test
    public void longerCrossOver() {
        given:
        def P1 = new GpTree([Operator.mult, 0, Operator.divi, 1, Operator.log, Operator.gpif, 1, 3, 4])
        def P2 = new GpTree([Operator.sub, "y", Operator.plus, "z", Operator.gpif, "x", "w", Operator.cos, "v"])
        
        when:
        def childTree = Crossover.crossover(P1,P2)
        println(P1.printGpTree())
        println(P2.printGpTree())
        println(childTree.printGpTree())
		
        then:
        assert (1..17).contains(childTree.nodes.size())
    }
}