import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ingredient } from '../../types/ingredient';
import { FormArray } from '@angular/forms';
import { SimpleIngredient } from '../../types/simple-ingredient';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  constructor(private http: HttpClient) { }

  getIngredients() {
    return this.http.get<SimpleIngredient[]>('http://localhost:8080/recipes-api/v1/ingredients');
  }

  ingredientsToArray(ingredients: FormArray): string[] {

    const ingredientsArray = Array<any>();
    for (let i = 0; i < ingredients.length; i++) {
      ingredientsArray.push(
        { ingredientId: ingredients.at(i).value.ingredient, 
          amount: ingredients.at(i).value.quantity, 
          measurementId: ingredients.at(i).value.measurement
        });
    }

    return ingredientsArray;
  }

}
