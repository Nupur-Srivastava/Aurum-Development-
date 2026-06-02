import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';
import {
  UserCategory, ConsultantSubCategory, BuilderSubCategory,
  VendorSubCategory, ProjectSizeUnit
} from '../../../../core/models/user.model';

function passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
  const password = control.get('password');
  const confirm = control.get('confirmPassword');
  if (password && confirm && password.value !== confirm.value) {
    return { passwordMismatch: true };
  }
  return null;
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  isLoading = false;
  errorMessage = '';
  showPassword = false;
  showConfirmPassword = false;
  currentStep = 1; // 1 = basic info, 2 = professional details

  categories: UserCategory[] = ['Customer', 'Consultants', 'Builders/Developers', 'Vendors'];
  projectSizeUnits: ProjectSizeUnit[] = ['Thousand', 'Lakh', 'Crore'];

  subCategoryMap: Record<string, string[]> = {
    'Consultants': ['Designers', 'PMC', 'Marketing'],
    'Builders/Developers': ['Buildings', 'Infra'],
    'Vendors': ['Contractors', 'Sub-Contractors', 'Manpower', 'Material', 'Machinery']
  };

  get selectedCategory(): string {
    return this.signupForm?.get('category')?.value || '';
  }

  get isProfessional(): boolean {
    return this.selectedCategory !== 'Customer' && this.selectedCategory !== '';
  }

  get subCategories(): string[] {
    return this.subCategoryMap[this.selectedCategory] || [];
  }

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^[6-9]\d{9}$/)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required],
      category: ['', Validators.required],
      yearsOfExperience: [null],
      subCategory: [null],
      projectSize: [null],
      projectSizeUnit: ['Crore']
    }, { validators: passwordMatchValidator });

    this.signupForm.get('category')?.valueChanges.subscribe(val => {
      this.updateProfessionalValidators(val);
    });
  }

  updateProfessionalValidators(category: string): void {
    const yoe = this.signupForm.get('yearsOfExperience');
    const sub = this.signupForm.get('subCategory');
    const ps = this.signupForm.get('projectSize');

    if (category !== 'Customer' && category !== '') {
      yoe?.setValidators([Validators.required, Validators.min(0), Validators.max(999)]);
      sub?.setValidators([Validators.required]);
      ps?.setValidators([Validators.required, Validators.min(0)]);
    } else {
      yoe?.clearValidators();
      sub?.clearValidators();
      ps?.clearValidators();
    }

    yoe?.updateValueAndValidity();
    sub?.updateValueAndValidity();
    ps?.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.signupForm.invalid) {
      this.signupForm.markAllAsTouched();
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    const formData = { ...this.signupForm.value };
    delete formData.confirmPassword;

    this.authService.signUp(formData).subscribe({
      next: (res) => {
        this.authService.saveToken(res.token);
        this.router.navigate(['/dashboard']); // update when dashboard is ready
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = err.error?.message || 'Sign up failed. Please try again.';
        this.isLoading = false;
      }
    });
  }

  onGoogleSignIn(): void {
    this.authService.signInWithGoogle();
  }

  goToSignIn(): void {
    this.router.navigate(['/auth/signin']);
  }

  hasError(field: string, error: string): boolean {
    const ctrl = this.signupForm.get(field);
    return !!(ctrl?.hasError(error) && ctrl?.touched);
  }
}