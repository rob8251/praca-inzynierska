import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../types/user';
import { AuthResponse } from '../types/auth-response';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: User | undefined;

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post<AuthResponse>('http://localhost:8080/recipes-api/v1/login', { username, password })
  }

  register(username: string, password: string, email: string, firstName: string, lastName: string) {
    return this.http.post<AuthResponse>('http://localhost:8080/recipes-api/v1/register', { username, password, email, firstName, lastName })
  }

  setUser(authResponse: AuthResponse): void {
    localStorage.setItem('user', JSON.stringify(authResponse.user));
    localStorage.setItem('token', authResponse.token);
    console.log(authResponse.token)
    console.log(authResponse.user)
    this.user = authResponse.user;
  }

  userLogout(): void {
    this.user = undefined;
    localStorage.removeItem('user');
  }

  getUserId(): number | undefined {
    return this.user?.id;
  }

  isAuthenticated(): boolean {
    const user = localStorage.getItem('user');
    if (user) {
      return true;
    }
    return false;
  }

  bearerToken(): string {
    const token = localStorage.getItem('token');
    return `Bearer ${token}`;
  }
}
