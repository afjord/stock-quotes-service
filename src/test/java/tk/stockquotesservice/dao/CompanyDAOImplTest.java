package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.StockQuotesServiceApplication;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK,
	classes = StockQuotesServiceApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class CompanyDAOImplTest {

  @Autowired
  SessionFactory factory;

  @Autowired
  CompanyDAO companyDAO;

  @Test
  public void addCompany() {
	Session session = factory.getCurrentSession();

	Company company1 = new Company();
	company1.setSymbol("CMP1");
	company1.setExchange("NAS");
	Company company2 = new Company();
	company2.setExchange("NAS2");
	company2.setSymbol("CMP2");

	companyDAO.addCompany(company1);
	companyDAO.addCompany(company2);
	Query<Company> query = session.createQuery("from Company", Company.class);
	assertEquals(2, query.getResultList().size());
  }

  @Test
  public void getCompany() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
		"('CMP', 'NAS'), ('CMP2', 'EXC')").executeUpdate();

	Company company1 = companyDAO.getCompany(new CompanyPK("CMP", "NAS"));
	Company company2 = companyDAO.getCompany(new CompanyPK("CMP2", "EXC"));
	assertEquals("CMP", company1.getSymbol());
	assertEquals("NAS", company1.getExchange());
	assertEquals("CMP2", company2.getSymbol());
	assertEquals("EXC", company2.getExchange());
  }

  @Test
  public void updateCompany() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
		"('CMP', 'NAS'), ('CMP2', 'EXC')").executeUpdate();

	Company company = companyDAO.getCompany(new CompanyPK("CMP2", "EXC"));
	company.setCompanyName("Test name");
	companyDAO.updateCompany(company);
	Company companyCheck = session
		.createQuery("from Company where symbol = 'CMP2' and exchange = 'EXC'", Company.class)
		.getSingleResult();
	assertEquals("Test name", companyCheck.getCompanyName());
  }

  @Test
  public void updateCompany_NPE() {
    Company company = new Company(new CompanyPK("CMP2", "EXC"));
    assertThrows(NullPointerException.class, () -> companyDAO.updateCompany(company));
  }

  @Test
  public void deleteCompany() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
		"('CMP', 'NAS'), ('CMP2', 'EXC')").executeUpdate();
	companyDAO.deleteCompany(new CompanyPK("CMP2", "EXC"));
	List<Company> companies = session.createQuery("from Company", Company.class).getResultList();
	assertEquals(1, companies.size());
	assertEquals("NAS", companies.get(0).getExchange());
  }
}