import {Component, OnInit} from "@angular/core";
import {Book} from "../../model/Book";
import {BookService} from "../book.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'book-add',
  templateUrl: 'book-add.component.html',
  styleUrls: ['book-add.component.css']
})

export class BookAddComponent implements OnInit {

  book = new Book();
  bookForm: FormGroup;

  constructor(private bookService: BookService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void {
    this.createFrom();
  }

  createFrom(): void {
    this.bookForm = this.formBuilder.group({
      author: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ],
      title: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ],
      category: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ],
      serialNumber: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ]
    });

    this.bookForm.valueChanges
      .subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }

  onValueChanged(data?: any) {
    if (!this.bookForm) return;
    const form = this.bookForm;
    for (const field in this.formErrors) {
      this.formErrors[field] = '';
      const control = form.get(field);
      if (control && control.dirty && !control.valid) {
        const messages = this.validationMessages[field];
        for (const key in control.errors) {
          this.formErrors[field] += messages[key] + ' ';
        }
      }
    }
  }

  formErrors = {
    'author': '',
    'title': '',
    'category': '',
    'serialNumber': ''
  };

  validationMessages = {
    'author': {
      'required': 'Author is required',
      'minlength': 'Author must be at least 2 characters long.',
      'maxlength': 'Author cannot be more than 25 characters long.'
    },
    'title': {
      'required': 'Title is required',
      'minlength': 'Title must be at least 2 characters long.',
      'maxlength': 'Title cannot be more than 25 characters long.'
    },
    'category': {
      'required': 'Category is required',
      'minlength': 'Category must be at least 2 characters long.',
      'maxlength': 'Category cannot be more than 25 characters long.'
    },
    'serialNumber': {
      'required': 'Serial Number is required',
      'minlength': 'Serial Number must be at least 2 characters long.',
      'maxlength': 'Serial Number cannot be more than 25 characters long.'
    }
  };

  add(book: Book): void {
    if (this.bookForm.status == 'VALID')
      this.bookService.add(book)
        .subscribe(() => this.router.navigate(['/books']));
  }

}
