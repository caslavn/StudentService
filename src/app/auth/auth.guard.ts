import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { nextTick } from 'process';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';
import { LoginInfo } from '../model/login-info';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private aS: AuthService) { }

  canActivate(): boolean {
    if (this.aS.isLoggedIn()) {

          this.router.navigate(['/login']);
      }
      return !this.aS.isLoggedIn();
  }
}


