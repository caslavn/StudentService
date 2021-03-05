import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Students } from '../model/students';
import { AuthService } from '../services/auth.service';
import { LoadingService } from '../services/loading.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isLoading: Subject<boolean> = this.ls.isLoading;
  constructor(public http: HttpClient, public auth: AuthService, public ls: LoadingService) { }

  ngOnInit(): void {

  }

  logout(){
    this.auth.Logout();
    console.log('logout')
  }
  isAdmin(){
   // return this.auth.currentUser.role == 'ADMIN' ? true : false
  }

}
