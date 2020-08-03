import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material';
import { GrowsariUserService } from 'src/app/services/user/growsariuser.service';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  name: string;
  email: string;
  password: boolean;

  isLoading = false;
  submitted = false;
  userForm : FormGroup;
  enabled = true;

  constructor(
    private formBuilder: FormBuilder,
    private userService : GrowsariUserService,
    private errorDialog: MatDialog
  ) { }

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password:['', Validators.required],
      confirmPassword:['', Validators.required],
      staticEnabled: []
    },  {
      validator: this.confirmPasswordMatch('password', 'confirmPassword')
    });
  }

  getnameModel() { return this.name}
  get formFields() { return this.userForm.controls; }

  createUser() {
    this.isLoading = true;
    this.submitted = true;

    if (this.userForm.invalid) {
      this.isLoading = false;
      return;
    }
  }

  resetForm() {
    
  }

  confirmPasswordMatch(password: string, confirmPassword: string) {
    return (formGroup: FormGroup) => {
      const passwordControl = formGroup.controls[password];
      const confirmPasswordControl = formGroup.controls[confirmPassword];

      if (confirmPasswordControl.errors && !confirmPasswordControl.errors.confirmPasswordMatch) {
          return;
      }

      if(passwordControl.value !== confirmPasswordControl.value){
          confirmPasswordControl.setErrors({confirmPasswordMatch: true});
      }else{
          confirmPasswordControl.setErrors(null);
      }
  }
  }
}
