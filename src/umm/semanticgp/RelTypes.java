package umm.semanticgp;

import org.neo4j.graphdb.RelationshipType;

public enum RelTypes implements RelationshipType
{
    LOCATEDIN, PARENTOF, CONNECTEDTO, ELITISM
}