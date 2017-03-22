import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs";
import {Book} from "../domain/Book";
import 'rxjs/add/operator/map';

@Injectable()
export class BookService {
  private bookUrl = 'http://localhost:8080/api/books/';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  getBooks(): Observable<Book[]> {
    return this.http.get(this.bookUrl)
      .map(response => response.json() as Book[]);
  }
}
