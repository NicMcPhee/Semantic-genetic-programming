package umm.sematicgp

class Ptc2 {

	def ptc2(size, variableRange) {
		def randomTree = new GpTree([])
		Random rand = new Random()
		
		if (size == 1) {
			randomTree[0] = rand.nextInt(variableRange)
			return randomTree
		} else {
			def indexArr = []
			def randomOperator = Operator.random()
			randomTree[0] = randomOperator
			def count = 1
			def i = 0;
			for (i; i < numArgs(randomOperator); i++) {
				randomTree[i + 1] = "ERC"
				indexArr[i] = i + 1
			}
			while (count + randomTree.size() < size) {
				def anotherOperator = Operator.random()
				count += 1
				def indexInGpTree = indexArr[rand.nextInt(indexArr.size())]
				randomTree[indexInGpTree] = anotherOperator
				def ercArr = []
				for(int j = 0; j < numArgs(randomOperator); j++) {
					ercArr[j] = "ERC"
					indexArr[indexArr.size] = indexInGpTree + j + 1
				}
				randomTree = randomTree.plus(indexInGpTree + 1, ercArr)
			}
			for (int k = 0; k < indexArr.size(); k++) {
				randomTree[indexArr[k]] = rand.nextInt(variableRange)
			}
			for (def m = 0; m < randomTree.size(); m++) {
				System.out.println(randomTree[m])
			}
			return randomTree
		}
	}
}
