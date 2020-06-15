import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ResponseDto } from 'src/app/model/responseDto';
import { Employees } from 'src/app/model/employees';
import { SharedService } from 'src/app/service/shared.service';
import { EmployeesService } from 'src/app/service/employees.service';
import { DepartamentService } from 'src/app/service/departament.service';
import { JobService } from 'src/app/service/job.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-employees-new',
  templateUrl: './employees-new.component.html',
  styleUrls: ['./employees-new.component.css']
})
export class EmployeesNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  employees = new Employees(null,'','','','',null,null,null);
  shared: SharedService;
  message: {type:string, text:string};
  classCss: {};
  listDepartament = [];
  listJobs = [];

  constructor(
    private employeesService: EmployeesService,
    private departamentService: DepartamentService,
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    this.shared = SharedService.getInstance();
   }

  ngOnInit() {
    let id:number = this.route.snapshot.params['id'];
    if(id != undefined){
      this.findById(id);
    }
    this.findAllDepartament();
    this.findAllJobs();
  }

  findById(id:number){
    this.employeesService.findById(id).subscribe((responseDto: ResponseDto) => {
      this.employees = responseDto.data;
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  register(){
    //this.message = {};
    this.employeesService.createOrUpdate(this.employees).subscribe((responseDto: ResponseDto) => {
      this.employees = new Employees(null,'','','','',null,null,null);
      let userReturn : Employees = responseDto.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `Registered ${userReturn.name} successfully`
      });
      this.router.navigate(['/login']);
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  onFileChange(event): void{
    if(event.target.files[0].size > 2000000){
      this.showMessage({
        type: 'error',
        text: 'Maximum image size is 2 MB'
      });
    } else {
      this.employees.image = '';
      var reader = new FileReader();
      reader.onloadend = (e: Event) => {
        this.employees.image = reader.result as string;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  findAllDepartament(){
    this.departamentService.list().subscribe((responseDto: ResponseDto) => {
      this.listDepartament = responseDto['data'];
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  findAllJobs(){
    this.jobService.list().subscribe((responseDto: ResponseDto) => {
      this.listJobs = responseDto['data'];
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  redirectLogin(){
    this.router.navigate(['/login']);
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
      }, 5000);
  }

  private buildClasses(type: string): void {
     this.classCss = {
       'alert': true
     }
     this.classCss['alert-'+type] =  true;
  }

}
