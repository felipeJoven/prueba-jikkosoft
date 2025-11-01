import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AppStorage } from '../storage/app.storage';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const appStorage = inject(AppStorage);
  const token = appStorage.getToken();

  if (token) {
    const cloned = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
    return next(cloned);
  }
  return next(req);
};
