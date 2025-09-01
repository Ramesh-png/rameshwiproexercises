import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { App } from './app/app';
import { routes } from './app/app.routes';
import { authInterceptor } from './app/core/auth.interceptor'; 
import { FormsModule } from '@angular/forms';
import { importProvidersFrom } from '@angular/core';


bootstrapApplication(App, {
  providers: [
    provideHttpClient(
      withInterceptors([authInterceptor])  // 👈 add interceptor here
    ),
    provideRouter(routes),
    importProvidersFrom(FormsModule)
  ]
}).catch(err => console.error(err));
