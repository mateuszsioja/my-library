import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {BookService} from "./book/book.service";
import {LoginComponent} from "./login/login.component";
import {AppRoutingModule} from "./app-routing.module";
import {RouterModule} from "@angular/router";
import {BookListComponent} from "./book/book-list/book-list.component";
import {AuthenticationService} from "./services/authentication.service";
import {BookComponent} from "./book/book.component";
import {BookAddComponent} from "./book/book-add/book-add.component";
import {BookEditComponent} from "./book/book-edit/book-edit.component";
import {RegisterComponent} from "./register/register.component";
import {RegisterService} from "./register/register.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    BookComponent,
    BookListComponent,
    BookAddComponent,
    BookEditComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    RouterModule
  ],
  providers: [
    BookService,
    AuthenticationService,
    RegisterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
