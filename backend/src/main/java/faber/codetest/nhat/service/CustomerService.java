package faber.codetest.nhat.service;

import faber.codetest.nhat.entity.Customer;
import faber.codetest.nhat.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CustomerService extends AbstractCrudService<Customer, Long> {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * save customer in list
     * @param customerList
     */
    public void saveCustomerList(List<Customer> customerList) {
        if (CollectionUtils.isEmpty(customerList)) {
            for (Customer customer : customerList) {
                createOrUpdate(customer);
            }
        }
    }

    /**
     * create new customer if not exist, otherwise create new one
     * @param customer
     */
    private void createOrUpdate(Customer customer) {
        String personId = customer.getPersonId();
        // find exist customer by Personal Id
        Customer existCustomer = customerRepository.findFirstByPersonId(personId);

        // create new or update
        if (existCustomer != null) {
            customer.setId(existCustomer.getId());
        }
        save(customer);
    }

    @Override
    public JpaRepository<Customer, Long> getRepository() {
        return customerRepository;
    }
}
