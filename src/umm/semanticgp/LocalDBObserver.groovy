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
                //Create Child node
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
            // edit parent 1 relation
            Node parentNode = getNode(parent1)
            toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.PARENTOF);

            //grab parent2
            this.parent2 = neo4jNotifier.getSecondParent();
            //grab point of altercation
            this.pointOfAltercation= neo4jNotifier.getPointOfAltercation();
            //Edit child's point of altercation
            individualNode.setProperty("cutPoint", pointOfAltercation);
            //build parent2 relationship
            Node parent2Node = getNode(parent2)
            toParent2 = parent2Node.createRelationshipTo(individualNode, RelTypes.PARENTOF);
            // sets rootParent property to appropriate parents
            setRootParent()
        } else if (transformationType == 'mutation') {
            //get parent1
            this.parent1 = neo4jNotifier.getFirstParent();
            // edit parent 1 relation
            Node parentNode = getNode(parent1)
            toParent1 = parentNode.createRelationshipTo(individualNode, RelTypes.PARENTOF);
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
        if (pointOfAltercation == 0) {
            toParent1.setProperty("rootParent", "false");
            toParent2.setProperty("rootParent", "true");
        } else {
            toParent1.setProperty("rootParent", "true");
            toParent2.setProperty("rootParent", "false");
        }
    }

    private createChildNode() {
        individualNode = graphDB.createNode();
        individualNode.setProperty("fitness", (double) child.getFitness());
        individualNode.setProperty("transformation_type", transformationType);
        individualNode.setProperty("tree", child.getTree().toString());
        individualNode.setProperty("uid", child.getUid().toString());
        individualNode.setProperty("generation", generation);
        individualNodes.add(individualNode, "uid", child.getUid().toString())
    }
}
