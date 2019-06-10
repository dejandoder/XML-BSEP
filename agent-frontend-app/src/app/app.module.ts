import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './guards/AuthGuard';
import { AuthService } from './service/AuthService';
import { AuthInterceptor } from './http-interceptor/AuthInterceptor';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { RandomGuard } from './guards/RandomGuard';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    RandomGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
