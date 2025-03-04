package com.ty.mySpringBank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ty.mySpringBank.model.Account;
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

	Account findByAccountNumber(String accountNumber);
}
