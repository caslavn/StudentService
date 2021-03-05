import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StudentsComponent } from './student-start/students/students.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './services/auth.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './auth/auth.interceptor';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { StudentExamComponent } from './student-start/student-exam/student-exam.component';
import { HeaderComponent } from './header/header.component';
import { ExamFailedComponent } from './student-start/exam-failed/exam-failed.component';
import { SearchfilterPipe } from './searchfilter.pipe';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { LoadingService } from './services/loading.service';
import { LoadingInterceptor } from './loading.interceptor';
import { AdminComponent } from './student-start/admin/admin.component';
import { JwtHelperService } from '@auth0/angular-jwt';

@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    LoginComponent,
    StudentExamComponent,
    HeaderComponent,
    ExamFailedComponent,
    SearchfilterPipe,
    AdminComponent,
  ],
  imports: [
    BrowserModule, 
    HttpClientModule,
    NgbModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule,
    MatProgressBarModule,
    
  ],
  exports: [RouterModule],
  providers: [ AuthService, LoadingService,
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: LoadingInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

