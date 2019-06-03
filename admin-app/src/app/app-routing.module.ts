import { NgModule } from '@angular/core';
import { Route, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

import { AuthGuard } from './guards/AuthGuard'
import { RandomGuard } from './guards/RandomGuard'
import { UsersComponent } from './users/users.component';
import { CommentsComponent } from './comments/comments.component';
import { ServicesAndCategoriesComponent } from './services-and-categories/services-and-categories.component';

const routes: Route []= [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, canActivate: [AuthGuard]},
  {
    path: 'home', 
    component: HomeComponent, 
    canActivate: [RandomGuard],
    children : [
      {path: '', redirectTo: 'users', pathMatch: 'full'},
      {path: "users", component: UsersComponent},
      {path: "comments", component: CommentsComponent},
      {path: "services&categories", component: ServicesAndCategoriesComponent},
      {path: '**', redirectTo: 'users', pathMatch: 'full'},
    ]
  },
  {path: '**',  redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
