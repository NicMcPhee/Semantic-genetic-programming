package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test

class EvolverTest {

	@Test
	public void initialPopTest() {
		given:
		def smallPop = new Evolver(["plus"], ["x","y"], 80, 0, 10, 20, 5, 20)
		smallPop.TestPointsList = [
		[['x': 0, 'y': 0], 0],
		[['x': 0, 'y': 1], 1],
		[['x': 1, 'y': 0], 1],
		[['x': 1, 'y': 1], 2],
		[['x': 1, 'y': 2], 3],
		[['x': 2, 'y': 1], 3],
		[['x': 2, 'y': 2], 4],
		[['x': 2, 'y': 3], 5],
		[['x': 3, 'y': 2], 5],
		[['x': 3, 'y': 3], 6],
		[['x': 3, 'y': 4], 7],
		[['x': 4, 'y': 3], 7],
		[['x': 4, 'y': 4], 8],
		[['x': 4, 'y': 5], 9],
		[['x': 5, 'y': 4], 9],
		[['x': 5, 'y': 5], 10],
		[['x': 5, 'y': 6], 11],
		[['x': 6, 'y': 5], 11],
		[['x': 6, 'y': 6], 12],
		[['x': 6, 'y': 7], 13],
		[['x': 7, 'y': 6], 13]
	]
		when:
		smallPop.initialPop()
		smallPop.printFitnessAndTree()
		println(smallPop.Population[0].getTree().printGpTree())
		println(smallPop.Population[1].getTree().printGpTree())
		println(smallPop.Population[2].getTree().printGpTree())
		println(smallPop.Population[3].getTree().printGpTree())
		println(smallPop.Population[4].getTree().printGpTree())
		println()
		then:
		assert (20..21).contains(smallPop.Population[0].getTree().size())
		assert (20..21).contains(smallPop.Population[1].getTree().size())
		assert (20..21).contains(smallPop.Population[2].getTree().size())
		assert (20..21).contains(smallPop.Population[3].getTree().size())
		assert (20..21).contains(smallPop.Population[4].getTree().size())
		assert (smallPop.Population.size() == 5)
	}

	@Test
	public void transformationTypesTest() {
		given:
		def smallPop = new Evolver(["plus"], ["x","y"], 80, 0, 10, 20, 4, 20)
		smallPop.TestPointsList = [
		[['x': 0, 'y': 0], 0],
		[['x': 0, 'y': 1], 1],
		[['x': 1, 'y': 0], 1],
		[['x': 1, 'y': 1], 2],
		[['x': 1, 'y': 2], 3],
		[['x': 2, 'y': 1], 3],
		[['x': 2, 'y': 2], 4],
		[['x': 2, 'y': 3], 5],
		[['x': 3, 'y': 2], 5],
		[['x': 3, 'y': 3], 6],
		[['x': 3, 'y': 4], 7],
		[['x': 4, 'y': 3], 7],
		[['x': 4, 'y': 4], 8],
		[['x': 4, 'y': 5], 9],
		[['x': 5, 'y': 4], 9],
		[['x': 5, 'y': 5], 10],
		[['x': 5, 'y': 6], 11],
		[['x': 6, 'y': 5], 11],
		[['x': 6, 'y': 6], 12],
		[['x': 6, 'y': 7], 13],
		[['x': 7, 'y': 6], 13]
	]
		when:
		smallPop.initialPop()
		println("TRANSFORMATION TYPE TEST")
		println(smallPop.Population[0].getTree().printGpTree())
		println(smallPop.Population[1].getTree().printGpTree())
		println(smallPop.Population[2].getTree().printGpTree())
		println(smallPop.Population[3].getTree().printGpTree())
		smallPop.generateNewGeneration(90, 1)
		println()
		println(smallPop.Population[0].getTree().printGpTree())
		println(smallPop.Population[1].getTree().printGpTree())
		println(smallPop.Population[2].getTree().printGpTree())
		println(smallPop.Population[3].getTree().printGpTree())
		println()
		then:
		assert (smallPop.Population.size() == 4)
	}
	
	@Test
	public void evolveTest() {
		given:
		def smallPop = new Evolver(["plus"], ["x","y"], 80, 0, 10, 20, 4, 3)
		smallPop.TestPointsList = [
		[['x': 0, 'y': 0], 0],
		[['x': 0, 'y': 1], 1],
		[['x': 1, 'y': 0], 1],
		[['x': 1, 'y': 1], 2],
		[['x': 1, 'y': 2], 3],
		[['x': 2, 'y': 1], 3],
		[['x': 2, 'y': 2], 4],
		[['x': 2, 'y': 3], 5],
		[['x': 3, 'y': 2], 5],
		[['x': 3, 'y': 3], 6],
		[['x': 3, 'y': 4], 7],
		[['x': 4, 'y': 3], 7],
		[['x': 4, 'y': 4], 8],
		[['x': 4, 'y': 5], 9],
		[['x': 5, 'y': 4], 9],
		[['x': 5, 'y': 5], 10],
		[['x': 5, 'y': 6], 11],
		[['x': 6, 'y': 5], 11],
		[['x': 6, 'y': 6], 12],
		[['x': 6, 'y': 7], 13],
		[['x': 7, 'y': 6], 13]
	]
		when:
		println("evolveTest")
		smallPop.evolve(90)
		println(smallPop.Population[0].getTree().printGpTree())
		println(smallPop.Population[1].getTree().printGpTree())
		println(smallPop.Population[2].getTree().printGpTree())
		println(smallPop.Population[3].getTree().printGpTree())
		then:
		assert (smallPop.Population.size() == 4)
	}
}