import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Userservice {

  constructor(private http:HttpClient) { }

  getUsers():Observable<User[]>  {
    let url:string="http://localhost:9000/user"
    return this.http.get<User[]>(url);
  }

  addUser(user: User): Observable<User> {
    let url:string="http://localhost:9000/user"
    return this.http.post<User>(url, user);
  }
  deleteUser(id: string): Observable<void> {
    let url:string=`http://localhost:9000/user/${id}`;
    return this.http.delete<void>(url);
  }

  findUser(id: string): Observable<User> {
    let url:string=`http://localhost:9000/user/${id}`;
    return this.http.get<User>(url);
  }

  saveUser(user: User): Observable<User> {
    let url:string=`http://localhost:9000/user`;
    return this.http.put<User>(url, user);

}
}
