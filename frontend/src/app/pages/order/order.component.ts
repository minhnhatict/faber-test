import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../share/service/order.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  public orderList: [];

  constructor(
    private orderService: OrderService,
  ) {
  }

  ngOnInit(): void {
    this.orderList = [];

    this.orderService.getList().subscribe(
      (resp: any) => {
        if (resp && resp.success) {
          this.orderList = resp.data;
        }
      }
    )
  }

  /**
   * calculate total price of an order
   * @param order
   */
  public calculateTotalPrice(order: any) {
    const departureFlight = order.departureFlight;
    const arrivalFlight = order.arrivalFlight;
    let totalPrice = departureFlight.price;
    if (arrivalFlight) {
      totalPrice += arrivalFlight.price;
    }
    return totalPrice;
  }

  /**
   * get order type: one-way or bio direction
   * @param order
   */
  public getOrderType(order: any) {
    if (order.departureFlight && order.arrivalFlight) {
      return 'Khứ hồi'
    }
    return 'Một chiều';
  }
}
