import {Component, OnInit} from "@angular/core";
import {BookService} from "./book/book.service";
import {Book} from "./domain/Book";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  books: Book[];

  ngOnInit(): void {
    this.bookService.getBooks().subscribe(books => this.books = books);
  }

  constructor(private bookService: BookService) {
  }

  title = 'app works!';
}
