package com.synopsis.capacitacion.transactions.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synopsis.capacitacion.transactions.entity.Transactions;

import jakarta.transaction.Transactional;


@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
	List<Transactions> findByAccountIban(String accountIban);

	@Query(value = "SELECT id, reference, accountiban AS accountIban, date, amount, fee, description, status, channel FROM get_all_transactions()", nativeQuery = true)
	List<Transactions> findAllTransactions();

	@Query(value = "SELECT id, reference, accountiban AS accountIban, date, amount, fee, description, status, channel  FROM get_transaction_by_id(:id)", nativeQuery = true)
	Transactions findTransactionById(@Param("id") Long id);
	
} 
