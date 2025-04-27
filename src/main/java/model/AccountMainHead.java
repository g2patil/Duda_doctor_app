package model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "account_main_head",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"account_main_head", "account_type_id"})
    }
)
public class AccountMainHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_main_head_id")
    private Long accountMainHeadId;

    @Column(name = "account_main_head")
    private String accountMainHead;

    @JsonIgnore
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    private AccountType accountType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    // Getters and setters
    public Long getAccountMainHeadId() {
        return accountMainHeadId;
    }

    public void setAccountMainHeadId(Long accountMainHeadId) {
        this.accountMainHeadId = accountMainHeadId;
    }

    public String getAccountMainHead() {
        return accountMainHead;
    }

    public void setAccountMainHead(String accountMainHead) {
        this.accountMainHead = accountMainHead;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountMainHead)) return false;
        AccountMainHead that = (AccountMainHead) o;
        return Objects.equals(accountMainHeadId, that.accountMainHeadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountMainHeadId);
    }
}


/*package model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_main_head",
uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(columnNames = {"account_main_head_id", "account_type_id"})
    }
		)

public class AccountMainHead {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_main_head_id")
    private Long accountMainHeadId;

    @Column(name = "account_main_head")
    private String accountMainHead;

    @JsonIgnore
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    // Foreign key relation to the AccountType table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    private AccountType accountType;

    // Foreign key relation to the User table (assumed MyUser is your user entity)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    // Getters and setters
    public Long getAccountMainHeadId() {
        return accountMainHeadId;
    }

    public void setAccountMainHeadId(Long accountMainHeadId) {
        this.accountMainHeadId = accountMainHeadId;
    }

    public String getAccountMainHead() {
        return accountMainHead;
    }

    public void setAccountMainHead(String accountMainHead) {
        this.accountMainHead = accountMainHead;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountMainHead that = (AccountMainHead) o;
        return Objects.equals(accountMainHeadId, that.accountMainHeadId) &&
               Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountMainHeadId, accountType);
    }
   
    
}
*/