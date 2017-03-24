import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {

  constructor(private router: Router) {
  }

  logout() {
    localStorage.removeItem('login');
    localStorage.removeItem('password');
    this.router.navigate(['/login']);
  }
}
