import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Students } from './students';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  url = environment.API;

  constructor(private http: HttpClient) { }

  public getStudents() {
    //let username = 'ivasavic';
   // let password = 'iva123';
    //const headers = new HttpHeaders({ AUthorization: 'Basic' + btoa(username + ":" + password) })
   // return this.http.get<Students[]>(this.url);
   return this.http.get('https://studentservice.herokuapp.com/api/student').pipe(map((response: any) => response));
  }

}
