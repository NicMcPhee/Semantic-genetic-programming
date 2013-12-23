package umm.semanticgp;

import static org.junit.Assert.*;

import org.junit.Test;

class TourneyTest {

	@Test
	public void testFitness() {
		given:
		def candidate1 = new GpTree([
			Operator.plus,
			"x",
			Operator.plus,
			"y",
			1
		])
		def candidate2 = new GpTree([
			Operator.plus,
			"y",
			Operator.mult,
			"x",
			1
		])
		when:

		def xplusyFitness = new Fitness([
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
		])
		def cand1Fitness = xplusyFitness.computeFitness(candidate1)
		def cand2Fitness = xplusyFitness.computeFitness(candidate2)
		then:
		assert cand1Fitness == 21
		assert cand2Fitness == 0
	}
	@Test
	public void testTourney() {
		given:
		def candidate1 = new GpTree([
			Operator.plus,
			"x",
			Operator.plus,
			"y",
			1
		])
		def candidate2 = new GpTree([
			Operator.plus,
			"y",
			Operator.mult,
			"x",
			1
		])
		def P1Evolver = new Evolver([Operator.plus, Operator.sub], [], 0, 4, 5, 20, 15)
		when:
		P1Evolver.FitnessList = [
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
		def parent = Tourney.Tournament([candidate1, candidate2],2)
		def equals = candidate2.equals(parent)
		then:
		assert equals
	}
}
