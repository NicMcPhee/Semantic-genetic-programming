
package umm.semanticgp

import java.util.Observable;
import java.util.Observer;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import java.util.UUID;

public class LocalDBObserver implements Observer {
    final GraphDatabaseService graphDB = GraphDB.graphDB();
    IndexManager index = graphDB.index();
	private static final long serialVersionUID = 8709627944120749083L;
	def runid = UUID.randomUUID();

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
        try {
            if (obs instanceof Neo4jObserverNotifier) {
                Neo4jObserverNotifier neo4jNotifier = (Neo4jObserverNotifier)obs;
                this.transformationType = neo4jNotifier.getTransformationType();
                this.child = neo4jNotifier.getChild();
                this.generation = neo4jNotifier.getGeneration();
                //Create Child node
				individualNodes = index.forNodes("Individual");
                createChildNode();

                //Create Relationships
				
                createRelationships(neo4jNotifier)
            }

            tx.success();
        }
        finally {
            tx.finish();
        }
    }

    private getNode(individual) {
        individualNodes.get("uid", individual.getUid().toString()).next()
    }

    private createRelationships(Neo4jObserverNotifier neo4jNotifier) {
        if (transformationType == 'crossover') {
            //get parent1
            this.parent1 = neo4jNotifier.getFirstParent();
            //grab parent2
            this.parent2 = neo4jNotifier.getSecondParent();
            //grab point of altercation
            this.pointOfAltercation= neo4jNotifier.getPointOfAltercation();
            //Edit child's point of altercation
            individualNode.setProperty("cutPoint", pointOfAltercation);
            // sets relationships to appropriate parents
            setRootParent()
        } else if (transformationType == 'mutation') {
            //get parent1
            this.parent1 = neo4jNotifier.getFirstParent();
            // edit parent 1 relation
            Node parentNode = getNode(parent1)
			toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.MUTANTOF);
            //grab point of altercation
            this.pointOfAltercation= neo4jNotifier.getPointOfAltercation();
            //Edit child's point of altercation
            individualNode.setProperty("cutPoint", this.pointOfAltercation);
        } else if (transformationType == 'reproduction') {
            //get parent1
            this.parent1 = neo4jNotifier.getFirstParent();
            // edit parent 1 relation
            Node parentNode = getNode(parent1)
            toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.PARENTOF);
        } else if (transformationType == 'elitism') {
            //get parent1
            this.parent1 = neo4jNotifier.getFirstParent();
            // edit parent 1 relation
            Node parentNode = getNode(parent1)
            toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.ELITISM);
        }
    }

    private setRootParent() {
		Node parentNode = getNode(parent1)
		Node parent2Node = getNode(parent2)
        if (pointOfAltercation == 0) {       
			toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.NONROOT_XOOF);
			toParent2 = parent2Node.createRelationshipTo(individualNode, RelTypes.ROOT_XOOF);
        } else {
			toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.ROOT_XOOF);
			toParent2 = parent2Node.createRelationshipTo(individualNode, RelTypes.NONROOT_XOOF);
        }
    }

    private createChildNode() {
        individualNode = graphDB.createNode();
		individualNode.setProperty("runid", runid.toString());
        individualNode.setProperty("fitness", (double) child.getFitness());
        individualNode.setProperty("transformation_type", transformationType);
        individualNode.setProperty("tree", child.getTree().toString());
        individualNode.setProperty("uid", child.getUid().toString());
        individualNode.setProperty("generation", generation);
        individualNodes.add(individualNode, "uid", child.getUid().toString())
		Label indivLabel = DynamicLabel.label('Individual');
		individualNode.addLabel(indivLabel)
		
		
    }
}