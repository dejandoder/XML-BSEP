import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AccomodationsComponent } from './components/accomodations/accomodations.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { MessagesComponent } from './components/messages/messages.component';
import { AuthGuard } from './guards/AuthGuard';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
{path: '',  redirectTo: '/home', pathMatch: 'full' },
{path: 'registration', component: RegistrationComponent, canActivate: [AuthGuard]},
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
