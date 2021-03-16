package tk.stockquotesservice.service;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

/**
 * @author Andrey Fyodorov
 * Created on 12.03.2021.
 */

public interface CompanyService {

  void addCompany(Company symbol);
  void addCompanies(Iterable<Company> collection);
  Company getCompany(CompanyPK companyPK);
  void updateCompany(Company symbol);
  void deleteCompany(CompanyPK pk);
}
