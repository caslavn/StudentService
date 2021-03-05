import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor(private router: Router, private aS: AuthService) { }

  canActivate(): boolean {
 
         let token = this.aS.getToken()
         let decodedrole = jwt_decode(token);
         let role = decodedrole['role'][0]
         if (role == 'ADMIN'){
           return true
         } else{
          if (confirm('Access denied'))
          this.router.navigate(['/api/students']);
          return false;
         }
}
}
