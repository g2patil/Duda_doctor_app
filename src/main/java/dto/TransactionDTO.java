package dto;

import java.time.LocalDate;

public class TransactionDTO {

    private LocalDate transactionDate;
    private Double amount;
    private String paymentMode;
    private String description;
    private Long accountSubHeadId;
    private Long userId;
    private Long accountTypeId;
    private Long accountMainHeadId;

    // Default constructor
    public TransactionDTO() {
    }

    // Parameterized constructor
    public TransactionDTO(LocalDate transactionDate, Double amount, String paymentMode,
                          String description, Long accountSubHeadId, Long userId,Long accountTypeId,Long accountMainHeadId) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.description = description;
        this.accountSubHeadId = accountSubHeadId;
        this.userId = userId;
        this.accountTypeId=accountTypeId;
        this.accountMainHeadId=accountMainHeadId;
    }

    public Long getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	// Getters and Setters
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

    public Long getAccountSubHeadId() {
        return accountSubHeadId;
    }

    public void setAccountSubHeadId(Long accountSubHeadId) {
        this.accountSubHeadId = accountSubHeadId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountMainHeadId() {
		return accountMainHeadId;
	}

	public void setAccountMainHeadId(Long accountMainHeadId) {
		this.accountMainHeadId = accountMainHeadId;
	}

	// toString() method (optional)
    @Override
    public String toString() {
        return "TransactionDTO{" +
                "transactionDate='" + transactionDate + '\'' +
                ", amount=" + amount +
                ", paymentMode='" + paymentMode + '\'' +
                ", description='" + description + '\'' +
                ", accountSubHeadId=" + accountSubHeadId +
                ", userId=" + userId +
                '}';
    }
}
