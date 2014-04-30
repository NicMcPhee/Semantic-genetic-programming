package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test
import spock.lang.Specification
import umm.semanticgp.GpTree
import umm.semanticgp.Operator

class ToStringTest extends Specification {
	
	@Test
	public void PrintOperationsTest() {
		given:
		def context = [:]
		def plusTree = new GpTree(["plus", 1, 1])
		def subTree = new GpTree(["sub", 1, 1])
		def multTree = new GpTree(["mult", 1, 1])
		def diviTree = new GpTree(["divi", 1, 1])
		def sinTree = new GpTree(["sin", 1])
		def cosTree = new GpTree(["cos", 1])
		def logTree = new GpTree(["log", 1])
		def gpifTree = new GpTree(["gpif", 1, 1, 0])
		
		when:
		def plusPrint = plusTree.printGpTree()
		def subPrint = subTree.printGpTree()
		def multPrint = multTree.printGpTree()
		def diviPrint = diviTree.printGpTree()
		def sinPrint = sinTree.printGpTree()
		def cosPrint = cosTree.printGpTree()
		def logPrint = logTree.printGpTree()
		def gpifPrint = gpifTree.printGpTree()
		
		then:
		plusPrint == "[+, 1, 1]"
		subPrint == "[-, 1, 1]"
		multPrint == "[*, 1, 1]"
		diviPrint == "[/, 1, 1]"
		sinPrint == "[sin, 1]"
		cosPrint == "[cos, 1]"
		logPrint == "[log, 1]"
		gpifPrint == "[if, 1, 1, 0]"
	}

	@Test
	public void PrintTest() {
		given:
		def context = [:]
		def cTree = new GpTree([5,3,2,3])
		def vTree = new GpTree(["x","y","z"])
		def opTree = new GpTree(["plus", "sin","z", 1])

		when:
		def resultConstantPrint = cTree.printGpTree()
		def resultVariablePrint = vTree.printGpTree()
		def resultSimpleOpPrint = opTree.printGpTree()
		
		then:
		resultConstantPrint == "[5, 3, 2, 3]"
		resultVariablePrint == "[x, y, z]"
		resultSimpleOpPrint == "[+, sin, z, 1]"
	}
}