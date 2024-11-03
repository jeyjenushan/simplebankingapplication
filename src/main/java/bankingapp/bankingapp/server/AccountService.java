package bankingapp.bankingapp.server;

import bankingapp.bankingapp.dto.AccountDto;
import bankingapp.bankingapp.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
AccountDto deposit(Long id,double amount);
AccountDto withdraw(Long id,double amount);
List<AccountDto> getAllAccounts();
void deleteAccount(Long id);
}
