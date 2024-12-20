package com.synopsis.capacitacion.product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="code",unique=true,length = 50)
	@NotBlank(message="Debe ingresar el Codigo del producto")
	private String code;
	
	@Column(name="name",length = 50)
	@NotBlank(message="Debe ingresar el Nombre del producto")
	private String name;
	
	public Product() {
		
	}		
	public Product(long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public Product(String code, String name) {
		super();		
		this.code = code;
		this.name = name;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	
}
