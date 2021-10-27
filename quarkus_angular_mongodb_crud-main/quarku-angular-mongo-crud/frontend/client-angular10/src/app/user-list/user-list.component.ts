import { UserDetailsComponent } from '../user-details/user-details.component';
import { Observable } from "rxjs";
import { UserService } from "../user.service";
import { User } from "../user";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
  styleUrls: ["./user-list.component.css"]
})
export class UserListComponent implements OnInit {
  users: Observable<User[]>;

  constructor(private userService: UserService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersList();
  }

  deleteUser(_id: string) {
  
    this.userService.deleteUser(_id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  updateUser(id: string){
    this.router.navigate(['update', id]);
  }
  userDetails(_id: string){
    this.router.navigate(['details', _id]);
  }
}