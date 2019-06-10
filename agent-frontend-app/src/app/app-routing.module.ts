import { NgModule } from '@angular/core';
import { Routes, RouterModule, Route } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guards/AuthGuard';
import { RandomGuard } from './guards/RandomGuard';
import { HomeComponent } from './components/home/home.component';

const routes: Route []= [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, canActivate: [AuthGuard]},
  {
    path: 'home', 
    component: HomeComponent, 
    canActivate: [RandomGuard],
   /* children : [
      {path: '', redirectTo: 'users', pathMatch: 'full'},
      {path: "users", component: UsersComponent},
      {path: "comments", component: CommentsComponent},
      {path: "services&categories", component: ServicesAndCategoriesComponent},
      {path: '**', redirectTo: 'users', pathMatch: 'full'},
    ]*/
  },
  {path: '**',  redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
