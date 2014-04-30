package umm.semanticgp;

import org.neo4j.graphdb.RelationshipType;

public enum RelTypes implements RelationshipType
{
    ROOT_XOOF, NONROOT_XOOF, PARENTOF, MUTANTOF, ELITISM
}