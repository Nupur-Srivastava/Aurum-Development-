import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth/signup',
    pathMatch: 'full'
  },
  {
    path: 'auth/signup',
    loadComponent: () =>
      import('./features/auth/pages/signup/signup.component')
        .then(m => m.SignupComponent)
  },
  {
    path: 'auth/signin',
    loadComponent: () =>
      import('./features/auth/pages/signin/signin.component')
        .then(m => m.SigninComponent)
  }
];