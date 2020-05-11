import {Injectable} from '@angular/core';
import {Router, CanActivate} from '@angular/router';
import {UserService} from "./user.service";

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public userService: UserService, public router: Router) {}

  canActivate(): boolean {
    try {
      this.userService.getUserProfile().subscribe(
        (resp) => {
          if (resp && resp.data) {
            const authorities = resp.authorities;
            if (authorities && authorities.length > 0) {
              return authorities[0].authority === 'ROLE_ADMIN';
            }
          }
        }
      )
    } catch (e) {
      return false;
    }
  }
}
