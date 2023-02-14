package realtorManagementApp.dao.impl;

import org.hibernate.Session;
import realtorManagementApp.dao.AddressDao;
import realtorManagementApp.entities.Address;
import realtorManagementApp.session.SessionHandler;

import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private Session currentSession;

    public AddressDaoImpl() {
    }

    @Override
    public List<Address> findAll() {
        currentSession = SessionHandler.openTransaction();
        List<Address> addresses = currentSession.createSQLQuery("select * from addresses").addEntity(Address.class).list();
        SessionHandler.closeTransactionSession();
        return addresses;
    }

    @Override
    public void save(Address address) {
        currentSession = SessionHandler.openTransaction();
        currentSession.save(address);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void delete(Address address) {
        currentSession = SessionHandler.openTransaction();
        currentSession.delete(address);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void update(Address address) {
        currentSession = SessionHandler.openTransaction();
        currentSession.update(address);
        SessionHandler.closeTransactionSession();
    }
}
