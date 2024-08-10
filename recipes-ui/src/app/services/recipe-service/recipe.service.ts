import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ingredient } from '../../types/ingredient';
import { CreateRecipe } from '../../types/create-recipe';
import { FormArray } from '@angular/forms';
import { Recipe } from '../../types/recipe';
import { SimpleIngredient } from '../../types/simple-ingredient';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  url = 'http://localhost:8080/recipes-api/v1/ingredients';

  constructor(private http: HttpClient) { }

  getIngredients() {
    return this.http.get<SimpleIngredient[]>(this.url);
  }

  addRecipe(recipe: CreateRecipe) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.bearerToken()
    });
    const options = { headers: headers};
    return this.http.post('http://localhost:8080/recipes-api/v1/recipes', recipe, options);
  }

  getRecipeById(id: number) {
    return this.http.get<Recipe>(`http://localhost:8080/recipes-api/v1/recipes/${id}`);
  }

  getRecipes() {
    return this.http.get<Recipe[]>('http://localhost:8080/recipes-api/v1/recipes');
  }

  editRecipe(id: number, recipe: CreateRecipe) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.bearerToken()
    });
    const options = { headers: headers};
    return this.http.put(`http://localhost:8080/recipes-api/v1/recipes/${id}`, recipe, options);
  }

  deleteRecipe(id: number) {
    let headers = new HttpHeaders({
      'Authorization': this.bearerToken()
    });
    const options = { headers: headers};
    return this.http.delete(`http://localhost:8080/recipes-api/v1/recipes/${id}`, options);
  }

  bearerToken(): string {
    const token = localStorage.getItem('token');
    console.log(token);
    return `Bearer ${token}`;
  }

  stepsToArray(steps: FormArray): string[] {
    const stepsArray = Array<any>();
    for (let i = 0; i < steps.length; i++) {
      stepsArray.push(
        { description: steps.at(i).value.step }
      );
    }

    return stepsArray;
  }
}
