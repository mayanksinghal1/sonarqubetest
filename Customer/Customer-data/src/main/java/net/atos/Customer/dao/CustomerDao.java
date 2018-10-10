package net.atos.Customer.dao;

import java.util.List;

import net.atos.Customer.pojo.Customer;



/**
 * @author a634994
 *
 */
public interface CustomerDao {

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
	 * @param customerId
	 * @return
	 */
	public boolean deleteCustomer(long customerId);

	/**
	 * @param customer
	 * @return
	 */
	public boolean updateCustomer(Customer customer);
}
