import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { LoadingService } from '../services/loading.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoading: Subject<boolean> = this.ls.isLoading;

  constructor(private aS: AuthService, private router: Router, private ls: LoadingService) { }

  ngOnInit() {

  }

  onSubmit() {
    this.aS.login(this.form).subscribe(
      res => {
        if (this.aS.isLoggedIn) {
          this.router.navigate(['api/students']);
        } else{
          err => console.log(err)
        }
      }
    )};



  }
  
