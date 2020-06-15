import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/service/shared.service';
import { EmployeesService } from 'src/app/service/employees.service';
import { Router } from '@angular/router';
import { Employees } from 'src/app/model/employees';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public shared: SharedService;

  constructor(
    private employeesService: EmployeesService,
    private router: Router
  ) {
    this.shared = SharedService.getInstance();
    this.shared.employees = new Employees(null,'','','','',null,null,'');
   }

  ngOnInit() {
  }

  signOut() : void{
    this.shared.token = null;
    this.shared.employees = null;
    window.location.href = '/login';
    window.location.reload();
  }
}
