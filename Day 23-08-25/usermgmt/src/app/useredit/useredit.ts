import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Userservice } from '../userservice';
import { User } from '../user';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-useredit',
  imports: [FormsModule],
  templateUrl: './useredit.html',
  styleUrl: './useredit.css'
})
export class Useredit implements OnInit   {

  constructor(private activatedRoute:ActivatedRoute,
    private userService:Userservice,
    private router:Router

  ) {}
  id:string | null = null;
  user:User = {

    name: '',
    email: ''
  };
  ngOnInit()
  {
      console.log("==> Useredit Component Initialized");
      // Get the user ID from the route parameters
     this.id = this.activatedRoute.snapshot.paramMap.get('id');
      if (this.id) {
        this.userService.findUser(this.id).subscribe(user => {
          // Handle the user data here, e.g., populate a form
          console.log(user);
          this.user = user;
        });
      }
    }
     save()
     {
      this.userService.saveUser(this.user).subscribe({
        next: (user) => {
          console.log('User saved successfully:', user);
          this.router.navigate(['/userlist']); // Navigate to the users list after saving

        },
        error: (error) => {
          console.error('Error saving user:', error);
          // Handle the error, e.g., show an error message
        }
     });
    }
  }

