import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';

import { MatTabsModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AccomodationsComponent } from './components/accomodations/accomodations.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { MessagesComponent } from './components/messages/messages.component';

import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { ModalModule } from 'ngx-bootstrap/modal';
import { GooglePlaceModule } from "ngx-google-places-autocomplete";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AccomodationUnitService } from './service/AccomodationUnitService';
import { AuthService } from './service/AuthService';
import { AuthGuard } from './guards/AuthGuard';
import { RandomGuard } from './guards/RandomGuard';
import { AuthInterceptor } from './http-interceptor/AuthInterceptor';
import { AccomodationPreviewComponent } from './components/accomodation-preview/accomodation-preview.component';
import { CarouselModule } from 'ngx-bootstrap/carousel';

import { FontAwesomeModule } from 'ngx-icons';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AccomodationsComponent,
    ReservationsComponent,
    MessagesComponent,
    AccomodationPreviewComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatTabsModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    ModalModule.forRoot(),
    GooglePlaceModule,
    HttpClientModule,
    CarouselModule.forRoot(),
    FontAwesomeModule
  ],
  providers: [
    AccomodationUnitService,
    AuthService,
    AuthGuard,
    RandomGuard,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
