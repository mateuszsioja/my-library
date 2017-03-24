import {OnInit, Component} from "@angular/core";
import {User} from "../model/user";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegisterService} from "./register.service";

@Component({
  moduleId: module.id,
  selector: 'register',
  templateUrl: 'register.component.html',
  styleUrls: ['register.component.css']
})

export class RegisterComponent implements OnInit {

  user = new User();
  userForm: FormGroup;

  constructor(private registerService: RegisterService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void {
    this.createFrom();
  }

  createFrom(): void {
    this.userForm = this.formBuilder.group({
      name: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ],
      surname: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ],
      login: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ],
      password: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(25)
      ]
      ]
    });

    this.userForm.valueChanges
      .subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }

  onValueChanged(data?: any) {
    if (!this.userForm) return;
    const form = this.userForm;
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
    'name': '',
    'surname': '',
    'login': '',
    'password': ''
  };

  validationMessages = {
    'name': {
      'required': 'Name is required',
      'minlength': 'Name must be at least 2 characters long.',
      'maxlength': 'Name cannot be more than 25 characters long.'
    },
    'surname': {
      'required': 'Surname is required',
      'minlength': 'Surname must be at least 2 characters long.',
      'maxlength': 'Surname cannot be more than 25 characters long.'
    },
    'login': {
      'required': 'Login is required',
      'minlength': 'Login must be at least 2 characters long.',
      'maxlength': 'Login cannot be more than 25 characters long.'
    },
    'password': {
      'required': 'Password is required',
      'minlength': 'Password Number must be at least 2 characters long.',
      'maxlength': 'Password Number cannot be more than 25 characters long.'
    }
  };

  add(user: User): void {
    if (this.userForm.status == 'VALID')
      this.registerService.register(user)
        .subscribe(() => this.router.navigate(['/login']));
  }
}
