package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"userId", "updatedOn", "createdBy", "createdOn"})
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @Column(nullable = false, name = "account_type")
    private String accountType;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    // Constructors
    public AccountType() {}

    public AccountType(Long accountTypeId,String accountType, Long userId) {
    	this.accountTypeId=accountTypeId;
        this.accountType = accountType;
        this.userId = userId;
        this.updatedOn = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getAccountTypeId() { return accountTypeId; }

    public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getUpdatedOn() { return updatedOn; }
    public void setUpdatedOn(LocalDateTime updatedOn) { this.updatedOn = updatedOn; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(accountTypeId, that.accountTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeId);
    }
}

