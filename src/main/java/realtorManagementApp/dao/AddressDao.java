package realtorManagementApp.dao;

import realtorManagementApp.entities.Address;

import java.util.List;

public interface AddressDao {
    public List<Address> findAll();

    public void save(Address address);

    public void delete(Address address);

    public void update(Address address);
}
