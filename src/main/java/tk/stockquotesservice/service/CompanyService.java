package tk.stockquotesservice.service;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

/**
 * @author Andrey Fyodorov
 * Created on 12.03.2021.
 */

public interface CompanyService {

  void add(Company symbol);
  void addCollection(Iterable<Company> collection);
  Company get(String symbol, String exchange);
  void update(Company symbol);
  void delete(CompanyPK pk);
}