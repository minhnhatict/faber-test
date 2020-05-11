import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Constansts} from "../constansts";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly apiUrl;

  constructor(
    private httpClient: HttpClient
  ) {
    this.apiUrl = `${Constansts.BASE_API_URL}/user`;
  }

  /**
   * get profile info of logged in user
   */
  public getUserProfile(): Observable<any> {
    const url = `${this.apiUrl}/profile`;
    return this.httpClient.get(url);
  }
}
