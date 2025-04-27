package model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "account_sub_head")
public class AccountSubHead {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_sub_head_id")
    private Long accountSubHeadId;

    
    @Column(name = "account_sub_head")
    private String accountSubHead;

    @Column(name = "updated_on")
    @JsonIgnore
    private LocalDateTime updatedOn;

    // Foreign key to AccountMainHead
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "account_main_head_id", referencedColumnName = "account_main_head_id")
    private AccountMainHead accountMainHead;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    private AccountType accountType;

    // Foreign key to MyUser (who updated the record)
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    // Getters and setters

    public Long getAccountSubHeadId() {
        return accountSubHeadId;
    }

    public void setAccountSubHeadId(Long accountSubHeadId) {
        this.accountSubHeadId = accountSubHeadId;
    }

    public String getAccountSubHead() {
        return accountSubHead;
    }

    public void setAccountSubHead(String accountSubHead) {
        this.accountSubHead = accountSubHead;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public AccountMainHead getAccountMainHead() {
        return accountMainHead;
    }

    public void setAccountMainHead(AccountMainHead accountMainHead) {
        this.accountMainHead = accountMainHead;
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
        AccountSubHead that = (AccountSubHead) o;
        return Objects.equals(accountSubHeadId, that.accountSubHeadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSubHeadId);
    }
    
}
