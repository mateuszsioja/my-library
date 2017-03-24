import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {User} from "../model/user";
import {Observable} from "rxjs";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

@Injectable()
export class RegisterService {

  private registerUrl = 'http://localhost:8080/api/users/';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  register(user: User): Observable<User> {
    return this.http.post(this.registerUrl, JSON.stringify(user), {headers: this.headers})
      .map(() => user).catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      });
  }
}
