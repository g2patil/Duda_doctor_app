package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "school_transaction")
public class SchoolTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "description")
    private String description;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    // === Account Type
    @ManyToOne
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    private AccountType accountType;

    // === Account Main Head (Change from Long to AccountMainHead)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_main_head_id", referencedColumnName = "account_main_head_id")
    private AccountMainHead accountMainHead;  // Referencing AccountMainHead entity

    // === Account Sub Head (Change from Long to AccountSubHead)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_sub_head_id", referencedColumnName = "account_sub_head_id")
    private AccountSubHead accountSubHead;  // Referencing AccountSubHead entity

    // === User who updated
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    // Getters and setters

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public AccountMainHead getAccountMainHead() {
        return accountMainHead;
    }

    public void setAccountMainHead(AccountMainHead accountMainHead) {
        this.accountMainHead = accountMainHead;
    }

    public AccountSubHead getAccountSubHead() {
        return accountSubHead;
    }

    public void setAccountSubHead(AccountSubHead accountSubHead) {
        this.accountSubHead = accountSubHead;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

	public void setAccountTypeId(Long accountTypeId) {
		// TODO Auto-generated method stub
		
	}

	public void setUserId(Long userId) {
		// TODO Auto-generated method stub
		
	}
    
}

