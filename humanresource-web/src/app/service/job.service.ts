import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Job } from '../model/job';
import { LOCAL_HOST } from './constant';

@Injectable()
export class JobService {

  constructor(private http: HttpClient) { }

  create(job: Job){
    job.id = null;
    return this.http.post(`${LOCAL_HOST}/api/job`,job);
  }

  findAll(page:number,count:number){
    return this.http.get(`${LOCAL_HOST}/api/job/${page}/${count}`);
  }

  list(){
    return this.http.get(`${LOCAL_HOST}/api/job/list`);
  }

  findById(id:number){
    return this.http.get(`${LOCAL_HOST}/api/job/${id}`);
  }

  delete(id:number){
    return this.http.delete(`${LOCAL_HOST}/api/job/${id}`);
  }
}
