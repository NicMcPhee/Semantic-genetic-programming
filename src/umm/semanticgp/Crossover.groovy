package umm.semanticgp

import java.util.Random;

// 90% or more crossover 1% mutation rest copy into next generation.
class Crossover {

    static Random rand = new Random()
    def percentage = rand.nextInt(100)

    static crossover(GpTree P1, GpTree P2) {
        def copyP1 = new GpTree(P1.nodes.clone())
        
        
        def l2opP1 = rand.nextInt(10)
        def l2opP2 = rand.nextInt(10)
        def parent1Leaves = []
        def parent2Leaves = []
        def parent1Operators = []
        def parent2Operators = []
        // adds indices of P1
        for (int i = 0; i < P1.nodes.size(); i++) {
            if (copyP1.nodes[i] instanceof Closure) {
                parent1Operators.add(i)
            }
            else {
                parent1Leaves.add(i)
            }
        }
        // adds indices of P2
        for (int i = 0; i < P2.nodes.size(); i++) {
            if (P2.nodes[i] instanceof Closure) {
                parent2Operators.add(i)
            }
            else {
                parent2Leaves.add(i)
            }
        }

        if (l2opP1 < 1) {
            def leafIndexToAdd = parent1Leaves[rand.nextInt(parent1Leaves.size())]
            copyP1.nodes.remove(leafIndexToAdd)
            
            if (l2opP2 < 1) {
                copyP1.nodes.addAll(leafIndexToAdd, P2.nodes[parent2Leaves[rand.nextInt(parent2Leaves.size())]])
            }
            else {
                copyP1.nodes.addAll(leafIndexToAdd,P2.findSubTree(parent2Operators[rand.nextInt(parent2Operators.size())]))
            }
        }
        else {
            def opIndexToAdd = parent1Operators[rand.nextInt(parent1Operators.size())]
            def endIndex = copyP1.findCrossoverParameters(opIndexToAdd) + 1
            copyP1.nodes.removeRange(opIndexToAdd,endIndex)
            if (l2opP2 < 1) {
                copyP1.nodes.addAll(opIndexToAdd, P2.nodes[parent2Leaves[rand.nextInt(parent2Leaves.size())]])
            }
            else {
                copyP1.nodes.addAll(opIndexToAdd,P2.findSubTree(parent2Operators[rand.nextInt(parent2Operators.size())]))
            }
        }
        return copyP1
    }
    
}
