package tk.stockquotesservice.entity;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Entity
@Table(name = "user")
public class User {

  @Id
  @Column(name = "user_id")
  private int id;

  @Column(name = "cur_subscribes")
  private int curSubscribes;

  @Column(name = "max_subscribes")
  private int maxSubscribes;

  @ElementCollection
  @CollectionTable(name = "expectation",
  joinColumns = @JoinColumn(name = "user_id"))
  @MapKeyJoinColumn(name = "symbol_id")
  @Column(name = "expected_price")
  private Map<Symbol, Double> symbols;

  public User() {
  }

  public User(int id) {
	maxSubscribes = 3;
	this.id = id;
  }

  public int getId() {
	return id;
  }

  public void setId(int id) {
	this.id = id;
  }

  public int getCurSubscribes() {
	return curSubscribes;
  }

  public void setCurSubscribes(int curSubscribes) {
	this.curSubscribes = curSubscribes;
  }

  public int getMaxSubscribes() {
	return maxSubscribes;
  }

  public void setMaxSubscribes(int maxSubscribes) {
	this.maxSubscribes = maxSubscribes;
  }

  public Map<Symbol, Double> getSymbols() {
	return symbols;
  }

  public void setSymbols(Map<Symbol, Double> symbols) {
	this.symbols = symbols;
  }
}
