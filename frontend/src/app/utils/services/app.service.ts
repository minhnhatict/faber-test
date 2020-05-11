import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  constructor(private router: Router) {
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
