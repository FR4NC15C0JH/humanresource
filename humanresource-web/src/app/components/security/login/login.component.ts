import { Component, OnInit } from '@angular/core';
import { Employees } from 'src/app/model/employees';
import { SharedService } from 'src/app/service/shared.service';
import { EmployeesService } from 'src/app/service/employees.service';
import { CurrentUserAuthenticationDto } from 'src/app/model/currentUserAuthenticationDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  employees = new Employees(null,'','','','',null,null,'');
  shared: SharedService;
  message: string;

  constructor(
    private employeesService: EmployeesService,
    private router: Router,
  ) {
    this.shared = SharedService.getInstance();
   }

  ngOnInit() {    
  }

  login(){
    this.message = '';
    this.employeesService.login(this.employees).subscribe((userAuthentication: CurrentUserAuthenticationDto) => {
      this.shared.token = userAuthentication.token;
      this.shared.employees = userAuthentication.employees;
      this.shared.employees.profiles = this.shared.employees.profiles.substring(5);
      this.shared.showTemplate.emit(true);
      //this.router.navigate(['/']);
      this.redirectEmployeesProfile(this.shared.employees.id);
    }, erro => {
      this.shared.token = null;
      this.shared.employees = null;
      this.shared.showTemplate.emit(false);
      this.message = 'Erro';
    });
  }

  redirectEmployeesProfile(id:number){
    this.router.navigate(['/employees-profile',id]);
  }

  redirectEmployeesNew(){
    this.router.navigate(['/employees-new']);
  }

  cancelLogin(){
    this.message = '';
    this.employees = new Employees(null,'','','','',null,null,'');
    window.location.href = '/login';
    window.location.reload();
  }

  getFormGroupClass(isInvalid: boolean, isDirty:boolean): {} {
    return{
      'form-group': true,
      'has-error': isInvalid && isDirty,
      'has-success': !isInvalid && isDirty
    };
  }

}
