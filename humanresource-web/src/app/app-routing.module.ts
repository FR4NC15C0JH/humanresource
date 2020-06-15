import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/security/login/login.component';
import { AuthGuard } from './components/security/auth.guard';
import { DepartamentNewComponent } from './components/departament-new/departament-new.component';
import { DepartamentListComponent } from './components/departament-list/departament-list.component';
import { JobNewComponent } from './components/job-new/job-new.component';
import { JobListComponent } from './components/job-list/job-list.component';
import { EmployeesProfileComponent } from './components/employees-profile/employees-profile.component';
import { EmployeesNewComponent } from './components/employees-new/employees-new.component';
import { EmployeesListComponent } from './components/employees-list/employees-list.component';
import { EmployeesEditComponent } from './components/employees-edit/employees-edit.component';


export const ROUTES: Routes = [
  { path: 'employees-profile/:id' , component : EmployeesProfileComponent, canActivate: [AuthGuard] },
  { path: 'login', component : LoginComponent },
  { path: '' , component : HomeComponent, canActivate: [AuthGuard] },
  { path: 'employees-new' , component : EmployeesNewComponent },
  { path: 'employees-list' , component : EmployeesListComponent, canActivate: [AuthGuard] },
  { path: 'employees-edit/:id' , component : EmployeesEditComponent, canActivate: [AuthGuard] },
  { path: 'departament-new' , component : DepartamentNewComponent, canActivate: [AuthGuard] },
  { path: 'departament-list' , component : DepartamentListComponent, canActivate: [AuthGuard] },
  { path: 'job-new' , component : JobNewComponent, canActivate: [AuthGuard] },
  { path: 'job-list' , component : JobListComponent, canActivate: [AuthGuard] }
]

@NgModule({
  imports: [RouterModule.forRoot(ROUTES)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
