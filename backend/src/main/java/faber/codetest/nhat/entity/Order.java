package faber.codetest.nhat.entity;

import faber.codetest.nhat.enumeration.FlightReturnTypeEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fbt_order")
public class Order extends BaseEntity {

    @Column
    private Long orderTime;

    @Enumerated(EnumType.STRING)
    private FlightReturnTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "departure_flight_id")
    private Flight departureFlight;

    @ManyToOne
    @JoinColumn(name = "arrival_flight_id")
    private Flight arrivalFlight;

    @Column
    private Integer totalAdult;

    @Column
    private Integer totalChildren;

    @Column
    private Integer totalSenior;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "fbt_order_customer",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customerList;

    @Column
    public BigDecimal totalPrice;

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public FlightReturnTypeEnum getType() {
        return type;
    }

    public void setType(FlightReturnTypeEnum type) {
        this.type = type;
    }

    public Flight getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(Flight departureFlight) {
        this.departureFlight = departureFlight;
    }

    public Flight getArrivalFlight() {
        return arrivalFlight;
    }

    public void setArrivalFlight(Flight arrivalFlight) {
        this.arrivalFlight = arrivalFlight;
    }

    public Integer getTotalAdult() {
        return totalAdult;
    }

    public void setTotalAdult(Integer totalAdult) {
        this.totalAdult = totalAdult;
    }

    public Integer getTotalChildren() {
        return totalChildren;
    }

    public void setTotalChildren(Integer totalChildren) {
        this.totalChildren = totalChildren;
    }

    public Integer getTotalSenior() {
        return totalSenior;
    }

    public void setTotalSenior(Integer totalSenior) {
        this.totalSenior = totalSenior;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
