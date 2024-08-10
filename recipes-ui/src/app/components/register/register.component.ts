import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private userService: UserService, private router: Router) {
    this.registerForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      firstName: new FormControl(''),
      lastName: new FormControl('')
    });
  }

  onSubmit() {
    const username: string = this.registerForm.value.username;
    const password: string = this.registerForm.value.password;
    const email: string = this.registerForm.value.email;
    const firstName: string = this.registerForm.value.firstName;
    const lastName: string = this.registerForm.value.lastName;

    this.userService.register(username, password, email, firstName, lastName).subscribe({
      complete: () => {
        this.registerForm.reset();
        this.router.navigate(['/login']);
      }
    });
  }
}
