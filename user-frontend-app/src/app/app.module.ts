import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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


import { ReservationPreviewComponent } from './components/reservation-preview/reservation-preview.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { ReservationService } from './service/ReservationService';
import { MessageService } from './service/MessageService';
import { RecenesionService } from './service/RecensionService';
library.add(fas);


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AccomodationsComponent,
    ReservationsComponent,
    MessagesComponent,
    AccomodationPreviewComponent,
    ReservationPreviewComponent,
  
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
    ReservationService,
    MessageService,
    RecenesionService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
