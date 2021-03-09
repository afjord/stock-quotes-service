package tk.stockquotesservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Entity
@Table(name = "expectation")
public class Expectation {

  @Id
  @Column(name = "user_id")
  private int userId;

  @Id
  @Column(name = "symbol_id")
  private int symbolId;

  @Column(name = "expected_price")
  private double expectedPrice;

  public double getExpectedPrice() {
	return expectedPrice;
  }

  public void setExpectedPrice(double expectedPrice) {
	this.expectedPrice = expectedPrice;
  }
}
