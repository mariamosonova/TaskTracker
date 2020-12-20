import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from '../_services/user.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  content = '';

  constructor(private userService: UserService, private appService: AppService) { }


  taskForm = new FormGroup({
    taskName: new FormControl('', Validators.nullValidator && Validators.required),
    taskDescription: new FormControl('', Validators.nullValidator && Validators.required)
  });

  tasks: any[] = [];
  taskCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmitTask() {
    this.appService.addTask(this.taskForm.value, this.taskCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
      console.log('message::::', data);
      this.taskCount = this.taskCount + 1;
      console.log(this.taskCount);
      this.taskForm.reset();
    });
  }

  getAllMyTasks() {
    this.appService.getMyTasks().pipe(takeUntil(this.destroy$)).subscribe((tasks: any[]) => {
		this.taskCount = tasks?.length ? tasks.length : 0;
        this.tasks = tasks;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
    this.userService.getUserBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
    this.getAllMyTasks();
  }
}
