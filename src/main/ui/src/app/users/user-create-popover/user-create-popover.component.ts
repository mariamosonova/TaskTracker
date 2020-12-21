import { Component, OnInit, Input, ViewChild, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { userRoles } from 'src/app/models/user-roles';

@Component({
  selector: 'app-user-create-popover',
  templateUrl: './user-create-popover.component.html',
  styleUrls: ['./user-create-popover.component.css']
})
export class UserCreatePopoverComponent implements OnInit, OnChanges {

  @ViewChild('template') popover: any;

  @Input() mode: 'create' | 'edit';
  @Input() visible = false;
  @Input() user;

  @Output() closeModal = new EventEmitter();
  @Output() userCreate = new EventEmitter<any>();
  @Output() userEdite = new EventEmitter<any>();

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  modalRef: BsModalRef;

  constructor() {}

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.user && !!changes.user.currentValue) {
      this.form.username = changes.user.currentValue.username;
      this.form.email = changes.user.currentValue.email;
      this.form.firstname = changes.user.currentValue.firstname;
      this.form.lastname = changes.user.currentValue.lastname;
      this.form.password = changes.user.currentValue.password;
      this.form.checkbox = changes.user.currentValue.roles[0].name === 'ROLE_MODERATOR' ? true : false;
      this.form.baseSalary = changes.user.currentValue.baseSalary;
    }
  }

  closePopover() {
    this.closeModal.emit();
  }

  onSubmit() {
  if (this.mode === 'create') {
      const user = {
        username: this.form.username,
        email: this.form.email,
        firstname: this.form.firstname,
        lastname: this.form.lastname,
        password: this.form.password,
        role: this.form.checkbox ? ['mod'] : [''],
        baseSalary: this.form.baseSalary.toString()
      };

      this.userCreate.emit(user);
    } else {
      const user = {
        id: this.user.id,
        username: this.form.username,
        email: this.form.email,
        firstname: this.form.firstname,
        lastname: this.form.lastname,
        password: this.form.password,
        roles: this.form.checkbox ? [userRoles.manager] : [userRoles.user],
        baseSalary: this.form.baseSalary.toString()
      };
      this.userEdite.emit(user);
    }

  this.closeModal.emit();
  }

  onClosePopover() {
    this.modalRef.hide();
  }

}
