import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, FormsModule, ReactiveFormsModule, Validators, FormArray } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { IngredientService } from '../../services/ingredient-service/ingredient.service';
import { MeasurementService } from '../../services/measurement-service/measurement.service';
import { CategoryService } from '../../services/category-service/category.service';
import { Category } from '../../types/category';
import { NgForOf } from '@angular/common';
import { Ingredient } from '../../types/ingredient';
import { Observable, startWith, map } from 'rxjs';
import { Measurement } from '../../types/measurement';
import { CreateRecipe } from '../../types/create-recipe';
import { RecipeService } from '../../services/recipe-service/recipe.service';
import { Recipe } from '../../types/recipe';
import { SimpleIngredient } from '../../types/simple-ingredient';

@Component({
  selector: 'app-recipe-form',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, MatIconModule, MatAutocompleteModule, NgForOf],
  templateUrl: './recipe-form.component.html',
  styleUrl: './recipe-form.component.css'
})
export class RecipeFormComponent implements OnInit {

  recipeForm: FormGroup;
  categories: Category[] | undefined;
  selectedCategory!: string;
  ingredients: FormArray;
  steps: FormArray;
  allIngredients: SimpleIngredient[] | undefined;
  allMeasurements: Measurement[] | undefined;

  @Input() recipeToEdit: Recipe | undefined;
  
  @Output() recipeEvent = new EventEmitter<CreateRecipe>();

  constructor(
    private ingredientService: IngredientService, 
    private measurementService: MeasurementService,
    private categoryService: CategoryService,
    private recipeService: RecipeService) {

      this.recipeForm = new FormGroup({
        name: new FormControl(''),
        description: new FormControl(''),
        prepTimeMinutes: new FormControl(''),
        servings: new FormControl(''),
        category: new FormControl(''),
        ingredientsArray: new FormArray([
          new FormGroup({
            ingredient: new FormControl(''),
            measurement: new FormControl(''),
            quantity: new FormControl('')
          })
        ]),
        stepsArray: new FormArray([
          new FormGroup({
            step: new FormControl('')
          })
        ])
        
      });
      this.ingredients = this.recipeForm.get('ingredientsArray') as FormArray;
      this.steps = this.recipeForm.get('stepsArray') as FormArray;
    }

    ngOnInit(): void {
        this.loadCategories();
        this.loadIngredients();
        this.loadMeasurements();
        this.loadRecipeToEdit();
    }

    onSubmit() {
      if (this.ingredients.length === 0) {
        return;
      }
      if (this.steps.length === 0) {
        return;
      }

      let recipe: CreateRecipe = {
        name: this.recipeForm.value.name,
        description: this.recipeForm.value.description,
        prepTimeMinutes: this.recipeForm.value.prepTimeMinutes,
        servings: this.recipeForm.value.servings,
        categoryId: this.recipeForm.value.category,
        ingredients: this.ingredientService.ingredientsToArray(this.ingredients),
        steps: this.recipeService.stepsToArray(this.steps)
      }

      // this.recipeService.addRecipe(recipe).subscribe(() => {
      //   // this.recipeEvent.emit();
      // });
      this.recipeEvent.emit(recipe);
      this.recipeToEdit = undefined;
    }

    loadRecipeToEdit() {
      if (this.recipeToEdit) {
        this.recipeForm.patchValue({
          name: this.recipeToEdit.name,
          description: this.recipeToEdit.description,
          prepTimeMinutes: this.recipeToEdit.prepTimeMinutes,
          servings: this.recipeToEdit.servings,
          category: this.recipeToEdit.category.id
        });

        console.log(this.recipeToEdit.ingredients)
        this.ingredients.clear();
        this.recipeToEdit.ingredients.forEach(ingredient => {
          this.ingredients.push(
            new FormGroup({
              ingredient: new FormControl(ingredient.ingredientId),
              measurement: new FormControl(ingredient.measurementId),
              quantity: new FormControl(ingredient.amount)
            })
          );
        });
        console.log(this.recipeToEdit.ingredients)
        

        this.steps.clear();
        this.recipeToEdit.steps.reverse();
        this.recipeToEdit.steps.forEach(step => {
          this.steps.push(
            new FormGroup({
              step: new FormControl(step.description)
            })
          );
        });
      }
    }

    loadCategories() {
      this.categoryService.getCategories().subscribe((categories: Category[]) => {
        this.categories = categories;
      });
    }

    loadIngredients() {
      this.ingredientService.getIngredients().subscribe((ingredients: SimpleIngredient[]) => {
        this.allIngredients = ingredients;
      });
    }

    loadMeasurements() {
      this.measurementService.getMeasurements().subscribe((measurements: Measurement[]) => {
        this.allMeasurements = measurements;
      });
    }

    
  moreIngredients(): void {
    this.ingredients.push(
      new FormGroup({
        ingredient: new FormControl(''),
        measurement: new FormControl(''),
        quantity: new FormControl('')
    }));
  }

  lessIngredients(index: number): void {
    this.ingredients.removeAt(index);
  }

  moreSteps(): void {
    this.steps.push(
      new FormGroup({
        step: new FormControl('')
    }));
  }

  lessSteps(index: number): void {
    this.steps.removeAt(index);
  }

  hasIngredientErrors(): boolean {
    for (const control of this.ingredients.controls) {
      if (control.get('ingredient')?.errors) {
        return true;
      }
    }
    return false;
  }

  displayFn(ingredient: any): string {
    return ingredient && ingredient.name ? ingredient.name : '';
  }

  displayFnMeasurement(measurement: any): string {
    return measurement && measurement.name ? measurement.name : '';
  }

}
