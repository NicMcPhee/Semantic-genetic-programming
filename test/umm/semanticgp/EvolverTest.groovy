package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

class EvolverTest {

	@Test
	public void initialPopTest() {
		given:
		def initialPop = new Evolver([Operator.plus], ["x","y"], 80, 10, 20, 5, 20)
		when:
		initialPop.initialPop()
		System.out.println(initialPop.Population[0].printGpTree())
		System.out.println(initialPop.Population[1].printGpTree())
		System.out.println(initialPop.Population[2].printGpTree())
		System.out.println(initialPop.Population[3].printGpTree())
		System.out.println(initialPop.Population[4].printGpTree())
		
		then:
		assert (20..21).contains(initialPop.Population[0].size())
		assert (20..21).contains(initialPop.Population[1].size())
		assert (20..21).contains(initialPop.Population[2].size())
		assert (20..21).contains(initialPop.Population[3].size())
		assert (20..21).contains(initialPop.Population[4].size())
		assert (initialPop.Population.size() == 5)
		
		
	}

}
