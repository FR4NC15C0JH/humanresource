import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Job } from 'src/app/model/job';
import { SharedService } from 'src/app/service/shared.service';
import { JobService } from 'src/app/service/job.service';
import { ActivatedRoute } from '@angular/router';
import { ResponseDto } from 'src/app/model/responseDto';

@Component({
  selector: 'app-job-new',
  templateUrl: './job-new.component.html',
  styleUrls: ['./job-new.component.css']
})
export class JobNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  job = new Job(null,'');
  shared: SharedService;
  message: {type:string, text:string};
  classCss: {};

  constructor(
    private jobService: JobService,
    private route: ActivatedRoute
  ) { this.shared = SharedService.getInstance(); }

  ngOnInit() {
    let id:number = this.route.snapshot.params['id'];
    if(id != undefined){
      this.findById(id);
    }
  }

  findById(id:number){
    this.jobService.findById(id).subscribe((responseDto: ResponseDto) => {
      this.job = responseDto.data;
    } , err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  register(){
    //this.message = {};
    this.jobService.create(this.job).subscribe((responseDto: ResponseDto) => {
      this.job = new Job(null,'');
      let jobReturn : Job = responseDto.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `Registered ${jobReturn.name} successfully`
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
      'alert' : true
    }
    this.classCss['alert-'+type] = true;
  }

}
