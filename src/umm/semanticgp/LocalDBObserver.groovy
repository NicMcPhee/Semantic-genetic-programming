package umm.semanticgp

import java.util.Observable;
import java.util.Observer;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;


public class LocalDBObserver implements Observer {
	final GraphDatabaseService graphDB = GraphDB.graphDB();
	IndexManager index = graphDB.index();
	
	Relationship toParent1;
	Relationship toParent2;
	Node individualNode;
	Index<Node> individualNodes;
	
	Observable observable;
	private Individual parent1;
	private Individual parent2;
	private Individual child;
	private int generation;
	private String transformationType;
	private int pointOfAltercation;

	public LocalDBObserver(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	public void update(Observable obs, Object arg) {


		Transaction tx = graphDB.beginTx();

		individualNodes = index.forNodes("Individuals");
		
		try {
			if (obs instanceof Neo4jObserverNotifier) {
				Neo4jObserverNotifier neo4jNotifier = (Neo4jObserverNotifier)obs;
				this.transformationType = neo4jNotifier.getTransformationType();
				this.child = neo4jNotifier.getChild();
				this.generation = neo4jNotifier.getGeneration();
//				println ("NEW INSTANCE")
				//Create Child node
				individualNode = graphDB.createNode();
				individualNode.setProperty("fitness", child.getFitness());
				individualNode.setProperty("transformation_type", transformationType);
				individualNode.setProperty("tree", child.getTree().toString());
				individualNode.setProperty("id", child.getUid().toString());
				individualNode.setProperty("generation", generation);
				individualNodes.add(individualNode, "id", child.getUid().toString());



				
				/*** made into method below had trouble with it*//////
				if (transformationType == 'crossover') {
						//get parent1
					this.parent1 = neo4jNotifier.getFirstParent();
						// edit parent 1 relation
					Node parentNode = individualNodes.get("id", parent1.getUid().toString()).next();
					toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.PARENTOF);
					toParent1.setProperty("root-parent", "true");
					
						//grab parent2
					this.parent2 = neo4jNotifier.getSecondParent();
					
						//grab point of altercation
					this.pointOfAltercation= neo4jNotifier.getPointOfAltercation();
					
						//Edit child's point of altercation
					individualNode.setProperty("Point_of_altercation", this.pointOfAltercation);
					
						 //build parent2 relationship
					Node parent2Node = individualNodes.get("id", parent2.getUid().toString()).next();
					toParent2 = parent2Node.createRelationshipTo(individualNode, RelTypes.PARENTOF);
					toParent2.setProperty("root-parent", "true");
//					System.out.println(transformationType);
//					System.out.println("parent1");
//					System.out.println(parent1);
//					System.out.println("parent2");
//					System.out.println(parent2);
//					System.out.println("child");
//					System.out.println(child);
//					System.out.println("gen");
//					System.out.println(generation);
//					System.out.println("alter");
//					System.out.println(pointOfAltercation);
					
				} else if (transformationType == 'mutation'){
						//get parent1
					this.parent1 = neo4jNotifier.getFirstParent();
						// edit parent 1 relation
					Node parentNode = individualNodes.get("id", parent1.getUid().toString()).next();
					toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.PARENTOF);
					
						//grab point of altercation
					this.pointOfAltercation= neo4jNotifier.getPointOfAltercation();
					
						//Edit child's point of altercation
					individualNode.setProperty("Point_of_altercation", this.pointOfAltercation);
//					System.out.println(transformationType);
//					System.out.println("parent1");
//					System.out.println(parent1);
//					System.out.println("child");
//					System.out.println(child);
//					System.out.println("gen");
//					System.out.println(generation);				
//					System.out.println("alter");
//					System.out.println(pointOfAltercation);
				} else if (transformationType == 'reproduction') {
						//get parent1
					this.parent1 = neo4jNotifier.getFirstParent();
						// edit parent 1 relation
					Node parentNode = individualNodes.get("id", parent1.getUid().toString()).next();
					toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.PARENTOF);
//					System.out.println("reproduction");
//					System.out.println("parent1");
//					System.out.println(parent1);
//					System.out.println("child");
//					System.out.println(child);
				}
				

				/*********************************************************
				 * transformationUpdate(neo4jNotifier, transformationType);
				 **********************************************************/
			}

			tx.success();
		}
		finally {
			tx.finish();
		}
	}

//	public void transformationUpdate(Neo4jObserverNotifier neo4jNotifier, String transformationType) {
//		if (transformationType == 'crossover') {
//			//this.parent2 = neo4jNotifier.getSecondParent();
//			this.pointOfAltercation= neo4jNotifier.getPointOfAltercation();
//			//Edit child's point of altercation
//			individualNode.setProperty("Point_of_altercation", this.pointOfAltercation);
//			// build parent2 relationship
//			//Node parent2Node = individualNodes.get("id", parent2.getUid().toString()).next();
//			//toParent2 = individualNode.createRelationshipTo(parent2Node, RelTypes.PARENTOF);
//
//		} else if (transformationType == 'mutation'){
//			this.pointOfAltercation= neo4jNotifier.getPointOfAltercation();
//			//Edit child's point of altercation
//			individualNode.setProperty("Point_of_altercation", this.pointOfAltercation);
//		}
//	}
}












