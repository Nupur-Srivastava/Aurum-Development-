export type UserCategory = 'Customer' | 'Consultants' | 'Builders/Developers' | 'Vendors';

export type ConsultantSubCategory = 'Designers' | 'PMC' | 'Marketing';
export type BuilderSubCategory = 'Buildings' | 'Infra';
export type VendorSubCategory = 'Contractors' | 'Sub-Contractors' | 'Manpower' | 'Material' | 'Machinery';

export type SubCategory = ConsultantSubCategory | BuilderSubCategory | VendorSubCategory;

export type ProjectSizeUnit = 'Thousand' | 'Lakh' | 'Crore';

export interface SignUpFormData {
  fullName: string;
  email: string;
  phoneNumber: string;
  password: string;
  confirmPassword: string;
  category: UserCategory;
  // Professional Details (non-Customer only)
  yearsOfExperience?: number;
  subCategory?: SubCategory;
  projectSize?: number;
  projectSizeUnit?: ProjectSizeUnit;
}

export interface SignInFormData {
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  refreshToken: string;
  user: {
    id: string;
    fullName: string;
    email: string;
    category: UserCategory;
  };
}