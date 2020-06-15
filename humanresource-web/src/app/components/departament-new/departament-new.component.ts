import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Departament } from 'src/app/model/departament';
import { SharedService } from 'src/app/service/shared.service';
import { DepartamentService } from 'src/app/service/departament.service';
import { ActivatedRoute } from '@angular/router';
import { ResponseDto } from 'src/app/model/responseDto';

@Component({
  selector: 'app-departament-new',
  templateUrl: './departament-new.component.html',
  styleUrls: ['./departament-new.component.css']
})
export class DepartamentNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  departament = new Departament(null,'');
  shared: SharedService;
  message: {type:string, text:string};
  classCss: {};
  listDepartament = [];

  constructor(
    private departamentService: DepartamentService,
    private route: ActivatedRoute
  ) { this.shared = SharedService.getInstance(); }

  ngOnInit() {
    let id:number = this.route.snapshot.params['id'];
    if(id != undefined){
      this.findById(id);
    }
  }

  findById(id:number){
    this.departamentService.findById(id).subscribe((responseDto: ResponseDto) => {
      this.departament = responseDto.data;
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  register(){
    //this.message = {};
    this.departamentService.create(this.departament).subscribe((responseDto: ResponseDto) => {
      this.departament = new Departament(null,'');
      let departamentReturn : Departament = responseDto.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `Registered ${departamentReturn.name} successfully`
      });
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
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
       'alert': true
     }
     this.classCss['alert-'+type] =  true;
  }

}
