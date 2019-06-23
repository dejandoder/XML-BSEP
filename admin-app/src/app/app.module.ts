import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { RequestOptions } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './service/AuthService';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './guards/AuthGuard';
import { RandomGuard } from './guards/RandomGuard';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import { UsersComponent } from './users/users.component';
import { CommentsComponent } from './comments/comments.component';
import { ServicesAndCategoriesComponent } from './services-and-categories/services-and-categories.component';
import { AccServicesService } from './service/AccServicesService';
import { AuthInterceptor } from './http-interceptor/AuthInterceptor';
import { AccTypeService } from './service/AccTypeService';
import {MatIconModule} from '@angular/material/icon';
import { UserService } from './service/UserService';
import { RecensionService } from './service/RecensionService';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    UsersComponent,
    CommentsComponent,
    ServicesAndCategoriesComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatTabsModule,
    MatIconModule,
    FormsModule
  ],
  providers: [
    AuthService,
    AuthGuard,
    AccServicesService,
    AccTypeService,
    UserService,
    RecensionService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    RandomGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
