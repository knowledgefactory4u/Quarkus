import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getUser(_id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${_id}`);
  }

  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  updateUser(_id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${_id}`, value);
  }

  deleteUser(_id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${_id}`, { responseType: 'text' });
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}