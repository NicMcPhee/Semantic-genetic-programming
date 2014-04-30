package umm.semanticgp

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;


import java.util.UUID;
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.Header
import org.apache.commons.httpclient.methods.StringRequestEntity

class RestDBObserver implements Observer {
	private static final long serialVersionUID = 8709627944120749083L;
	def runid = UUID.randomUUID();
	def SERVER_ROOT_URI = 'http://localhost:7474'//'http://sentinel.morris.umn.edu:7474'

	//	Relationship toParent1;
	//	Relationship toParent2;
	//	Node individualNode;
	//	Index<Node> individualNodes;

	Observable observable;
	private Individual parent1;
	private String parent1Uid;
	private Individual parent2;
	private String parent2Uid;
	private Individual child;
	private int generation;
	private String transformationType;
	private int cutPoint

	public RestDBObserver(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	public void update(Observable obs, Object arg) {
		if (obs instanceof Neo4jObserverNotifier) {
			Neo4jObserverNotifier neo4jNotifier = (Neo4jObserverNotifier)obs;
			this.transformationType = neo4jNotifier.getTransformationType();
			this.child = neo4jNotifier.getChild();
			this.generation = neo4jNotifier.getGeneration();
			if (transformationType == 'crossover'){
				String childLocation = createChildWithCut();
				this.parent1 = neo4jNotifier.getFirstParent()
				this.parent2 = neo4jNotifier.getSecondParent()
				this.parent1Uid = neo4jNotifier.getUid(parent1)
				this.parent2Uid = neo4jNotifier.getUid(parent2)
				this.cutPoint = neo4jNotifier.getPointOfAltercation()
				String p1Location = getParent(parent1Uid)
				String p2Location = getParent(parent2Uid)
				addRelationship(p1Location, childLocation, RelTypes.ROOT_XOOF)
				addRelationship(p2Location, childLocation, RelTypes.NONROOT_XOOF)
			} else if (transformationType == 'reproduction') {
				String childLocation = createChildWOCut();
				this.parent1 = neo4jNotifier.getFirstParent()
				this.parent1Uid = neo4jNotifier.getUid(parent1)
				String p1Location = getParent(parent1Uid)
				addRelationship(p1Location, childLocation, RelTypes.PARENTOF)
			} else if (transformationType == 'mutation'){
				String childLocation = createChildWithCut();
				this.parent1 = neo4jNotifier.getFirstParent()
				this.parent1Uid = neo4jNotifier.getUid(parent1)
				String p1Location = getParent(parent1Uid)
				addRelationship(p1Location, childLocation, RelTypes.MUTANTOF)
			} else if (transformationType == 'elitism') {
				String childLocation = createChildWOCut();
				this.parent1 = neo4jNotifier.getFirstParent()
				this.parent1Uid = neo4jNotifier.getUid(parent1)
				this.cutPoint = neo4jNotifier.getPointOfAltercation()
				String p1Location = getParent(parent1Uid)
				addRelationship(p1Location, childLocation, RelTypes.ELITISM)
			} else if (transformationType == 'initial') {
				createChildWOCut();
			}
		}
	}

	public int getServerStatus(){
		int status = 500;
		try{
			String url = SERVER_ROOT_URI;
			HttpClient client = new HttpClient();
			GetMethod mGet =   new GetMethod(url);
			status = client.executeMethod(mGet);
			mGet.releaseConnection( );
		}catch(Exception e){
			System.out.println("Exception in connecting to neo4j : " + e);
		}

		return status;
	}

	public String createChildWOCut(){
		String output = null;
		String location = null;
		try{
			String nodePointUrl = this.SERVER_ROOT_URI + "/db/data/node";
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(nodePointUrl);

			/**
			 * set headers
			 */
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/json");
			mtHeader.setName("accept");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);

			/**
			 * set json payload
			 */
			StringRequestEntity requestEntity = new StringRequestEntity(
			'{"runid": "' + runid + '", "fitness": "' + child.getFitness() + '", "transformation_type": "' + transformationType +'", "tree": "' + child.getTree().toString() +'", "uid": "'  + child.getUid().toString() + '","generation": "' + generation + '", "uid": "' + child.getUid().toString() + '"}',
			"application/json", "UTF-8");
			mPost.setRequestEntity(requestEntity);
			int status = client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString( );
			Header locationHeader =  mPost.getResponseHeader("location");
			location = locationHeader.getValue();
			mPost.releaseConnection( );
//			System.out.println("status : " + status);
//			System.out.println("location : " + location);
//			System.out.println("output : " + output);
		}catch(Exception e){
			System.out.println("Exception in creating wo cut node in neo4j : " + e);
		}

		return location;
	}

