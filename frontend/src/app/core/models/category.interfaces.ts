export interface DropdownOption {
  value: string;
  label: string;
}

export interface SubCategory2Selection {
  subCategory2: string;
  projectTypes: string[];
}

export interface SubCategory1Selection {
  subCategory1: string;
  subCategory2Selections: SubCategory2Selection[];
}