import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { Amarre } from './amarre';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AmarreService {

  constructor(private http: HttpClient) { }

  getAmarres(): Observable<Amarre[]> {
    return this.http.get<Amarre[]>(`${environment.urlLocalApi}/amarres`);
  }
}
