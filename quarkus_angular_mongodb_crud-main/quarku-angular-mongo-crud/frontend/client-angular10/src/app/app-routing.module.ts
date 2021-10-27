import { UserDetailsComponent } from './user-details/user-details.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserListComponent } from './user-list/user-list.component';
import { UpdateUserComponent } from './update-user/update-user.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'users', component: UserListComponent },
  { path: 'add', component: CreateUserComponent },
  { path: 'update/:_id', component: UpdateUserComponent },
  { path: 'details/:_id', component: UserDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }