import { Component, OnInit } from '@angular/core';
import { Userservice } from '../userservice';
import { User } from '../user';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-userlist',
  imports: [RouterLink],
  templateUrl: './userlist.html',
  styleUrl: './userlist.css'
})
export class Userlist implements OnInit   {

  constructor(private userService:Userservice,private router:Router) { }

  users: User[] = [];


  ngOnInit() {
    console.log("==> Userlist Component Initialized");
    this.userService.getUsers().subscribe((data: User[]) => {
      this.users = data;
      console.log('Users fetched successfully:', data);
    }, (error) => {
      console.error('Error fetching users:', error);
    });

}

  addUser() {
    this.router.navigate(['/useradd']);
    // Logic to navigate to the user add page
  }
}
