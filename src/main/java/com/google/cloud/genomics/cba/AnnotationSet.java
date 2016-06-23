package com.google.cloud.genomics.cba;

import java.util.ArrayList;

import com.google.gson.JsonObject;


/**
 * <h1>AnnotationSet APIs</h1> This class contains generic annotationSet APIs. It prepares and converts
 * Annotation fields to the corresponding fields in Google Genomics annotation APIs.
 *
 *
 * @param TOKEN
 *            A piece of data generated by the server which identifies a
 *            user.
 * @param referenceSetId
 *            The ID of the reference set that defines the coordinate space for this set's annotations.
 * @param datasetId
 *            The dataset to which this annotation set belongs.
 * @param referenceSetName
 *            The display name for this annotation set.
 * @param annotationSetInfo
 *            A map of additional read alignment information. 
 *            This must be of the form map<string, string[]> (string key mapping to a list of string values).
 * @param annotationSetSourceUri
 *            The source URI describing the file from which this annotation set was generated, if any.
 * @param annotationSetType
 *            The type of annotations contained within this set.
 *            
 *            
 * @version 1.0
 * @since 2016-07-17
 */


public class AnnotationSet {
	
	/*REST API - Authentication Token*/
	private String TOKEN;
	
	/*AnnotationSet Fields*/
	private String referenceSetId;
	private String datasetId; 
	private String annotaionSetName;
	private JsonObject annotationSetInfo;
	private String annotationSetSourceUri;
	private String annotationSetType;
	
	private String annotationSetId;

	
	public void submitAnnotationSet() {

		if (!getReferenceSetId().isEmpty() && !getDatasetId().isEmpty()) {

			String CURL = "curl -v -X POST -H \"Authorization: Bearer " + getTOKEN() + "\"";
			String JSON = "-d '{\"datasetId\": \"" + getDatasetId() + "\" ,  \"referenceSetId\": \"" + getReferenceSetId() + "\" , "
					+ " \"name\": \"" + getAnnotaionSetName() + "\" }'";
			String HTTP_REQ = "--header \"Content-Type: application/json\" https://genomics.googleapis.com/v1/annotationsets";

			ArrayList<String> output = CurlHttpRequests.execution(CURL + " " + JSON + " " + HTTP_REQ, ".");
			if (null == output) {
				System.out.println("\n\n\t\tcreateAnnotationSet FAILED!");
				System.exit(0);
			} else {// returns annotationsetId
				if (output.toString().contains("id"))
					this.setAnnotationSetId(Annotation.getIdFromJSON(output));
				else {
					System.out.println("\n\n\t\tcreateAnnotationSet FAILED!");
					System.exit(0);
				}
			}
		} else
			System.out.println("referenceId and datasetId are required fields!");

	}
	
	public String getReferenceSetId() {
		return referenceSetId;
	}

	public void setReferenceSetId(String referenceSetId) {
		this.referenceSetId = referenceSetId;
	}

	public String getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}

	public String getAnnotaionSetName() {
		return annotaionSetName;
	}

	public void setAnnotaionSetName(String annotaionSetName) {
		this.annotaionSetName = annotaionSetName;
	}
	
	public JsonObject getAnnotationSetInfo() {
		return annotationSetInfo;
	}

	public void setAnnotationSetInfo(JsonObject annotationSetInfo) {
		this.annotationSetInfo = annotationSetInfo;
	}

	public String getAnnotationSetSourceUri() {
		return annotationSetSourceUri;
	}

	public void setAnnotationSetSourceUri(String annotationSetSourceUri) {
		this.annotationSetSourceUri = annotationSetSourceUri;
	}

	public String getAnnotationSetType() {
		return annotationSetType;
	}

	public void setAnnotationSetType(String annotationSetType) {
		this.annotationSetType = annotationSetType;
	}
	

	public String getTOKEN() {
		return TOKEN;
	}

	public void setTOKEN(String token) {
		TOKEN = token;
	}

	public String getAnnotationSetId() {
		return annotationSetId;
	}

	private void setAnnotationSetId(String annotationSetId) {
		this.annotationSetId = annotationSetId;
	}
}
