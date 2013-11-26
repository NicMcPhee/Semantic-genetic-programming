package umm.sematicgp
import java.util.Random
class Ptc2 {
	
	static Ops = []
//	def Vars
	def Ptc2(Ops/*Vars*/) {
		this.Ops = Ops
	//	this.Vars = Vars
	}

	def generateTree(size, constantRange) {
		def randomTree = new GpTree([])
		Random rand = new Random()
		
		if (size == 1) {
			randomTree[0] = rand.nextInt(constantRange)
			return randomTree
		} else {
			def indexArr = []
			def randomOperator = randomOperators()
			randomTree.nodes[0] = randomOperator
			//def count = 1
			def i = 0;
			for (i; i < Operator.numArgs(randomOperator); i++) {
				randomTree.nodes[i + 1] = "ERC"
				indexArr[i] = i + 1
			}
			for (def m = 0; m < randomTree.nodes.size(); m++) {
				System.out.println(randomTree.nodes[m])
			}
			println " "
			while (/*count + */randomTree.nodes.size() < size) {
				print "la"
				def anotherOperator = randomOperators()
				//count += 1
				def randomNodeIndex = rand.nextInt(indexArr.size())
				def indexInGpTree = indexArr[randomNodeIndex]
				randomTree.nodes[indexInGpTree] = anotherOperator
				def ercArr = []
				for(int j = 0; j < Operator.numArgs(anotherOperator); j++) {
					ercArr[j] = "ERC"
					println "IndexArrErcAdd"
					for (def m = 0; m < indexArr.size(); m++) {
						System.out.println(indexArr[m])
					}
				}			
				randomTree.nodes.addAll(indexInGpTree + 1, ercArr)
				indexArr.clear()
				for (def k = 0; k < randomTree.nodes.size(); k++) {
					if (randomTree.nodes[k] == "ERC") {
						indexArr[indexArr.size()] = k
					}
				}
				for (def m = 0; m < 15; m++) {
					System.out.println(randomTree.nodes[m])
				}
				println " "
			}
			println "IndexArr"
			for (def m = 0; m < indexArr.size(); m++) {
				System.out.println(indexArr[m])
			}
		
			for (int k = 0; k < indexArr.size(); k++) {
				randomTree.nodes[indexArr[k]] = rand.nextInt(constantRange)
				for (def m = 0; m < randomTree.nodes.size(); m++) {
					System.out.println(randomTree.nodes[m])
				}
				println " "
			}

			for (def m = 0; m < randomTree.nodes.size(); m++) {
				System.out.println(randomTree.nodes[m])
			}
			return randomTree
		}
	}
	
	static randomOperators() {
		
		Random rand = new Random()
		return Ops[rand.nextInt(Ops.size())]
		
	}
}
