package io.github.paulooorg.api.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "refresh_token")
public class RefreshToken extends PersistentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_token_sequence")
	@SequenceGenerator(name = "refresh_token_sequence", sequenceName = "refresh_token_sequence", allocationSize = 3)
	private Long id;
	
	private String token;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private LocalDateTime expiration;
	
	private boolean blocked = false;

	public static RefreshToken newRefreshToken(User user) {
		LocalDateTime expiration = LocalDateTime.now().plusMonths(1);
		return new RefreshToken(randomKey(), user, expiration);
	}
	
	private static String randomKey() {
		return UUID.randomUUID().toString();
	}

	public RefreshToken(String token, User user, LocalDateTime expiration) {
		this.token = token;
		this.user = user;
		this.expiration = expiration;
	}

	public RefreshToken() {
		
	}
	
	public boolean isValid() {
		return !isBlocked() && expiration.isAfter(LocalDateTime.now());
	}
	
	public void block() {
		blocked = true;
	}
	
	@Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    	this.id = id;
    }
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}
