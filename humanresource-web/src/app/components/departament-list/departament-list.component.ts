import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/service/shared.service';
import { MessageService } from 'src/app/service/message.service';
import { DepartamentService } from 'src/app/service/departament.service';
import { Router } from '@angular/router';
import { ResponseDto } from 'src/app/model/responseDto';

@Component({
  selector: 'app-departament-list',
  templateUrl: './departament-list.component.html',
  styleUrls: ['./departament-list.component.css']
})
export class DepartamentListComponent implements OnInit {

  page: number = 0;
  count: number = 5;
  pages: Array<number>;
  shared: SharedService;
  message: {type:string, text:string};
  classCss: {};
  listDepartament = [];

  constructor(
    private messageService: MessageService,
    private departamentService: DepartamentService,
    private router: Router
  ) { this.shared = SharedService.getInstance(); }

  ngOnInit() {
    this.findAll(this.page,this.count);
  }

  findAll(page:number,count:number){
    this.departamentService.findAll(page,count).subscribe((responseDto:ResponseDto) => {
      this.listDepartament = responseDto['data']['content'];
      this.pages = new Array(responseDto['data']['totalPages']);
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  delete(id:number){
    this.messageService.confirm('Do you want to delete the email ?')
      .then((candelete:boolean) => {
          if(candelete){            
            this.departamentService.delete(id).subscribe((responseDto:ResponseDto) => {
              this.showMessage({
                type: 'success',
                text: 'Record deleted'
              });
              this.findAll(this.page,this.count);
            }, err => {
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
          });
        }
    });
  }

  setNextPage(event:any){
    event.preventDefault();
    if(this.page+1 < this.pages.length){
      this.page = this.page +1;
      this.findAll(this.page,this.count);
    }
  }

  setPreviousPage(event:any){
    event.preventDefault();
    if(this.page > 0){
      this.page = this.page -1;
      this.findAll(this.page,this.count);
    }
  }

  setPage(i,event:any){
    event.preventDefault();
    this.page = i;
    this.findAll(this.page,this.count);
  }

  private showMessage(message: {type:string, text:string}): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type:string): void {
    this.classCss = {
      'alert': true
    }
    this.classCss['alert-'+type] = true;
  }

}
