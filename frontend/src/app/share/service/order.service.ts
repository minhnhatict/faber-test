import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Constansts} from "../constansts";
import {FormGroup} from "@angular/forms";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly apiUrl;
  private readonly adminApiUrl;

  constructor(
    private httpClient: HttpClient
  ) {
    this.apiUrl = `${Constansts.BASE_API_URL}/order`;
    this.adminApiUrl = `${Constansts.ADMIN_API_URL}/order`;
  }

  public getList(): Observable<any> {
    return this.httpClient.get(this.adminApiUrl);
  }

  /**
   * send request to book flight ticket
   *
   * @param orderForm
   * @param customerFormList
   */
  public save(orderForm: any, customerFormList: FormGroup[]) {
    const url = `${this.apiUrl}/book-ticket`;
    const customerList = [];

    // convert formGroup obj to customer object
    for (const customerForm of customerFormList) {
      customerList.push(OrderService.convertCustomerFormGroup(customerForm));
    }

    const orderParams = {
      orderTime: new Date().getTime(),
      type: orderForm.type,
      departureFlight: orderForm.departureFlight,
      arrivalFlight: orderForm.arrivalFlight,
      totalAdult: orderForm.totalAdult,
      totalChild: orderForm.totalChild,
      totalSenior: orderForm.totalSenior,
      customerList: customerList
    };
    return this.httpClient.post(url, orderParams);
  }

  /**
   * convert formGroup obj to customer object
   *
   * @param customerFormGroup
   */
  private static convertCustomerFormGroup(customerFormGroup: FormGroup) {
    return {
      fullName: customerFormGroup.get('fullName').value,
      birthDate: customerFormGroup.get('birthDate').value.getTime(),
      phone: customerFormGroup.get('phone').value,
      personId: customerFormGroup.get('personId').value,
      email: customerFormGroup.get('email').value
    }
  }
}
