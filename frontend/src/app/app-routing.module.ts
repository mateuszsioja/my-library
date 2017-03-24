import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {BookListComponent} from "./book/book-list/book-list.component";
import {BookComponent} from "./book/book.component";
import {BookAddComponent} from "./book/book-add/book-add.component";
import {BookEditComponent} from "./book/book-edit/book-edit.component";
import {RegisterComponent} from "./register/register.component";


const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },

  {
    path: 'books',
    component: BookComponent,
    children: [
      {
        path: '',
        component: BookListComponent
      },
      {
        path: 'add-book',
        component: BookAddComponent
      },
      {
        path: 'edit/:id',
        component: BookEditComponent
      }
    ]
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
