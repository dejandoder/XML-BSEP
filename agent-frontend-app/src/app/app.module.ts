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

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import { AccomodationsComponent } from './components/accomodations/accomodations.component';
import { MessagesComponent } from './messages/messages.component';
import { AddAccomodationComponent } from './components/add-accomodation/add-accomodation.component';

import { BsDatepickerModule } from 'ngx-bootstrap/datepicker'
import { AccomodationUnitService } from './service/AccomodationUnitService';
import {MatIconModule} from '@angular/material/icon';
import { ImageService } from './service/ImageService';
import { EditAccomodationComponent } from './components/edit-accomodation/edit-accomodation.component';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { PricePlanService } from './service/PricePlanService';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AccomodationsComponent,
    MessagesComponent,
    AddAccomodationComponent,
    EditAccomodationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MatIconModule,
    MatTabsModule,
    BsDatepickerModule.forRoot(),
    CarouselModule.forRoot(),
    TabsModule.forRoot()
  ],
  providers: [
    AuthService,
    AuthGuard,
    AccomodationUnitService,
    ImageService,
    PricePlanService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    RandomGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
