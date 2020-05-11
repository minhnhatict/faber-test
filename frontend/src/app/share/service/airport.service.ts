import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Constansts} from "../constansts";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AirportService {
  private readonly adminApiUrl;
  private readonly apiUrl;

  constructor(
    private httpClient: HttpClient
  ) {
    this.adminApiUrl = `${Constansts.ADMIN_API_URL}/airport`;
    this.apiUrl = `${Constansts.BASE_API_URL}/airport`;
  }

  public getList(): Observable<any> {
    return this.httpClient.get(this.apiUrl)
  }

  public getListAdmin(): Observable<any> {
    return this.httpClient.get(this.adminApiUrl)
  }

  public save(airPort: any): Observable<any> {
    return this.httpClient.post(this.adminApiUrl, airPort);
  }

  public delete(id: any) {
    const url = `${this.adminApiUrl}/${id}`;
    return this.httpClient.delete(url);
  }

  public search(searchKey: string): Observable<any> {
    const url = `${this.adminApiUrl}/search`;
    const params = new HttpParams().set("searchKey", searchKey);
    return this.httpClient.get(url, {params: params});
  }

  public searchAvailableDeparture() {
    const url = `${this.apiUrl}/search-available-departure`;
    return this.httpClient.get(url);
  }

  public searchAvailableArrival(depatureAiport: any) {
    const url = `${this.apiUrl}/search-available-arrival`;
    const params = new HttpParams().set("departureAirportId", depatureAiport.id);
    return this.httpClient.get(url, {params: params});
  }
}
