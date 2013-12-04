package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

import spock.lang.Specification;
import umm.semanticgp.GpTree
import umm.semanticgp.Operator;

class ToStringTest extends Specification {
	
	@Test
	public void PrintOperationsTest() {
		given:
		def context = [:]
		def plusTree = new GpTree([Operator.plus, 1, 1])
		def subTree = new GpTree([Operator.sub, 1, 1])
		def multTree = new GpTree([Operator.mult, 1, 1])
		def diviTree = new GpTree([Operator.divi, 1, 1])
		def sinTree = new GpTree([Operator.sin, 1])
		def cosTree = new GpTree([Operator.cos, 1])
		def logTree = new GpTree([Operator.log, 1])
		def gpifTree = new GpTree([Operator.gpif, 1, 1, 0])
		
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
		def opTree = new GpTree([Operator.plus, Operator.sin,"z", 1])

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
