package umm.semanticgp;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class GraphDB {

	private static final String DB_PATH = "neo4j/graph.db";
	private static GraphDatabaseService graphDB;
	
	private GraphDB(){

	}
	
	private static void registerShutdownHook( final GraphDatabaseService graphDb )
	{
	    // Registers a shutdown hook for the Neo4j instance so that it
	    // shuts down nicely when the VM exits (even if you "Ctrl-C" the
	    // running example before it's completed)
	    Runtime.getRuntime().addShutdownHook( new Thread()
	    {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	        }
	    } );
	}
	
	public static GraphDatabaseService graphDB(){
		if(graphDB == null){
			graphDB = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
			registerShutdownHook( graphDB );
		}
		return graphDB;
	}
}