	public String createChildWithCut(){
		String output = null;
		String location = null;
		try{
			String nodePointUrl = this.SERVER_ROOT_URI + "/db/data/node";
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(nodePointUrl);

			/**
			 * set headers
			 */
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/json");
			mtHeader.setName("accept");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);

			/**
			 * set json payload
			 */
			StringRequestEntity requestEntity = new StringRequestEntity(
			'{"runid": "' + runid + '", "fitness": "' + child.getFitness() + '", "transformation_type": "' + transformationType +'", "tree": "' + child.getTree().toString() +'", "uid": "'  + child.getUid().toString() + '","generation": "' + generation + '", "uid": "' + child.getUid().toString() + '", "cutPoint": "' + cutPoint + '"}',
			"application/json", "UTF-8");
			mPost.setRequestEntity(requestEntity);
			int status = client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString( );
			Header locationHeader =  mPost.getResponseHeader("location");
			location = locationHeader.getValue();
			mPost.releaseConnection( );
//			System.out.println("status : " + status);
//			System.out.println("location : " + location);
//			System.out.println("output : " + output);
		}catch(Exception e){
			System.out.println("Exception in creating with cut node in neo4j : " + e);
		}

		return location;
	}

	public String addRelationship(String startNodeURI, String endNodeURI, relationshipType){
		String output = null;
		String location = null;
		try{
			String fromUrl = startNodeURI + "/relationships";
			//System.out.println("from url : " + fromUrl);

			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(fromUrl);

			/**
			 * set headers
			 */
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/json");
			mtHeader.setName("accept");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);

			/**
			 * set json payload
			 */
			StringRequestEntity requestEntity = new StringRequestEntity('{"to": "' + endNodeURI + '","type": "' + relationshipType + '" }',"application/json", "UTF-8");
			mPost.setRequestEntity(requestEntity);
			int status = client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString( );
			Header locationHeader =  mPost.getResponseHeader("location");
			location = locationHeader.getValue();
			mPost.releaseConnection( );
//			System.out.println("status : " + status);
//			System.out.println("location : " + location);
//			System.out.println("output : " + output);
		}catch(Exception e){
			System.out.println("Exception in creating node in neo4j : " + e);
		}

		return location;

	}

	
	public String getParent(String parentid){
		
		String output = null;
		String location = null;
		try{
			String nodePointUrl = this.SERVER_ROOT_URI + "/db/data/cypher";
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(nodePointUrl);
 
			/**
			 * set headers
			 */
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/json");
			mtHeader.setName("accept");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);
			/**
			 * set json payload
			 */
			String query = '{"query": "START p = node(*) WHERE p.uid = \'' + parentid + '\' RETURN p", "params": {}}';
			StringRequestEntity requestEntity = new StringRequestEntity(query,
																		"application/json",
																		"UTF-8");
			mPost.setRequestEntity(requestEntity);
			int status = client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString();
			location = parseForParentLocation(output);	
			mPost.releaseConnection( );
//			System.out.println("status : " + status);
//			System.out.println("location : " + location);
//			System.out.println("output : " + output);
		}catch(Exception e){
		System.out.println("Exception in finding parent in neo4j : " + e);
		}
 
		return location;
	}
	
	private String parseForParentLocation(String response) {
		def responseArray = response.split(",");
		int i = 0;
		i = responseArray.length - 1;
			while (!responseArray[i].contains('self')) {
				i--;
			}
			return responseArray[i].split('"')[3];
		
	}
}
