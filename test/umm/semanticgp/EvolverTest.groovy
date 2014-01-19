package umm.semanticgp

import static org.junit.Assert.*
import org.junit.Test

class EvolverTest {

	@Test
	public void initialPopTest() {
		given:
		def initialPop = new Evolver([Operator.plus], ["x","y"], 80, 0, 10, 20, 5, 20)
		when:
		initialPop.initialPop()
		println(initialPop.Population[0].getTree().printGpTree())
		println(initialPop.Population[1].getTree().printGpTree())
		println(initialPop.Population[2].getTree().printGpTree())
		println(initialPop.Population[3].getTree().printGpTree())
		println(initialPop.Population[4].getTree().printGpTree())
		println()
		then:
		assert (20..21).contains(initialPop.Population[0].getTree().size())
		assert (20..21).contains(initialPop.Population[1].getTree().size())
		assert (20..21).contains(initialPop.Population[2].getTree().size())
		assert (20..21).contains(initialPop.Population[3].getTree().size())
		assert (20..21).contains(initialPop.Population[4].getTree().size())
		assert (initialPop.Population.size() == 5)
	}

	@Test
	public void mutationTypesTest() {
		given:
		def initialPop = new Evolver([Operator.plus], ["x","y"], 80, 0, 10, 20, 4, 20)
		when:
		initialPop.initialPop()
		println()
		println(initialPop.Population[0].getTree().printGpTree())
		println(initialPop.Population[1].getTree().printGpTree())
		println(initialPop.Population[2].getTree().printGpTree())
		println(initialPop.Population[3].getTree().printGpTree())
		initialPop.mutationType(90)
		println()
		println(initialPop.Population[0].getTree().printGpTree())
		println(initialPop.Population[1].getTree().printGpTree())
		println(initialPop.Population[2].getTree().printGpTree())
		println(initialPop.Population[3].getTree().printGpTree())
		println()
		then:
		assert (initialPop.Population.size() == 4)
	}
	
	@Test
	public void evolveTest() {
		given:
		def initialPop = new Evolver([Operator.plus], ["x","y"], 80, 0, 10, 20, 4, 3)
		when:
		initialPop.evolve(90)
		println(initialPop.Population[0].getTree().printGpTree())
		println(initialPop.Population[1].getTree().printGpTree())
		println(initialPop.Population[2].getTree().printGpTree())
		println(initialPop.Population[3].getTree().printGpTree())
		then:
		assert (initialPop.Population.size() == 4)
	}
}