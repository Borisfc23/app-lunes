package com.synopsis.capacitacion.transactions.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synopsis.capacitacion.transactions.entity.Transactions;
import com.synopsis.capacitacion.transactions.repository.TransactionsRepository;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@Autowired
	TransactionsRepository repositorio;
	
	@GetMapping()
	public List<Transactions> list() {
		return repositorio.findAllTransactions();
	}
	
	@GetMapping("/buscar")
	public List<Transactions> getTransactionsByAccountIban(String accountIban) {
	    return repositorio.findByAccountIban(accountIban);
}
		
	@GetMapping("/{id}")
	public Transactions get(@PathVariable long id) {
		return repositorio.findTransactionById(id);
	}
	
	@PostMapping("/create")
	public Transactions creat(@RequestBody Transactions cuerpo) {

		return repositorio.save(cuerpo);
	}
	
	
}
