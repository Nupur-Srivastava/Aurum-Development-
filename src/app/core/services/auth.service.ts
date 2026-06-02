import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpFormData, SignInFormData, AuthResponse } from '../models/user.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl; // e.g. http://localhost:8080/api

  constructor(private http: HttpClient) {}

  signUp(data: SignUpFormData): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/auth/signup`, data);
  }

  signIn(data: SignInFormData): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/auth/signin`, data);
  }

  signInWithGoogle(): void {
    // Redirect to Google OAuth — backend handles this
    window.location.href = `${this.apiUrl}/auth/google`;
  }

  saveToken(token: string): void {
    localStorage.setItem('aec_token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('aec_token');
  }

  logout(): void {
    localStorage.removeItem('aec_token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
