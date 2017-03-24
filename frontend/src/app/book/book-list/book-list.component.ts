import {Component, OnInit} from "@angular/core";
import {Book} from "../../model/Book";
import {BookService} from "../book.service";

@Component({
  moduleId: module.id,
  selector: 'book-list',
  templateUrl: 'book-list.component.html',
  styleUrls: ['book-list.component.css']
})

export class BookListComponent implements OnInit {

  books: Book[];
  er: string;

  constructor(private bookService: BookService) {
  }

  ngOnInit(): void {
    this.getBooksForLoggedInUser();
  }

  getBooksForLoggedInUser() {
    this.bookService.getBooks().subscribe(books => {
      this.books = books;
    }, (err) => {
      if (err === 'Unauthorized') {
        this.er = "Unauthorized";
      }
    })
  }

  delete(book: Book): void {
    this.bookService.deleteBook(book.bookId)
      .subscribe(() => this.getBooksForLoggedInUser())
  }
}
