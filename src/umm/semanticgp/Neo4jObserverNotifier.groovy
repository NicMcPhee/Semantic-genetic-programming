package umm.semanticgp

import java.util.Observable;
import java.util.Observer;

class Neo4jObserverNotifier extends Observable {
	
	private Individual parent1;
	private Individual parent2;
	private Individual child;
	private int generation;
	private String transformationType;
	private int pointOfAltercation;
	
	public Neo4jObserverNotifier() {}
	
	public void childCreated() {
		setChanged();
		notifyObservers();
	}
	
	public void setCrossover(Individual parent1, Individual parent2, Individual child, int generation, int pointOfAltercation) {
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.child = child;
		this.generation = generation;
		this.transformationType = 'crossover';
		this.pointOfAltercation = pointOfAltercation;
		childCreated();
	}
	
	public void setMutation(Individual parent1, Individual child, int generation, int pointOfAltercation) {
		this.parent1 = parent1;
		this.child = child;
		this.generation = generation;
		this.transformationType = 'mutation';
		this.pointOfAltercation = pointOfAltercation;
		childCreated();
	}
	
	public void setReproduction(Individual parent, Individual child, int generation) {
		this.parent1 = parent;
		this.child = child;
		this.generation = generation;
		this.transformationType = 'reproduction';
		childCreated();
	}
	
	public void setInitial(Individual child) {
		this.child = child;
		this.generation = 0;
		this.transformationType = 'initial';
		childCreated();
	}
	
	public void setElitism(Individual parent, Individual child, int generation) {
		this.child = child;
		this.parent1 = parent;
		this.generation = generation;
		this.transformationType = 'elitism';
		childCreated();
	}
	
	public Individual getFirstParent() {
		return parent1;
	}
	
	public Individual getSecondParent() {
		return parent2;
	}
	
	public Individual getChild() {
		return child;
	}
	
	// made generations start at 1 because neo4j does not allow zeros to be the display
	public int getGeneration() {
		return generation + 1;
	}
	
	public String getTransformationType() {
		return transformationType;
	}
	
	public int getPointOfAltercation() {
		return pointOfAltercation;
	}
	
	public String getUid(parent) {
		return parent.uid
	}
}