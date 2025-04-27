package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AccountMainHead;
import repository.AccountMainHeadRepository;

@Service
public class AccountMainHeadService {

    @Autowired
    private AccountMainHeadRepository accountMainHeadRepository;

    public List<AccountMainHead> getByAccountTypeId(Long accountTypeId) {
        return accountMainHeadRepository.findByAccountType_AccountTypeId(accountTypeId);
    }
}
