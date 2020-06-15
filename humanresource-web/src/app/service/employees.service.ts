import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LOCAL_HOST } from './constant';
import { Employees } from '../model/employees';

@Injectable()
export class EmployeesService {

  constructor(private http: HttpClient) { }

  login(employees: Employees){
    return this.http.post(`${LOCAL_HOST}/api/auth`, employees);
  }

  createOrUpdate(employees: Employees){
    if(employees.id != null && employees.id != null){
      return this.http.put(`${LOCAL_HOST}/api/employees`, employees);
    }else {
      employees.id = null;
      return this.http.post(`${LOCAL_HOST}/api/employees/create`, employees);
    }
  }

  findAll(page:number,count:number){
    return this.http.get(`${LOCAL_HOST}/api/employees/${page}/${count}`);
  }

  findById(id:number){
    return this.http.get(`${LOCAL_HOST}/api/employees/${id}`);
  }
  
  delete(id:number){
    return this.http.delete(`${LOCAL_HOST}/api/employees/${id}`);
  }
}
