import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = environment.API2;

  constructor(private http: HttpClient) { }

  public login(username: string, password: string) {
    const headers = new HttpHeaders({ AUthorization: 'Basic' + btoa(username + ":" + password) })
    return this.http.get(this.url, { headers, responseType: 'json' });
  }
}
