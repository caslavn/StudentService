import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoadingService } from './services/loading.service';
import { finalize } from 'rxjs/operators';
import { AuthService } from './services/auth.service';

@Injectable()
export class LoadingInterceptor implements HttpInterceptor {

  constructor(public aS:AuthService ,public ls: LoadingService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.ls.show();
    const reqWithAuth = request.clone  ({
      setHeaders: {
        Authorization: `Bearer ${this.aS.getToken()}`
      }
    });
    return next.handle(reqWithAuth).pipe(
      finalize(() => this.ls.hide())
    );
  }
}
