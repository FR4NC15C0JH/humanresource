import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Departament } from '../model/departament';
import { LOCAL_HOST } from './constant';

@Injectable()
export class DepartamentService {

  constructor(private http: HttpClient) { }

  create(departament: Departament){
    departament.id = null;
    return this.http.post(`${LOCAL_HOST}/api/departament`,departament);
  }

  findAll(page:number,count:number){
    return this.http.get(`${LOCAL_HOST}/api/departament/${page}/${count}`);
  }

  list(){
    return this.http.get(`${LOCAL_HOST}/api/departament/list`);
  }

  findById(id:number){
    return this.http.get(`${LOCAL_HOST}/api/departament/${id}`);
  }

  delete(id:number){
    return this.http.delete(`${LOCAL_HOST}/api/departament/${id}`);
  }
}
