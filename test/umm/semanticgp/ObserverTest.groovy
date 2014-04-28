package umm.semanticgp
	import static org.junit.Assert.*
	import org.junit.Test
import spock.lang.Specification
import umm.semanticgp.GpTree
import umm.semanticgp.Individual
import umm.semanticgp.LocalDBObserver
import umm.semanticgp.Neo4jObserverNotifier

class ObserverTest extends Specification {
	
		@Test
		public void simpleObserver() {
			given:
				Neo4jObserverNotifier neo4j = new Neo4jObserverNotifier();
				LocalDBObserver observer = new LocalDBObserver(neo4j);
				def parent1GpTree = new GpTree(["plus","sub", 1, 2, 3]);
				def parent2GpTree = new GpTree(["sub","plus", 3, 2, 1]);
				def childGpTree = new GpTree(["mult","divi", 4, 5, 6]);
				def child2GpTree = new GpTree(["mult","divi", 4, 100000, 6]);
				def parent1 = new Individual(parent1GpTree);
				parent1.setFitness(5);
				println(parent1)
				def parent2 = new Individual(parent2GpTree);
				parent2.setFitness(10);
				println(parent2)
				def child = new Individual(childGpTree);
				child.setFitness(3);
				def child2 = new Individual(child2GpTree);
				child2.setFitness(40);
				println(child)
			when:
				neo4j.setInitial(parent1)
				neo4j.setInitial(parent2)
				neo4j.setCrossover(parent1,parent2, child, 1, 1)
				neo4j.setReproduction(child, child.clone(), 2)
				neo4j.setMutation(child, child2, 2, 3)
				neo4j.setElitism(child2, child2.clone(), 3)
			
			then:
				assert true;
		}
		
		@Test
		public void simpleObserverWithXOAltPointZero() {
			given:
				Neo4jObserverNotifier neo4j = new Neo4jObserverNotifier();
				LocalDBObserver observer = new LocalDBObserver(neo4j);
				def parent1GpTree = new GpTree(["plus","sub", 1, 2, 3]);
				def parent2GpTree = new GpTree(["sub","plus", 3, 2, 1]);
				def childGpTree = new GpTree(["mult","divi", 4, 5, 6]);
				def child2GpTree = new GpTree(["mult","divi", 4, 100000, 6]);
				def parent1 = new Individual(parent1GpTree);
				parent1.setFitness(5);
				println(parent1)
				def parent2 = new Individual(parent2GpTree);
				parent2.setFitness(10);
				println(parent2)
				def child = new Individual(childGpTree);
				child.setFitness(3);
				def child2 = new Individual(child2GpTree);
				child2.setFitness(40);
				println(child)
			when:
				neo4j.setInitial(parent1)
				neo4j.setInitial(parent2)
				neo4j.setCrossover(parent1,parent2, child, 1, 0)
				neo4j.setReproduction(child, child.clone(), 2)
				neo4j.setMutation(child, child2, 2, 3)
				neo4j.setElitism(child2, child2.clone(), 3)
			
			then:
				assert true;
		}
}
