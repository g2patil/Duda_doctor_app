package model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_main_head")
public class AccountMainHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_main_head_id")
    private Long accountMainHeadId;

    @Column(name = "account_main_head")
    private String accountMainHead;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    // Foreign key relation to the AccountType table
    @ManyToOne
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    private AccountType accountType;

    // Foreign key relation to the User table (assumed MyUser is your user entity)
    @ManyToOne
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
        return Objects.equals(accountMainHeadId, that.accountMainHeadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountMainHeadId);
    }
    
}
