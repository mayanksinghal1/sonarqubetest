package net.atos.Customer.service;

import java.util.List;

import net.atos.Customer.pojo.Customer;



/**
 * @author a634994
 *
 */
public interface CustomerService {

	/**
	 * @param id
	 * @return
	 */
	public Customer findById(long id);
	
	
	/**
	 * @return
	 */
	public List<Customer> findAll();
	 
	/**
	 * @param customer
	 * @return
	 */
	public Customer addCustomer(Customer customer);
	
	/**
	 * @param customer
	 * @return
	 */
	public boolean deleteCustomer(long customer);
	
	/**
	 * @param customer
	 * @return
	 */
	public boolean updateCustomer(Customer customer);
	
}
