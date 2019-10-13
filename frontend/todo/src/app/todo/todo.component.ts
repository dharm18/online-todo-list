import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoService } from './../service/data/todo.service';
import { Todo } from '../todo';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  id:number
  todo: Todo
  username: string;

  constructor(private todoService: TodoService,
    private authenticationService: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {

    this.id = this.route.snapshot.params['id'];
    
    this.todo = new Todo();

    this.username = this.authenticationService.getAuthenticatedUser()
    
    if(this.id!=-1) {
      this.todoService.retrieveTodo(this.username, this.id)
          .subscribe (
            data => this.todo = data
          )
    }

  }

  saveTodo() {
    if(this.id == -1) { //=== ==
      this.todoService.createTodo(this.username, this.todo)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['todo-list'])
            }
          )
    } else {
      this.todoService.updateTodo(this.username, this.id, this.todo)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['todo-list'])
            }
          )
    }
  }

}
