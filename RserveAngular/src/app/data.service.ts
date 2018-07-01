import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private baseUrl = 'http://localhost:8080/';

  getDataFrame(dsn) {
    return this.http.get(this.baseUrl + 'api/getDF/' + dsn).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Index Error');
      })
    );
  }

  getSumStats(dsn) {
    return this.http.get(this.baseUrl + 'api/getDF/' + dsn + '/stats').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Sum Stats Error');
      })
    );
  }

  getLinModel(dsn, respVar) {
    console.log(dsn + ' : ' + respVar);
    return this.http.get(this.baseUrl + 'api/getLinModel/' + dsn + '/response/' + respVar).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Lin Model Error');
      })
    );
  }

  getVarNames(dsn) {
    console.log(dsn);
    return this.http.get(this.baseUrl + 'api/getVarNames/' + dsn).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Get Var Names Error');
      })
    );
  }

  constructor(private http: HttpClient) { }
}
