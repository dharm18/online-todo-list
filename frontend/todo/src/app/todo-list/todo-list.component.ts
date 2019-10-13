import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Todo } from '../todo';
import { TodoService } from '../service/data/todo.service';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})

export class TodoListComponent implements OnInit {

  todos: Todo[];
  message: string;
  private username : string; 

  constructor(
    private todoService:TodoService,
    private authenticationService: AuthenticationService,
    private router : Router
  ) { }

  ngOnInit() {
    this.username = this.authenticationService.getAuthenticatedUser();
    this.refreshTodos();
  }

  refreshTodos(){
    this.todoService.retrieveAllTodos(this.username).subscribe(
      response => {
        console.log(response);
        this.todos = response;
      }
    )
  }

  deleteTodo(id) {
    console.log(`delete todo ${id}` )
    this.todoService.deleteTodo(this.username, id).subscribe (
      response => {
        console.log(response);
        this.message = `Delete of Todo ${id} Successful!`;
        this.refreshTodos();
      }
    )
  }

  updateTodo(id) {
    console.log(`update ${id}`)
    this.router.navigate(['todos',id])
  }

  addTodo() {
    this.router.navigate(['todos',-1])
  }

  toggleTodoComplete(todo, id) {
    this.todoService.toggleTodoComplete(this.username, todo, id).subscribe(
      response => {
        console.log(response);
        this.refreshTodos();
      }
    )
  }
}