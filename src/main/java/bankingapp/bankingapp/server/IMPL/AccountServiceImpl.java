package bankingapp.bankingapp.server.IMPL;

import bankingapp.bankingapp.dto.AccountDto;
import bankingapp.bankingapp.entity.Account;
import bankingapp.bankingapp.mapper.AccountMapper;
import bankingapp.bankingapp.repository.AccountRepository;
import bankingapp.bankingapp.server.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
@Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedaccount= accountRepository.save(account);
      return  AccountMapper.mapToAccountDto(savedaccount);


    }

    @Override
    public AccountDto getAccountById(Long id) {
    Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exits"));
    return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
    Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exits"));
    double total=account.getBalance()+amount;
    account.setBalance(total);
    Account account1=accountRepository.save(account);
    return AccountMapper.mapToAccountDto(account1);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
    Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Sry id is not exist"));
    if(account.getBalance()<amount){
        throw new RuntimeException("The balance amount is not enough");
    }
    double current=account.getBalance()-amount;
    account.setBalance(current);
    Account account1=accountRepository.save(account);
    return AccountMapper.mapToAccountDto(account1);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
List<Account> accounts=accountRepository.findAll();
return accounts.stream().map(account ->AccountMapper.mapToAccountDto(account) ).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("No id exits"));
        accountRepository.delete(account);

    }


}
