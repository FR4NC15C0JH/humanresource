import { Injectable, EventEmitter } from '@angular/core';
import { Employees } from '../model/employees';

@Injectable()
export class SharedService {

  public static instance: SharedService = null;
  employees: Employees
  token: string;
  showTemplate = new EventEmitter<boolean>();

  constructor() { 
    return SharedService.instance = SharedService.instance || this;
  }

  public static getInstance(){
    if(this.instance == null){
      this.instance = new SharedService();
    }
    return this.instance;
  }
  /** Filtro de menus visiveis em tela*/
  isLoggedIn():boolean{
    if(this.employees == null){
      return false;
    }
    return this.employees.email != '';
  }
}
