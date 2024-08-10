import { Component, Input } from '@angular/core';
import { RecipeFormComponent } from '../recipe-form/recipe-form.component';
import { CreateRecipe } from '../../types/create-recipe';
import { Router } from '@angular/router';
import { RecipeService } from '../../services/recipe-service/recipe.service';

@Component({
  selector: 'app-create-recipe',
  standalone: true,
  imports: [RecipeFormComponent],
  templateUrl: './create-recipe.component.html',
  styleUrl: './create-recipe.component.css'
})
export class CreateRecipeComponent {
  @Input() recipe!: CreateRecipe;

  constructor(private recipeService: RecipeService, private router: Router) {}

  createRecipe(recipe: CreateRecipe) {
      this.recipeService.addRecipe(recipe).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
