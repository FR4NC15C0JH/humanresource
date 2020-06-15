import { Component, OnInit } from '@angular/core';
import { Employees } from 'src/app/model/employees';
import { SharedService } from 'src/app/service/shared.service';
import { EmployeesService } from 'src/app/service/employees.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseDto } from 'src/app/model/responseDto';

@Component({
  selector: 'app-employees-profile',
  templateUrl: './employees-profile.component.html',
  styleUrls: ['./employees-profile.component.css']
})
export class EmployeesProfileComponent implements OnInit {

  employees = new Employees(null,'','','','',null,null,'');
  shared: SharedService;
  message : {type:string, text:string};
  classCss : {};

  constructor(
    private employeesService: EmployeesService,
    private route: ActivatedRoute,
    private router: Router,
  ) { 
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    let id:number = this.route.snapshot.params['id'];
    if(id != undefined){
      this.findById(id);
    }
  }

  findById(id:number){
    this.employeesService.findById(id).subscribe((responseDto: ResponseDto) => {
      this.employees = responseDto.data;
    }, erro => {
      this.showMessage({
        type: 'error',
        text: erro['error']['errors'][0]
      });
    });
  }

  edit(id:number){
    this.router.navigate(['/employees-edit',id]);
  }

  getFormGroupClass(isInvalid: boolean, isDirty:boolean): {} {
    return {
      'form-group': true,
      'has-error' : isInvalid  && isDirty,
      'has-success' : !isInvalid  && isDirty
    };
  }
  
  private showMessage(message: {type: string, text: string}): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  } 
  
  private buildClasses(type: string): void {
    this.classCss = {
      'alert' : true
    }
    this.classCss['alert-'+type] = true;
  }

}
