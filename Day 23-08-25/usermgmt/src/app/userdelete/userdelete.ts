import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Userservice } from '../userservice';

@Component({
  selector: 'app-userdelete',
  imports: [],
  templateUrl: './userdelete.html',
  styleUrl: './userdelete.css'
})
export class Userdelete {

  id:string | null = null;
  constructor(private activeRoute:ActivatedRoute,private userService:Userservice,private router:Router) {
   }
   ngOnInit() {
         this.id = this.activeRoute.snapshot.paramMap.get('id');
         console.log("User to be deleted has id:", this.id);
         this.userService.deleteUser(this.id!).subscribe(response => {
           console.log('User deleted successfully');
           this.router.navigate(['/userlist']); // Navigate to the user list after deletion
           // Logic to navigate back to user list or show a success message
         }  , error => {
           console.error('Error deleting user:', error);
           // Logic to handle error, e.g., show an error message
         }  );
   }

}
