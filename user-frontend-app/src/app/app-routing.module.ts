import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AccomodationsComponent } from './components/accomodations/accomodations.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { MessagesComponent } from './components/messages/messages.component';

const routes: Routes = [
{path: '',  redirectTo: '/home', pathMatch: 'full' },
{path: 'login', component: LoginComponent},
{path: 'home',
 component: HomeComponent,
 children : [
  {path: '',  redirectTo: 'accomodations', pathMatch: 'full' },
  {path: 'accomodations', component: AccomodationsComponent},
  {path: 'reservations', component: ReservationsComponent},
  {path: 'messages', component: MessagesComponent},
  {path: '**',  redirectTo: 'accomodations'}]
},
{path: '**',  redirectTo: '/home' }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
