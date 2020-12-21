import { Component, OnInit, TemplateRef  } from '@angular/core';
import { Router } from '@angular/router';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { User } from '../models/user-roles';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

modalRef: BsModalRef;
users = [];
popoverMode = 'create';
selectedUser;
errorMessage;
roles: any;
isAdminRole: any;
isModeratorRole: any;

constructor(private modalService: BsModalService,
            private userService: UserService,
            private authService: AuthService,
            private router: Router,
            private tokenStorageService: TokenStorageService) {}

  ngOnInit(): void {
    this.getUsers();
    const user = this.tokenStorageService.getUser();
    this.roles = user.roles;
  
    this.isAdminRole = this.roles.includes('ROLE_ADMIN');
    this.isModeratorRole = this.roles.includes('ROLE_MODERATOR');
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

  isModerator(user: any): boolean {
    return user.roles.map(role => role.name).join(', ').indexOf('ROLE_MODERATOR') !== -1;
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
