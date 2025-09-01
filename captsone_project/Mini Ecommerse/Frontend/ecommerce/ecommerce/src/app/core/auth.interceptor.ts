import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');  // token you saved after login
  if (token) {
    req = req.clone({
      setHeaders: { Authorization: token }   // backend expects "Bearer <token>"
    });
  }
  return next(req);
};
