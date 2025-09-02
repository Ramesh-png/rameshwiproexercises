import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    if(token){ req = req.clone({ setHeaders: { Authorization: `Bearer ${token}` } }); }
    return next.handle(req).pipe(catchError((err: HttpErrorResponse) => {
      if(err.status===401){ alert('Please login again'); localStorage.clear(); location.href='/login'; }
      if(err.status===403){ alert('Not allowed'); }
      return throwError(() => err);
    }));
  }
}
