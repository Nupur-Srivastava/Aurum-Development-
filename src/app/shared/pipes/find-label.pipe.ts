import { Pipe, PipeTransform } from '@angular/core';
 
@Pipe({ name: 'findLabel', standalone: true })
export class FindLabelPipe implements PipeTransform {
  transform(options: { value: string; label: string }[], value: string): string {
    return options.find(o => o.value === value)?.label ?? value;
  }
}

