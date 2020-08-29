package io.github.paulooorg.api.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_account_key")
public class UserAccountKey extends PersistentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_key_sequence")
	@SequenceGenerator(name = "user_account_key_sequence", sequenceName = "user_account_key_sequence", allocationSize = 3)
	private Long id;
	
	private String key;
	
	@Column(name = "key_type")
	@Enumerated(EnumType.STRING)
	private KeyType keyType;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "expiration")
	private LocalDateTime expiration;
	
	public static UserAccountKey newRecoveryKey(User user) {
		LocalDateTime expirationDate = LocalDateTime.now().plusHours(24);
		return new UserAccountKey(randomKey(), KeyType.RECOVERY_KEY, user, expirationDate);
	}
	
	public static UserAccountKey newActivationKey(User user) {
		LocalDateTime expirationDate = LocalDateTime.now().plusHours(24);
		return new UserAccountKey(randomKey(), KeyType.ACTIVATION_KEY, user, expirationDate);
	}
	
	private static String randomKey() {
		return UUID.randomUUID().toString();
	}
	
	public UserAccountKey(String key, KeyType keyType, User user, LocalDateTime expirationDate) {
		this.key = key;
		this.keyType = keyType;
		this.user = user;
		this.expiration = expirationDate;
	}

	public UserAccountKey() {
		
	}

	public enum KeyType {
		RECOVERY_KEY, ACTIVATION_KEY;
	}

	@Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    	this.id = id;
    }
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public KeyType getKeyType() {
		return keyType;
	}

	public void setKeyType(KeyType keyType) {
		this.keyType = keyType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpirationDate() {
		return expiration;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expiration = expirationDate;
	}
}
