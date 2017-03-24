import {Component, OnInit} from "@angular/core";
import {LoggedInUser} from "../model/logged-in-user";
import {BookService} from "../book/book.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";
import {Book} from "../model/Book";

@Component({
  moduleId: module.id,
  selector: 'login',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})

export class LoginComponent implements OnInit {

  books: Book[];
  er: string;

  user = new LoggedInUser();

  constructor(private bookService: BookService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.authenticationService.logout();
  }

  login() {
    this.authenticationService.logout();
    localStorage.setItem('login', this.user.login);
    localStorage.setItem('password', this.user.password);

    this.bookService.getBooks().subscribe(books => {
      this.books = books;
      this.router.navigate(['/books']);
    }, (err) => {
      if (err === 'Unauthorized') {
        this.er = "Unauthorized";
      }
    })
  }

}
