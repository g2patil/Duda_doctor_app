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
    
    
    @Column(name = "voucher_no")
    private Long voucher_no;
    
    
    @Column(name = "lf_no")
    private Long lf_no;
    
    

    public Long getLf_no() {
		return lf_no;
	}

	public void setLf_no(Long lf_no) {
		this.lf_no = lf_no;
	}

	public Long getVoucher_no() {
		return voucher_no;
	}

	public void setVoucher_no(Long voucher_no) {
		this.voucher_no = voucher_no;
	}

	public Long getAccount_sub_head_id() {
		return account_sub_head_id;
	}

	public void setAccount_sub_head_id(Long account_sub_head_id) {
		this.account_sub_head_id = account_sub_head_id;
	}

	@Column(name = "payment_mode")
    private String paymentMode;
    
    @Column(name = "cash_bank")
    private String cash_bank;
    

    @Column(name = "description")
    private String description;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    // === Account Type
    @ManyToOne
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    private AccountType accountType;

    // === Account Main Head (Change from Long to AccountMainHead)
  //  @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "account_main_head_id", referencedColumnName = "account_main_head_id")
    @Column(nullable = false)
    private Long/*AccountMainHead */accountMainHead;  // Referencing AccountMainHead entity

    // === Account Sub Head (Change from Long to AccountSubHead)
   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "account_sub_head_id", referencedColumnName = "account_sub_head_id")
    @Column(nullable = false)
    private Long account_sub_head_id;  // Referencing AccountSubHead entity

    // === User who updated
  //  @ManyToOne
  //  @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Long/*MyUser*/ user;

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

    public Long/*AccountMainHead*/ getAccountMainHead() {
        return accountMainHead;
    }

    public void setAccountMainHead(Long/*AccountMainHead*/ accountMainHead) {
        this.accountMainHead = accountMainHead;
    }

    public Long getAccountSubHead() {
        return account_sub_head_id;
    }

    public void setAccountSubHead(Long accountSubHead) {
        this.account_sub_head_id = accountSubHead;
    }

    public Long/*MyUser*/ getUser() {
        return user;
    }

    public void setUser(Long/*MyUser*/ user) {
        this.user = user;
    }

	public void setAccountTypeId(Long accountTypeId) {
		// TODO Auto-generated method stub
		
	}

	public void setUserId(Long userId) {
		// TODO Auto-generated method stub
		
	}

	public String getCash_bank() {
		return cash_bank;
	}

	public void setCash_bank(String cash_bank) {
		this.cash_bank = cash_bank;
	}
    
}

