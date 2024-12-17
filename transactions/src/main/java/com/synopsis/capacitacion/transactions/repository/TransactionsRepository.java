package com.synopsis.capacitacion.transactions.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synopsis.capacitacion.transactions.entity.Transactions;


@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
	List<Transactions> findByAccountIban(String accountIban);

	@Query(value = "SELECT * FROM get_all_transactions()", nativeQuery = true)
	List<Transactions> findAllTransactions();

	@Query(value = "SELECT * FROM get_transaction_by_id(:id)", nativeQuery = true)
	Transactions findTransactionById(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM  insert_transaction(:account_iban, :amount, :channel, :date, :description, :fee, :reference, :status)", nativeQuery = true)
	void insertTransaction(@Param("account_iban") String account_iban, @Param("amount") Double amount,
			@Param("channel") String channel, @Param("date") LocalDateTime date, @Param("description") String description,
			@Param("fee") Double fee, @Param("reference") String reference, @Param("status") String status);
}
