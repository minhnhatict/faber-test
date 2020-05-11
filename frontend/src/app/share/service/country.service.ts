import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Constansts} from "../constansts";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  private readonly adminApiUrl;

  constructor(
    private httpClient: HttpClient
  ) {
    this.adminApiUrl = `${Constansts.ADMIN_API_URL}/country`;
  }

  public search(searchKey): Observable<any> {
    const url = `${this.adminApiUrl}/search`;
    const params = new HttpParams().set("searchKey", searchKey);
    return this.httpClient.get(url, {params: params});
  }
}
