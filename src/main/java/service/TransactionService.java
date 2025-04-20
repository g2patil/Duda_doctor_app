package service;

import dto.TransactionDTO;
import model.*;
import repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private SchoolTransactionRepository repository;

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountMainHeadRepository accountMainHeadRepository;

    @Autowired
    private AccountSubHeadRepository accountSubHeadRepository;

    public void saveTransaction(TransactionDTO dto) {
        SchoolTransaction transaction = new SchoolTransaction();

        transaction.setTransactionDate(dto.getTransactionDate());
        transaction.setAmount(dto.getAmount());
        transaction.setPaymentMode(dto.getPaymentMode());
        transaction.setDescription(dto.getDescription());
        transaction.setUpdatedOn(LocalDateTime.now());

        // ✅ Set full entities instead of IDs
        MyUser user = userRepository.findById(dto.getUserId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(dto.getAccountTypeId()).orElse(null);
        AccountMainHead accountMainHead = accountMainHeadRepository.findById(dto.getAccountMainHeadId()).orElse(null);
        AccountSubHead accountSubHead = accountSubHeadRepository.findById(dto.getAccountSubHeadId()).orElse(null);

        transaction.setUser(user);
        transaction.setAccountType(accountType);
        transaction.setAccountMainHead(accountMainHead);
        transaction.setAccountSubHead(accountSubHead);

        repository.save(transaction);

        System.out.println("Saving to DB: " + transaction);
    }
}
