package tk.stockquotesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;
import tk.stockquotesservice.externalServicesClients.IEXClient;

import java.util.*;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

  private CompanyDAO companyDAO;

  private IEXClient iexClient;

  @Value("${io.iexcloud.appID}")
  private String iexAppID;

  @Autowired
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void setCompanyDAO(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
  }

  @Autowired
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void setIexController(IEXClient iexClient) {
	this.iexClient = iexClient;
  }

  @Override
  public void addCompany(Company company) {
	companyDAO.addCompany(company);
  }

  @Override
  public Company getCompanyBySymbol(String symbol) {
	return companyDAO.getCompanyBySymbol(symbol);
  }

  @Override
  public void addCompanies(Iterable<Company> collection) {
	for (Company company : collection) {
	  companyDAO.addCompany(company);
	}
  }

  @Override
  public void updateCompany(Company company) {
	companyDAO.updateCompany(company);
  }

  @Override
  public void deleteCompanyBySymbol(String symbol) {
	companyDAO.deleteCompanyBySymbol(symbol);
  }

  @Override
  public Map<User, Expectation> getAllSubscribedUsers(String symbol) {
	return companyDAO.getCompanyBySymbol(symbol).getUsers();
  }

  @Override
  public Company getCompanyBySymbolIfNotFoundGetThemFromIEX(String symbol) {
	return Objects.requireNonNullElse(
			companyDAO.getCompanyBySymbol(symbol),
			iexClient.getCompany(symbol, iexAppID));
  }

  @Override
  public void addOrUpdateCompany(Company company) {
	companyDAO.addOrUpdateCompany(company);
  }

}
