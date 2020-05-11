package faber.codetest.nhat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "fbt_flight")
public class Flight extends BaseEntity {

    public static final String DEPARTURE_DATETIME = "departureDateTime";
    public static final String DEPARTURE_AIRPORT = "departureAirport";

    public static final String ARRIVAL_DATETIME = "arrivalDateTime";

    @ManyToOne
    @JoinColumn(name = "depart_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column
    private Long departureDateTime;

    @Column
    private Long arrivalDateTime;

    @Column
    private BigDecimal price;

    @Column
    private Integer seatQuantity;

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Long getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Long departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Long getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Long arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(Integer seatQuantity) {
        this.seatQuantity = seatQuantity;
    }
}
