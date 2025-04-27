package service;

import model.AccountType;
import repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }
}
