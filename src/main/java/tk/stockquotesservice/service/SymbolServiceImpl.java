package tk.stockquotesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.SymbolDAO;
import tk.stockquotesservice.entity.Symbol;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Service
public class SymbolServiceImpl implements SymbolService {

  private SymbolDAO symbolDAO;

  @Autowired
  public void setSymbolDAO(SymbolDAO symbolDAO) {
	this.symbolDAO = symbolDAO;
  }

  @Override
  @Transactional
  public void add(Symbol symbol) {
    symbolDAO.add(symbol);
  }

  @Override
  @Transactional
  public void addCollection(Iterable<Symbol> collection) {
    for (Symbol symbol : collection) {
      symbolDAO.add(symbol);
    }
  }

  @Override
  @Transactional
  public Symbol getById(int id) {
    return symbolDAO.getById(id);
  }

  @Override
  @Transactional
  public void update(Symbol symbol) {
	symbolDAO.update(symbol);
  }

  @Override
  @Transactional
  public void delete(int id) {
    symbolDAO.delete(id);
  }
}