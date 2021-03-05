import { Pipe, PipeTransform } from '@angular/core';
import { Students} from './model/students'

@Pipe({
  name: 'searchfilter'
})
export class SearchfilterPipe implements PipeTransform {

  transform(students: Students[], searchValue: string): any {
    if (!searchValue){
     return students; 
    }
    return students.filter(students =>
      students.name.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
  }

}
