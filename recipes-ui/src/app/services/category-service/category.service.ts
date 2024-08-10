import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../../types/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  getCategories() {
    return this.http.get<Category[]>('http://localhost:8080/recipes-api/v1/categories');
  }
}
