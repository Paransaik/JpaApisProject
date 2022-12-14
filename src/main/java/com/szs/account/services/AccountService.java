package com.szs.account.services;

import com.szs.account.models.Accounts;
import com.szs.account.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Accounts getAccount(Long userId, Long accountId) {
        return accountRepository.findByUserIdAndId(userId, accountId);
    }

    public Accounts save(Long userId, String name) {
        return accountRepository
                .save(Accounts
                        .builder()
                        .userId(userId)
                        .name(name)
                        .createdAt(LocalDateTime.now())
                        .build());
    }

    public List<Accounts> findAllByUserIdOrderByIdDesc(Long userId) {
        return accountRepository.findAllByUserIdOrderByIdDesc(userId);
    }

    public double getInterestDue(Long balance) {
        double rate = (double) balance;
        if (balance < 1000000) rate *= 0.03;
        else rate *= 0.04;
        return Math.round(rate * 10) / 10.0;
    }

}