import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Userservice } from '../userservice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-useradd',
  imports: [FormsModule],
  templateUrl: './useradd.html',
  styleUrl: './useradd.css'
})
export class Useradd {


  constructor(private userService:Userservice,private router:Router) { }
  userName:string = '';
  userEmail:string = '';

submit() {

  console.log('Adding user:', this.userName, this.userEmail);

  this.userService.addUser({ name: this.userName, email: this.userEmail }).subscribe(response => {
    console.log('User added successfully:', response);
    this.userName = '';
    this.userEmail = '';
    this.router.navigate(['/userlist']); // Navigate to the users list after adding  the user

  }, error => {
    console.error('Error adding user:', error);
  });
}

}
