import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { SalaryModel, User } from 'src/app/models/user-roles';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {


  @Input() isUser = false;
  @Input() extUser = null;

  user: User;

  modelDate;
  salaryDate;
  modalRef: BsModalRef;
  form = {
    comment: '',
    bonus: 0
  };
  bonuses;
  salary: SalaryModel;

  constructor(private modalService: BsModalService,
              private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
      if (!this.isUser){
    this.route.params.subscribe(params => {
      this.userService.getUser(params.id).subscribe(user => this.user = user);
    });
} else{
    this.user = this.extUser;
}
  }

  checkUserBonus() {
    this.userService.getBonuses(this.user.id, this.modelDate.getMonth() + 1, this.modelDate.getFullYear()).subscribe(value => {
      this.bonuses = value;
    });
  }

  getUserSalary() {
    this.userService.getSalary(this.user.id, this.salaryDate.getMonth() + 1, this.salaryDate.getFullYear()).subscribe( value => {
      this.salary = value;
      this.salary.monthEff = Number(this.salary.monthEff).toFixed(2);
    });
  }

  onOpenCalendar(container) {
    container.monthSelectHandler = (event: any): void => {
    container._store.dispatch(container._actions.select(event.date));
    };
    container.setViewMode('month');
  }

  openBounsPopover(template): void {
    this.modalRef = this.modalService.show(template);
  }

  onSubmit() {
    this.closeBonusPopover();
    this.userService.addBonus(this.user.id, this.form.comment, new Date(), this.form.bonus).subscribe(() => {
      this.form = { comment: '', bonus: 0 };
    });
  }

  closeBonusPopover(): void {
    this.modalRef.hide();
  }

}
