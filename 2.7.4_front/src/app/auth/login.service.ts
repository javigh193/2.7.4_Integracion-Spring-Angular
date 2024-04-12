import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, map, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("");

  constructor(private http:HttpClient) { 
    this.currentUserLoginOn = new BehaviorSubject<boolean>(sessionStorage.getItem("token") != null);
    this.currentUserData = new BehaviorSubject<String>(sessionStorage.getItem("data") || "");
  }

  login(credentials:LoginRequest):Observable<any> {
    return this.http.post<any>( `${environment.urlLocalHost}/auth/login`, credentials)
      .pipe(
        tap( (userData) => {
          sessionStorage.setItem("token", userData.token)
          sessionStorage.setItem("username", userData.username)
          this.currentUserData.next(userData.token);
          this.currentUserLoginOn.next(true);
        }),
        map((userData) => userData.token),
        catchError(this.handleError)
      );
  }

  logout():void {
    sessionStorage.removeItem("token");
    this.currentUserLoginOn.next(false);
  }

  private handleError(error:HttpErrorResponse) {
    if (error.status===0) {
      console.error("Se ha producido un error", error.error);
    } else {
      console.error("Error", error);
    }
    return throwError( () => new Error('Algo falló. Por favor, inténtelo de nuevo.'))
  }

  get userData():Observable<String> {
    return this.currentUserData.asObservable();
  }

  get userLoginOn():Observable<boolean> {
    return this.currentUserLoginOn.asObservable();
  }

}
