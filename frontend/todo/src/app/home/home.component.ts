import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { ConcatSource } from 'webpack-sources';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  name = "";

  constructor(private authenticationService :AuthenticationService) { }

  ngOnInit() {
    this.name = this.authenticationService.getAuthenticatedUser();
    console.log(this.name);
  }

}
