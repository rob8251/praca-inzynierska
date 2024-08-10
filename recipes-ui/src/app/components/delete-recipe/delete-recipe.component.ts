import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeService } from '../../services/recipe-service/recipe.service';
import { Recipe } from '../../types/recipe';

@Component({
  selector: 'app-delete-recipe',
  standalone: true,
  imports: [],
  templateUrl: './delete-recipe.component.html',
  styleUrl: './delete-recipe.component.css'
})
export class DeleteRecipeComponent {

  @Input() recipe!: Recipe;

  constructor(private recipeService: RecipeService, private router: Router) {}

  deleteRecipe() {
    this.recipeService.deleteRecipe(this.recipe.id).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
