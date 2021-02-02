import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  constructor(private router: Router, public http: HttpClient, public ls: LoginService) { }

  ngOnInit(): void {
  }
  public onLoginClick() {
    this.router.navigate(['api/students']);
  }
  doLogin() {
    let resp = this.ls.login(this.username, this.password);
    resp.subscribe(data => {
      console.log(data)
    })
  }

}
