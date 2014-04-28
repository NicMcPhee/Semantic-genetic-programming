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
	def SERVER_ROOT_URI = 'http://localhost:7474'

//	Relationship toParent1;
//	Relationship toParent2;
//	Node individualNode;
//	Index<Node> individualNodes;

	Observable observable;
	private Individual parent1;
	private Individual parent2;
	private Individual child;
	private int generation;
	private String transformationType;
	private int pointOfAltercation;

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
				//Create Child node
				//individualNodes = index.forNodes("Individual");
				getServerStatus();
				if (transformationType == 'reproduction') {
					createReproduction();
				} else if (transformationType == 'mutation'){
					createMutation();
				} else if (transformationType == 'crossover'){
					createXO()
				} else if (transformationType == 'elitism') {
					createElite()
				} else if (transformationType == 'initial') {
					createReproduction()
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
			println(status)
			mGet.releaseConnection( );
		}catch(Exception e){
			System.out.println("Exception in connecting to neo4j : " + e);
		}

		return status;
	}

	public String createReproduction(){
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
			println(status)
			output = mPost.getResponseBodyAsString( );
			println(output)
			Header locationHeader =  mPost.getResponseHeader("location");
			location = locationHeader.getValue();
			mPost.releaseConnection( );
			System.out.println("status : " + status);
			System.out.println("location : " + location);
			System.out.println("output : " + output);
		}catch(Exception e){
			System.out.println("Exception in creating node in neo4j : " + e);
		}

		return location;
	}
	
	public String createMutation(){
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
				'{"runid": "' + runid + '", "fitness": "' + child.getFitness() + '", "transformation_type": "' + transformationType +'", "tree": "' + child.getTree().toString() +'", "uid": "'  + child.getUid().toString() + '","generation": "' + generation + '", "uid": "' + child.getUid().toString() + '", "cutPoint": "' + neo4jNotifier.getPointOfAltercation() + '"}',
				"application/json", "UTF-8");
			mPost.setRequestEntity(requestEntity);
			int status = client.executeMethod(mPost);
			println(status)
			output = mPost.getResponseBodyAsString( );
			println(output)
			Header locationHeader =  mPost.getResponseHeader("location");
			location = locationHeader.getValue();
			mPost.releaseConnection( );
			System.out.println("status : " + status);
			System.out.println("location : " + location);
			System.out.println("output : " + output);
		}catch(Exception e){
			System.out.println("Exception in creating node in neo4j : " + e);
		}

		return location;
	}
	
	
}
