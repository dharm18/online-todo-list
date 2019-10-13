import { Component, OnInit } from '@angular/core';
import { SignUpRequest } from '../SignUpRequest';
import { AuthenticationService } from '../service/authentication.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  errorResponse: any;
  successResponse: any;
  signUpRequest: SignUpRequest;

  constructor(
    private authenticationService: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.signUpRequest = new SignUpRequest();
  }

  signup(signupForm) {
    this.authenticationService.executeJWTSignUpService(signupForm.value)
          .subscribe (
            data => {
              console.log(data)
              this.successResponse = data;
            },
            error => {
              console.log(error)
              this.errorResponse = error.error.message;
            }
          )
  }

}
