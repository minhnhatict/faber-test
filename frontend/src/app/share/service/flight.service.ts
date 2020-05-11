import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Constansts} from "../constansts";
import {Observable} from "rxjs";
import {NgbDate} from "@ng-bootstrap/ng-bootstrap";

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  private readonly adminApiUrl;
  private readonly apiUrl;

  constructor(
    private httpClient: HttpClient
  ) {
    this.adminApiUrl = `${Constansts.ADMIN_API_URL}/flight`;
    this.apiUrl = `${Constansts.BASE_API_URL}/flight`;
  }

  public getList(): Observable<any> {
    return this.httpClient.get(this.adminApiUrl)
  }

  /**
   * search list of flights
   *
   * @param departureAirport
   * @param arrivalAirport
   * @param fromDate
   * @param toDate
   */
  public search(departureAirport, arrivalAirport, fromDate) {
    const url = `${this.apiUrl}/search`;
    const params = new HttpParams()
      .set('departureAirport', departureAirport)
      .set('arrivalAirport', arrivalAirport)
      .set('fromDate', fromDate);
    return this.httpClient.get(url, {params: params});
  }

  /**
   * submit request to create / update a Flight
   * @param flightForm
   */
  public save(flightForm: any) {
    flightForm.departureDateTime = this.calculateDateTime(flightForm.departureDate, flightForm.departureTime);
    flightForm.arrivalDateTime = this.calculateDateTime(flightForm.arrivalDate, flightForm.arrivalTime);

    return this.httpClient.post(this.adminApiUrl, flightForm);
  }

  /**
   * submit request to delete Flight
   *
   * @param id
   */
  public delete(id: any) {
    const url = `${this.adminApiUrl}/${id}`;
    return this.httpClient.delete(url);
  }

  private calculateDateTime(date: NgbDate, time: any): number {
    const resultDate = new Date();
    resultDate.setDate(date.day);
    resultDate.setMonth(date.month - 1);
    resultDate.setFullYear(date.year);

    resultDate.setHours(0);
    resultDate.setMinutes(0);
    resultDate.setSeconds(0);

    let miliSeconds = resultDate.getTime();
    miliSeconds += time.hour * 60 * 60 * 1000;
    miliSeconds += time.minute * 60 * 1000;
    return miliSeconds;
  }
}
