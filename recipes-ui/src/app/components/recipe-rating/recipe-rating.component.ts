import { Component, Input } from '@angular/core';
import { Recipe } from '../../types/recipe';

@Component({
  selector: 'app-recipe-rating',
  standalone: true,
  imports: [],
  templateUrl: './recipe-rating.component.html',
  styleUrl: './recipe-rating.component.css'
})
export class RecipeRatingComponent {

  @Input() recipe!: Recipe;

  
}
