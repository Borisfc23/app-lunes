package com.synopsis.capacitacion.customer.controller;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.JsonNode;
import com.synopsis.capacitacion.customer.entities.Customer;
import com.synopsis.capacitacion.customer.entities.CustomerProduct;
import com.synopsis.capacitacion.customer.entities.TransactionDTO;
import com.synopsis.capacitacion.customer.repository.CustomerRepository;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	
	@Autowired
	 private Environment env;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
    private WebClient.Builder webClientBuilder;

	public CustomerRestController(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	HttpClient client = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.option(ChannelOption.SO_KEEPALIVE, true).option(EpollChannelOption.TCP_KEEPIDLE, 300)
			.option(EpollChannelOption.TCP_KEEPINTVL, 60).responseTimeout(Duration.ofSeconds(1))
			.doOnConnected(connection -> {
				connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
			});

	@GetMapping()
	public List<Customer> list() {
		return customerRepository.findAll();
	}

	@GetMapping("/{id}")
	public Customer get(@PathVariable long id) {
		return customerRepository.findById(id).get();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable long id, @RequestBody Customer input) {
		Customer find = customerRepository.findById(id).get();
		if (find != null) {
			find.setCode(input.getCode());
			find.setName(input.getName());
			find.setIban(input.getIban());
			find.setPhone(input.getPhone());
			find.setSurname(input.getSurname());
		}
		Customer save = customerRepository.save(find);
		return ResponseEntity.ok(save);
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody Customer input) {

		input.getProducts().forEach(x -> x.setCustomer(input));
		Customer save = customerRepository.save(input);
		return ResponseEntity.ok(save);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Optional<Customer> findById = customerRepository.findById(id);
		if (findById.get() != null) {
			customerRepository.delete(findById.get());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/full/{id}")
	public Customer getFull(@PathVariable long id) {

		Customer customer = customerRepository.findById(id).get();

		List<CustomerProduct> products = customer.getProducts();

		products.forEach(product -> {
			String productName = getProductName(product.getProductId());
			product.setProductName(productName);
			 
		}); 
	    //List<TransactionDTO> transactions = getTransactions(customer.getIban());
	   
	   //customer.setTransactions(transactions);
	    //SIN USAR DTO
	    List<Map<String, Object>> transactions = getTransactions2(customer.getIban());
		   
	    customer.setTransactions(transactions);
	    return customer;
	}

	private String getProductName(long id) {

		WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
				.baseUrl(env.getProperty("endpoint.ms-product.base-path"))
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url",env.getProperty("endpoint.ms-product.base-path"))).build();
		JsonNode block = build.method(HttpMethod.GET).uri(env.getProperty("endpoint.ms-product.path")+ id).retrieve().bodyToMono(JsonNode.class).block();

		String name = block.get("name").asText();
		return name;
	}
	
	private List<TransactionDTO> getTransactions(String iban) {

	    WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
	            .baseUrl("http://localhost:8082/transactions/buscar")
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .build();

	    List<TransactionDTO> transactions = build.method(HttpMethod.GET)
	            .uri(uriBuilder -> uriBuilder.queryParam("accountIban", iban).build())  
	            .retrieve()
	            .bodyToFlux(TransactionDTO.class) 
	            .collectList() 
	            .block(); 

	    return transactions;
	}
	//fORMA SIN USAR DTO
	private List<Map<String, Object>> getTransactions2(String iban) {

	    WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
	            .baseUrl(env.getProperty("endpoint.ms-transaction.base-path").concat(env.getProperty("endpoint.ms-transaction.path")))
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .build();

	    List<Map<String, Object>> transactions = build.method(HttpMethod.GET)
	            .uri(uriBuilder -> uriBuilder.queryParam("accountIban", iban).build())  
	            .retrieve()
	            .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {})
	            .collectList() 
	            .block(); 

	    return transactions;  
	}
}
