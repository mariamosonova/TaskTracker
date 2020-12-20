import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { TaskService } from '../../_services/task.service';

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

  tasks: any;
  currentTask = null;
  currentIndex = -1;
  title = '';

  isTaskEditMode = false;

  showAdminBoard = false;
  showModeratorBoard = false;

  form = {
    comment: ''
  }
  user;
  comments;

  constructor(private taskService: TaskService,
              private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.retrieveTasks();

    if (!!this.tokenStorageService.getToken()) {
      this.user = this.tokenStorageService.getUser();
      this.showAdminBoard = this.user.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.user.roles.includes('ROLE_MODERATOR');
    }
  }

  retrieveTasks(): void {
    this.taskService.getAll()
      .subscribe(
        data => {
          this.tasks = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveTasks();
    this.currentTask = null;
    this.currentIndex = -1;
  }

  setActiveTask(task, index): void {
    this.getTask(task.id, index);
  }

  getTask(id, index?): void {
    this.taskService.get(id).subscribe( newTask => {
      this.currentTask = newTask;
      this.currentTask.comments = this.currentTask.comments.map( comment => {
        comment.date = new Date(comment.date);
        return comment;
      });
      this.currentIndex = index ? index : this.currentIndex;
    });
  }

  removeAllTasks(): void {
    this.taskService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchTitle(): void {
    this.taskService.findByTitle(this.title)
      .subscribe(
        data => {
          this.tasks = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  onTaskCreate(): void {
    this.retrieveTasks();
    this.isTaskEditMode = false;
  }

  addComment(): void {
    const comment = {
      taskId: this.currentTask.id.toString(),
      userId: this.user.id.toString(),
      comment: this.form.comment.toString(),
      date: new Date().toString()
    };

    this.taskService.addTaskComment(comment).subscribe(() => {
      this.form.comment = '';
      this.getTask(this.currentTask.id);
    });
  }
}
