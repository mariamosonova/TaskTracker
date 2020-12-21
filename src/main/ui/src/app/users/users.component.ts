import { Component, OnInit, TemplateRef  } from '@angular/core';
import { Router } from '@angular/router';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { User } from '../models/user-roles';
import { AuthService } from '../_services/auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

modalRef: BsModalRef;
users: User[] = [];
popoverMode = 'create';
selectedUser;
errorMessage;

constructor(private modalService: BsModalService,
            private userService: UserService,
            private authService: AuthService,
            private router: Router) {}

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.userService.getAllUsers().subscribe(users => {
      this.users = users;
    });
  }

  openModal(template: TemplateRef<any>, mode: string, user?: any) {
    this.selectedUser = user;
    this.popoverMode = mode;
    this.modalRef = this.modalService.show(template);
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  onUserCreate(user): void {
    this.authService.register(user).subscribe(
      () => this.getUsers(),
      err => {
        this.errorMessage = err.error.message;
      });
  }

  getStrRoles(roles: any[]): string {
    return roles.map(role => role.name).join(', ');
  }

  isAdmin(user: any): boolean {
    return user.roles.map(role => role.name).join(', ').indexOf('ROLE_ADMIN') !== -1;
  }

  onUserEdite(user: User) {
    this.userService.editeUser(user).subscribe(() =>
      this.users = this.users.map(searchUser => {
      if (searchUser.username === user.username) {
        searchUser = user;
      }
      return searchUser;
    })
    );
  }

  deleteUser(deleteUser: any) {
    this.userService.deleteUser(deleteUser).subscribe(() => {
      this.users = this.users.filter( user => user.username !== deleteUser.username);
    });
  }

  selectUser(user): void {
    this.router.navigate([`users/${user.id}`]);
  }

}
