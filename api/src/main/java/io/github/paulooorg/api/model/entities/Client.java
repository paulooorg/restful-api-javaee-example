package io.github.paulooorg.api.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Client extends PersistentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
	@SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 3)
	private Long id;
	
	@Column(length = 150)
	@NotNull
	@NotBlank
    private String name;

    @Column(length = 100)
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column(length = 18)
    @NotNull
    @NotBlank
    private String document;

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DocumentType documentType;

	@Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    	this.id = id;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
}
