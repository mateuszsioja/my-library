import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs";
import {Book} from "../model/Book";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

@Injectable()
export class BookService {
  private bookUrl = 'http://localhost:8080/api/books/';

  constructor(private http: Http) {
  }

  getBooks(): Observable<Book[]> {
    let headers = new Headers({
      'Authorization': 'Basic ' +
      btoa(localStorage.getItem('login') + ':' + localStorage.getItem('password'))
    });

    return this.http.get(this.bookUrl, {headers: headers})
      .map(response => {
        return response.json() as Book[];
      })
      .catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      });
  }

  getBookById(id: number): Observable<Book> {
    let headers = new Headers({
      'Authorization': 'Basic ' +
      btoa(localStorage.getItem('login') + ':' + localStorage.getItem('password'))
    });

    return this.http.get(this.bookUrl + '/' + id, {headers: headers})
      .map(response => {
        return response.json() as Book;
      })
      .catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      });
  }

  add(book: Book): Observable<Book> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' +
      btoa(localStorage.getItem('login') + ':' + localStorage.getItem('password'))
    });
    return this.http.post(this.bookUrl, JSON.stringify(book), {headers: headers})
      .map(() => book).catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      });
  }

  update(book: Book): Observable<Book> {
    let headersGetA = new Headers({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(localStorage.getItem('login') + ':' + localStorage.getItem('password'))
    });
    return this.http.put(this.bookUrl + '/' + book.bookId, JSON.stringify(book), {headers: headersGetA})
      .map(() => book).catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      });
  }

  deleteBook(id: number): Observable<void> {
    let headers = new Headers({
      'Authorization': 'Basic ' +
      btoa(localStorage.getItem('login') + ':' + localStorage.getItem('password'))
    });

    return this.http.delete(this.bookUrl + '/' + id, {headers: headers})
      .map(response => {
        return;
      })
      .catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      });
  }
}
