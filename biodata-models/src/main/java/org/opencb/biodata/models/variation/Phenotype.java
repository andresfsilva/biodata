package org.opencb.biodata.models.variation;

import java.util.Map;

public class Phenotype {

	private String name;
	private String description;
	
	private String studyName;
	private String studyDescription;
	private String studyExternalReference;
	private String studyType;
	
	private String source;
	private String version;
	
	private Map<String, Object> attributes;
//	private String associated_variant_risk_allele = null;
//	private String risk_allele_freq_in_controls = null;
//	private String p_value = null;
//	private String associated_gene = null;

	public Phenotype(String name, String description, String studyName,
			String studyDescription, String studyExternalReference,
			String studyType, String source, String version,
			Map<String, Object> attributes) {
		this.name = name;
		this.description = description;
		this.studyName = studyName;
		this.studyDescription = studyDescription;
		this.studyExternalReference = studyExternalReference;
		this.studyType = studyType;
		this.source = source;
		this.version = version;
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	
	public String getStudyDescription() {
		return studyDescription;
	}

	public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}

	
	public String getStudyExternalReference() {
		return studyExternalReference;
	}

	public void setStudyExternalReference(String studyExternalReference) {
		this.studyExternalReference = studyExternalReference;
	}

	
	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
}
