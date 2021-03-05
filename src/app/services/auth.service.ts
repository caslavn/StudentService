import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { catchError, map, mapTo, tap } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { Exam } from "../model/exam";
import { LoginInfo } from "../model/login-info";
import { Students } from "../model/students";
import { Token } from '../model/token';
import { Subject } from '../model/subject';
import { JwtHelperService } from "@auth0/angular-jwt";
import jwt_decode from "jwt-decode";


@Injectable({
    providedIn: 'root'
})

export class AuthService {

    helper = new JwtHelperService();
    decodedToken: any;
    loggedUser: string;

    students: Students[] = [];
    constructor(private http: HttpClient) {
    }

    login(user): Observable<boolean> {
        return this.http.post<any>(environment.api + '/auth/login', user)
            .pipe(

                tap(token => this.doLoginUser(user, user.username, token)),

                mapTo(true),
                catchError(error => {
                    if (confirm('Check your credential'))
                        return of(false);

                }
                ));

    }

    private doLoginUser(user: string, username: string, token: any) {
        this.loggedUser = username;
        this.storeToken(token.token);
        if (user && token) {
            localStorage.setItem('currentUser', JSON.stringify(user));
        }

    }

    getAuthorizationToken() {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        return currentUser.token;
    }

    getToken() {
          let token =  localStorage.getItem('access_token');
        return token;
    }

    refreshToken() {
        return this.http.post<any>(environment.api + '/refresh-token', {
            'refreshToken': this.getToken()
        }).pipe(tap((token: Token) => {
            this.storeToken(token);
        }));
    }

    removeToken() {
        return localStorage.removeItem('access_token');
    }
    removeRefToken() {
        return localStorage.removeItem('refresh_token')
    }

    private storeToken(token: Token) {
        localStorage.setItem('access_token', token.access_token);
        localStorage.setItem('refresh_token', token.refresh_token);
    }

    isLoggedIn() {
        if (localStorage.getItem('currentUser')) {
            return true;
        }
        return false;
    }

    isLoggedOut() {
        return !this.isLoggedIn();
    }

    public getAllStudents() {
        return this.http.get<Students[]>(environment.api + '/api/student',
            { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.getItem('access_token') }) })

    }
    // public getAllStudentsAdmin() {
    // return this.http.get<Students[]>(environment.api + '/api/student/admin',
    // { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.getItem('access_token') }) })

    // }

    public getExam() {
        return this.http.get<Exam[]>(environment.api + '/api/exam',
            { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.getItem('access_token') }) })

    }
    public getSubject() {
        return this.http.get<Subject[]>(environment.api + '/api/exam/students-who-failed',
            { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.getItem('access_token') }) })
    }

    public getStudentsWhoFailed() {
        return this.http.get<Exam[]>(environment.api + '/api/exam/students-who-failed',
            { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.getItem('access_token') }) })

    }

    public insertStudent(student) {
        return this.http.post(environment.api + '/api/student/admin', student,
            { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.getItem('access_token') }) })
    }

    public deleteStudent(student) {
        return this.http.delete<Students[]>(environment.api + '/api/student/admin?' + "index=" + student.index,
            { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.getItem('access_token') }) })
    }

    public Logout() {
        return this.http.get<Exam[]>(environment.api + '/sign-out',
            { headers: new HttpHeaders({ 'Authorization': 'Bearer' + localStorage.removeItem('access_token') + localStorage.removeItem('refresh_token') + localStorage.removeItem('currentUser') }) })
    }

}

